package manager;

import java.io.IOException;
import java.net.Socket;

import object.SocketStream;

/**
 * 这个类是用来代表连接进来的客户端的类
 * @author 林思鑫
 *
 */
public class Client {
	
	/**
	 * 用来标志客户端的用户名
	 */
	private String userName;
	
	/**
	 * 客户端的ip
	 */
	private String ip;
	
	/**
	 * 客户端的Socket
	 */
	private Socket socket;
	
	/**
	 * 用来保存对这个客户端的通信的流
	 */
	private SocketStream ss;
	
	
	public void setUserName(String username)
	{
		this.userName=username;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getIp() {
		return ip;
	}

	public Socket getSocket() {
		return socket;
	}

	public SocketStream getSocketStream() {
		return ss;
	}

	/**
	 * 传入socket的构造方法
	 * @param socket 指定客户端的socket
	 * @throws IOException
	 */
	public Client(Socket socket) throws IOException {
		
		this.socket=socket;
		ss=new SocketStream(socket);
	}

}
