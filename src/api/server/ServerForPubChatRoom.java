package api.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;

import headInfoFliter.factory.ProcesserFactory;
import object_client_server.Client;
import threadData.ThreadDataTransfer;

/**
 * 服务器的Socket服务
 * @author 林思鑫
 *
 */
public class ServerForPubChatRoom implements AsServer{
	
	/**
	 * 服务器的ServerSocket
	 */
	private ServerSocket serverSocket;

	
	/**
	 * 用来保存在线用户名列表
	 */
	public ArrayList<String> userNameList;
	
	/**
	 * 用来显示获取客户端的发送到服务器的信息
	 */
	public JTextArea textPane;
	
	/**
	 * 用来计算在线人数
	 * 向外暴露
	 */
	public int counter=0;
	
	/**
	 * 用来保存客户端
	 */
	ArrayList<Client> clientList;
	
	/**
	 * 用来传递线程中的值到外面
	 */
	private ThreadDataTransfer tdt;
	
	/**
	 * 过滤器工厂,用来过滤头信息,然后执行相应的操作
	 */
	private ProcesserFactory processerFactory;
	
	/**
	 * 构造方法
	 * @param tdt 中介数据传输类
	 */
	public ServerForPubChatRoom(ThreadDataTransfer tdt)  {
		userNameList=new ArrayList<>();
		clientList=new ArrayList<>();
		this.tdt=tdt;
	}
	
	/**
	 * 构造方法
	 * @param textPane 用来显示信息的,主要是测试使用
	 * @param tdt 向外界传递线程内的数据的中介
	 */
	public ServerForPubChatRoom(ThreadDataTransfer tdt,JTextArea textPane)  {
		this(tdt);
		this.textPane=textPane;
	}
	
	/**
	 * 启动服务,接收客户端的连接,多线程的创建
	 * @param port 所使用的端口
	 * @throws IOException 
	 */
	@Override
	public  void startService(int port) throws IOException
	{
			//新建一个serverSocket,可能会出现端口已经被占用的问题
			serverSocket=new ServerSocket(port);
			
			if ( textPane != null )
				textPane.append("正在等待链接\n");
			
			startListen();
			
	}
	
	/**
	 * 开始监听端口 让客户端连接进来
	 * @throws IOException
	 */
	private void startListen() throws IOException
	{
		//开始监听并创建新的线程,发送连接成功的信息,处理客户端发来的头信息
		while(true)
		{
			Socket currentSocket = serverSocket.accept();
			Thread tempThread=new Thread(new Runnable() {
				@Override
				public void run() {
					
					Client client = null;
					try {
						client = new Client(currentSocket);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					synchronized(this)
					{
						counter++;
					}
					
					//向客户端发送连接成功的消息
					client.getSocketStream().getPrintWriter().println("连接服务器成功\n");
					client.getSocketStream().getPrintWriter().flush();
					
					//接收头信息
					String line = null;
					try {
						line = client.getSocketStream().getBufferReader().readLine();
						
						if(textPane != null)
							textPane.append(line+"\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//新建头信息处理工厂过滤信息
					ProcesserFactory.setMemeberList(clientList);
					//获取用户名
					client.setUserName(searchUserName(line));
					
					//将客户端加入到列表中去
					clientList.add(client);
					
					//将用户名加入到列表中去
					userNameList.add(client.getUserName());
					
					//更新服务器组件的数据
					if(tdt!=null)
						tdt.updateState(counter, userNameList);
					
					if(textPane != null)
						textPane.append("userNameList="+listToString(userNameList)+"\n");
					
					
					//向客户端发送在线列表的消息
					
					
					//接收信息 测试使用 这个应该放在最后
					if(textPane != null)
						try {
							while((line=client.getSocketStream().getBufferReader().readLine()) != null)
							{
								textPane.append(line+"\n");
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					
				}
			});
			tempThread.start();//线程必须启动,否则会一直阻塞在那里
		}
	}
	
	/**
	 * 从头信息中获取用户名信息
	 * @param 从字符串中截取用户名
	 * @return 返回客户端的用户名
	 */
	private String searchUserName(String line)
	{
		int i=line.indexOf("UserName=");
		int length="UserName=".length();
		if(i!=-1)
			return line.substring(i+length,line.indexOf("&",length));
		else
			return "";
	}

	/**
	 * 获取用户名信息列表中的所有用户名,转换成特定格式的字符串(userName1&userName2&...&)
	 * @param nameList 保存了在线用户名信息的列表
	 * @return 特定格式的在线用户名字符串
	 */
	private String listToString(ArrayList<String> nameList)
	{
		String returnString="";
		Iterator<String> it=nameList.iterator();
		while(it.hasNext())
		{
			returnString=returnString+it.next()+"&";
		}
		return returnString;
	}
	
	
	/**
	 * 停止所有的服务,这个应该给所有的客户端发送退出消息,然后让客户端退出,之后自己在停止socket
	 * @throws IOException serverSocket.close()会产生的错误,交给调用者处理
	 */
	synchronized public void stopService() throws IOException
	{
		//流的关闭有点复杂 这是因为流的关闭是两方面的 需要好好研究一下
//		Iterator<Client> it = clientList.iterator();
//		while(it.hasNext())
//		{
//			it.next().getSocketStream().closeStream();
//		}
//		
		this.serverSocket.close();//这个指定的是不再去监听那个端口了
		
	}
	
	/**
	 * 用来发信息给指定的客户端
	 * @param username 指定发送信息的用户名
	 * @param message 要发的信息
	 */
	public void sendMessage(Client client, String message)
	{
		if (client != null )
		{
			client.getSocketStream().getPrintWriter().println(message);
			client.getSocketStream().getPrintWriter().flush();
		}
	}

	/**
	 * 对所有保存的活动客户发送消息
	 * @param message
	 */
	public void sendAllClient(String message)
	{
		Iterator<Client> it = clientList.iterator();
		while(it.hasNext())
		{
			PrintWriter pw=it.next().getSocketStream().getPrintWriter();
			pw.println(message);
			pw.flush();
		}
	}
	
	
	@Override
	public void registerUpdate() {
		// TODO Auto-generated method stub
		
	}

}

