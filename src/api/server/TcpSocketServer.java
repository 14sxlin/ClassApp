package api.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JTextArea;

import threadData.ThreadDataTransfer;

/**
 * 服务器的Socket服务
 * @author 林思鑫
 *
 */
public class TcpSocketServer implements AsServer{
	
	/**
	 * 服务器的ServerSocket
	 */
	private ServerSocket serverSocket;
	
	/**
	 * 用来保存在线用户的socketstream
	 */
	private HashMap<String, SocketStream> socketStreamMap;
	
	/**
	 * 用来保存在线的用户socket
	 */
	public HashMap<String, Socket> socketMap;
	
	/**
	 * 用来保存线程的信息
	 */
	public HashMap<String, Thread> threadMap;
	
	/**
	 * 用来保存在线用户名列表
	 */
	public ArrayList<String> userNameList;
	
	/**
	 * 内部类,保存了输入流和输出流信息
	 */
	private SocketStream ss;
	
	/**
	 * 保存当前的用户名
	 */
	private String currentUserName;
	
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
	 * 用来保存当前的线程
	 */
	private Thread currentThread;
	
	/**
	 * 用来表示是否启用向外部传输数据的功能
	 */
	public boolean outSwing;

	/**
	 * 用来传递线程中的值到外面
	 */
	private ThreadDataTransfer tdt;
	
	/**
	 * 构造方法
	 * @param tdt 中介数据传输类
	 */
	public TcpSocketServer(ThreadDataTransfer tdt)  {
		socketMap=new HashMap<>();
		userNameList=new ArrayList<>();
		threadMap=new HashMap<>();
		socketStreamMap=new HashMap<>();
		
		this.tdt=tdt;
	}
	
	/**
	 * 构造方法
	 * @param textPane 用来显示信息的,主要是测试使用
	 * @param tdt 向外界传递线程内的数据的中介
	 */
	public TcpSocketServer(ThreadDataTransfer tdt,JTextArea textPane)  {
		this(tdt);
		this.textPane=textPane;
	}
	
	/**
	 * 启动服务,接收客户端的连接,多线程的创建
	 * @param port 所使用的端口
	 */
	@Override
	public  void startService(int port)
	{
		try {
			//新建一个serverSocket,可能会出现端口已经被占用的问题
			serverSocket=new ServerSocket(port);
			
			if ( textPane != null )
				textPane.append("正在等待链接\n");
			
			//开始监听并创建新的线程,发送连接成功的信息,处理客户端发来的头信息
			while(true)
			{
				Socket currentSocket=serverSocket.accept();
				currentThread=new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							ss=new SocketStream(currentSocket);
							
							synchronized(this)
							{
								counter++;
							}
							
							//向客户端发送连接成功的消息
							ss.pw.println("连接服务器成功\n");
							ss.pw.flush();
							
							//接收头信息
							String line=ss.br.readLine();
							
							if(textPane != null)
								textPane.append(line+"\n");
							
							handleHeaderInfo(line, currentSocket,
									socketMap, userNameList);
							
							//更新服务器组件的数据
							if(tdt!=null)
								tdt.updateState(counter, userNameList);
							
							
							//获取用户名
							currentUserName=searchUserName(line);
							
							if(textPane != null)
								textPane.append("ueserNameList="+listToString(userNameList)+"\n");
							
							//由用户名保存相应的线程和流
							synchronized(this)
							{	
								socketStreamMap.put(currentUserName, ss);	
								threadMap.put(currentUserName , currentThread);
							}
							
							
							//向客户端发送在线列表的消息
							
							
							//接收信息 测试使用 这个应该放在最后
							try {
								if(textPane != null)
									while((line=ss.br.readLine()) != null)
									{
										textPane.append(line+"\n");
									}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				});
				currentThread.start();//线程必须启动,否则会一直阻塞在那里
							
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 内部的Socket类
	 * @author 林思鑫
	 *
	 */
	private  class SocketStream{
		private BufferedReader br;
		private PrintWriter pw;
		public SocketStream(Socket socket) throws IOException {
			this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw=new PrintWriter(socket.getOutputStream());
		}
		@SuppressWarnings("unused")
		public  void closeStream() throws IOException
		{
			this.br.close();
			this.pw.close();
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
	 * 用来处理头信息,获取客户端的信息,然后将用户信息添加到用户名列表当中
	 * @param line 从输入流中读取的字符串
	 * @param currentSocket 发送信息过来的Socket
	 * @param socketMap 添加目标
	 * @param userNameList 添加目标
	 */
	private void handleHeaderInfo(String line,Socket currentSocket,
			Map<String, Socket> socketMap,ArrayList<String> userNameList)
	{
		//处理头信息
		if(line.substring(0, 6).equals("#head#"))
		{
			//组装socketMap和userList和threadMap
			String userName=searchUserName(line);
			socketMap.put(userName, currentSocket);
			userNameList.add(userName);
		}
		
	}
	
	/**
	 * 停止所有的服务,这个应该给所有的客户端发送退出消息,然后让客户端退出,之后自己在停止socket
	 * @throws IOException serverSocket.close()会产生的错误,交给调用者处理
	 */
	@SuppressWarnings("deprecation")
	synchronized public void stopService() throws IOException
	{
//		if(ss != null)
//			ss.closeStream();
		Iterator<String> it=userNameList.iterator();
		String username;
		while(it.hasNext())
		{
			username=it.next();
			try {
				socketMap.get(username).close();
				threadMap.get(username).stop();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.serverSocket.close();//这个指定的是不再去监听那个端口了
			
		if ( textPane != null )
			textPane.append("服务已关闭\n");
	}
	
	/**
	 * 用来发信息给指定的客户端
	 * @param username 指定发送信息的用户名,用*号代表向所有的在线用户发送
	 * @param message 要发的信息
	 */
	public void sendMessage(String username, String message)
	{
		if (username != "*") {
			SocketStream tempss = socketStreamMap.get(username);
			if (tempss != null) {
				tempss.pw.println(message);
				tempss.pw.flush();
			} 
		}else
		{
			SocketStream tempss;
			Iterator<String> it=userNameList.iterator();
			while( it.hasNext() )
			{
				tempss = socketStreamMap.get( it.next() );
				tempss.pw.println(message);
				tempss.pw.flush();
			}
		}
	}

	@Override
	public void notifyLogin() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLogout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerUpdate() {
		// TODO Auto-generated method stub
		
	}

}

