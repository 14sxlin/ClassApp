package server_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

/**
 * 客户端的socket管理
 * @author 林思鑫
 *
 */
public class TcpSocketClient {
	/**
	 * 客户端用户的名称,用来标志客户端
	 */
	private String userName;
	
	/**
	 * 与服务器连接的stream
	 */
	private SocketStream ss; 
	
	
	/**
	 * 与服务器连接的socket
	 */
	private Socket serverSocket;
	
	/**
	 * 用来与外界交互的变量
	 */
	private JTextArea jTextArea;
	
	/**
	 * 构造方法
	 * @param userName 传入客户端的用户名
	 */
	public TcpSocketClient(String userName) {
		this.userName=userName;
	}
	
	/**
	 * 构造方法
	 * @param userName 用户名
	 * @param jTextArea 外部的变量
	 */
	public TcpSocketClient(String userName,JTextArea jTextArea) {
		this(userName);
		this.jTextArea=jTextArea;
	}
	
	/**
	 * 开始连接服务器,发送头信息到输出流给服务器
	 * @param serverIp 服务器的ip地址
	 * @param serverPort 服务器的服务端口
	 * @throws IOException 抛出异常让调用它的类处理
	 */
	public void startConnectServer(String serverIp,int serverPort) throws IOException
	{
		try {
			//尝试连接服务器
			this.serverSocket=new Socket(serverIp, serverPort);
			ss=new SocketStream(serverSocket);
			
			//发送头信息
			sendHeaderInfo(ss.pw);
		
			//读取服务器的信息,测试使用
			String line;
			while( (line=ss.br.readLine()) != null)
			{
				this.jTextArea.append(line+"\n");
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * 内部socketStream类,用于获取socket的流
	 */
	public class SocketStream{
		private BufferedReader br;
		private PrintWriter pw;
		
		public SocketStream(Socket socket) throws IOException {
			this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw=new PrintWriter(socket.getOutputStream());
		}
		
		@SuppressWarnings("unused")
		private void closeStream() throws IOException
		{
			if(br!=null)
				this.br.close();
			if(pw!=null)
				this.pw.close();
		}
	}
	
	/**
	 * 发送头信息给服务器
	 * @param pw 指定服务器的输出流
	 */
	private void sendHeaderInfo(PrintWriter pw)
	{
		//目前只有两个信息
		pw.println("#head#"+"UserName="+this.userName
				+"&Ip="+this.serverSocket.getInetAddress().getHostAddress());
		pw.flush();
	}

	
	/**
	 * 给外面用的接口,接收从远方发来的信息
	 * @return 返回这个信息
	 * @throws IOException 给调用者去处理
	 */
	public String recevieMessage() throws IOException
	{
		return ss.br.readLine();
	}
	
	/**
	 * 暴露给外面的接口,用于发送信息到服务器
	 * @param message 要发送的消息
	 */
	public void sendMessage(String message)
	{
		ss.pw.println(message);
		ss.pw.flush();
	}
}
