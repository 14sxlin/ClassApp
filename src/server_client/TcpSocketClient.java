package server_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
	 * 指定服务器的IP
	 */
	private String serverIp;
	
	/**
	 * 指定服务器的端口
	 */
	private int serverPort;
	
	/**
	 * 与服务器连接的socket
	 */
	private Socket clientSocket;
	
	/**
	 * 构造方法
	 * @param userName 传入客户端的用户名
	 * @param serverIp	传入服务器的IP地址
	 */
	public TcpSocketClient(String userName,String serverIp,int serverPort) {
		this.userName=userName;
		this.serverIp=serverIp;
		this.serverPort=serverPort;
	}
	
	/**
	 * 开始连接服务器
	 * @throws IOException 抛出异常让调用它的类处理
	 */
	public void startConnectServer() throws IOException
	{
		try {
			//尝试连接服务器
			this.clientSocket=new Socket(this.serverIp, this.serverPort);
			ss=new SocketStream(clientSocket);
			
			//发送头信息
			sendHeaderInfo(ss.pw);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * 内部socketStream类,用于获取socket的流
	 */
	private class SocketStream{
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
				+"&Ip="+this.clientSocket.getInetAddress().getHostAddress());
		pw.flush();
	}
}
