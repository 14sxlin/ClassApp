package api.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.pubChatRoom.server.GuiForServer;
import headInfoProcesser.factory.ProcesserFactory;
import headInfoProcesser.processer.HeadInfoProcesser;
import headInfoProcesser.processer.LoginProcesser;
import headinfoFilter.HeadInfoFilter;
import object.Client;
import threadData.ThreadDataTransfer;
import tools.ClientsManager;

/**
 * 服务器的Socket服务
 * @author 林思鑫
 *
 */
public class ServerLogic implements AsServer{
	
	/**
	 * 服务器的ServerSocket
	 */
	private ServerSocket serverSocket;

	/**
	 * 用来与图形界面交互
	 */
	private GuiForServer gui;
	
	/**
	 * 用来传递线程中的值到外面
	 */
	private ThreadDataTransfer tdt;
	
	/**
	 * 用来检查并过滤头信息
	 */
	private HeadInfoFilter filter;
	
	/**
	 * 用来产生相应的过滤器
	 */
	private ProcesserFactory factory;
	
//	/**
//	 * 构造方法
//	 * @param tdt 中介数据传输类
//	 */
//	public ServerForPubChatRoom(ThreadDataTransfer tdt)  {
//		this.tdt=tdt;
//	}
	
	/**
	 * 构造方法
	 * @param textPane 用来显示信息的,主要是测试使用
	 * @param tdt 向外界传递线程内的数据的中介
	 */
	public ServerLogic(GuiForServer gui)  {
		this.gui = gui;
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
			
			if ( gui.textPane != null )
				gui.textPane.append("正在等待链接\n");
			
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
						e2.printStackTrace();
					}

					
					//向客户端发送连接成功的消息
					client.getSocketStream().getPrintWriter().println("连接服务器成功\n");
					client.getSocketStream().getPrintWriter().flush();
					
					//接收客户端发送过来的头信息
					//认为第一次发过来的信息就是头信息,包含了用户名
					String line = null;
					try {
						line = client.getSocketStream().getBufferReader().readLine();
					
						if(gui.textPane != null)
							gui.textPane.append(line+"\n");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					//获取用户名
					client.setUserName(searchUserName(line));
					
					synchronized (ClientsManager.class) {
						try {
							new LoginProcesser(ClientsManager.clientList).process(client);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						//更新服务器组件的数据
						if (tdt != null)
							tdt.updateState(ClientsManager.clientList.size(), ClientsManager.userNameList);
						if (gui.textPane != null)
							gui.textPane.append("userNameList=" +ClientsManager.userNameList.toString()  + "\n");
					}
					
					//接收客户端的信息 这里面要处理客户端可能发过来的头信息
					if(gui.textPane != null)
						try {
							while((line=client.getSocketStream().getBufferReader().readLine()) != null)
							{
								filter = new HeadInfoFilter(line);
								if(filter.isHeadInfo())
								{
									try {
										factory = new ProcesserFactory(ClientsManager.clientList);
										HeadInfoProcesser processer ;
										processer=factory.createProcesser(filter.filteType());	
										if(processer.getType() == HeadInfoProcesser.STRING)
											processer.process(filter.filteContent());
										else processer.process(client);
									} catch (Exception e) {
										e.printStackTrace();
									}
//									gui.textPane.append(line+"\n");
								}
								else
								{
//									gui.textPane.append(line+"\n");
									ClientsManager.sendAllClient(line);
								}
								
							}
						} catch (IOException e) {
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
		int i=line.indexOf("content=");
		int length="content=".length();
		if(i!=-1)
			return line.substring(i+length,line.indexOf("#",length));
		else
			return "";
	}

	
	
	/**
	 * 停止所有的服务,这个应该给所有的客户端发送退出消息,然后让客户端退出,之后自己在停止socket
	 * @throws IOException serverSocket.close()会产生的错误,交给调用者处理
	 */
	public void stopService() throws IOException
	{
		//流的关闭有点复杂 这是因为流的关闭是两方面的 需要好好研究一下
//		Iterator<Client> it = clientList.iterator();
//		while(it.hasNext())
//		{
//			it.next().getSocketStream().closeStream();
//		}
//		
		if( this.serverSocket != null)
		{
			this.serverSocket.close();//这个指定的是不再去监听那个端口了
		}
		
	}

	@Override
	public void registerUpdate() {
		// TODO Auto-generated method stub
		
	}
	

}

