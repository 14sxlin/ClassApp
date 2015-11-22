package api.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import object_client_server.Server;

/**
 * 客户端的socket管理
 * @author 林思鑫
 *
 */
public class ClientForPubChatRoom implements AsClient {
	/**
	 * 客户端用户的名称,用来标志客户端
	 */
	private String userName;
	
	/**
	 * 服务器对象
	 */
	private Server server;
	
	/**
	 * 用来与外界交互的变量
	 */
	private JTextArea jTextArea;
	
	/**
	 * 构造方法
	 * @param userName 传入客户端的用户名
	 */
	public ClientForPubChatRoom(String userName) {
		this.userName=userName;
	}
	
	/**
	 * 构造方法
	 * @param userName 用户名
	 * @param jTextArea 外部的变量
	 */
	public ClientForPubChatRoom(String userName,JTextArea jTextArea) {
		this(userName);
		this.jTextArea=jTextArea;
	}
	
	/**
	 * 开始连接服务器,发送头信息到输出流给服务器
	 * @param serverIp 服务器的ip地址
	 * @param serverPort 服务器的服务端口
	 * @throws IOException 抛出异常让调用它的类处理
	 */
	@Override
	public void startConnectServer(String serverIp,int serverPort) throws IOException
	{
		try {
			//尝试连接服务器
			server=new Server( new Socket(serverIp, serverPort));
			
			//发送头信息
			sendHeaderInfo(server.getSocketStream().getPrintWriter());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 发送头信息给服务器
	 * @param pw 指定服务器的输出流
	 */
	@Override
	public void sendHeaderInfo(PrintWriter pw)
	{
		//目前只有两个信息
		pw.println("#head#"+"UserName="+this.userName
				+"&Ip="+this.server.getSocket().getInetAddress().getHostAddress());
		pw.flush();
	}

	/**
	 * 给服务器发送消息
	 * @param message 要发送的消息
	 */
	@Override
	public void sendMessageToServer(String message)
	{
		if(server.getSocketStream() != null)
		{
			PrintWriter pw=server.getSocketStream().getPrintWriter();
			pw.println(message);
			pw.flush();
		}
	}


	/**
	 * 接收服务器发送过来的消息
	 * @param storeString 用来储存接收到的消息
	 */
	@Override
	public void receiveMessageFromServer(StringBuilder storeString) throws IOException {
		
		if (server.getSocketStream()!=null) {
			String line;
			while ((line = server.getSocketStream().getBufferReader().readLine()) != null) {
				storeString.append(line + "\n");
				this.jTextArea.append(line+"\n");
			} 
		}
	}

	
	@Override
	public void sendLogoutMessage() {
		this.sendMessageToServer("#head:logout?username="+this.userName);
	}
}
