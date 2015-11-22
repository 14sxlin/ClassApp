package object_client_server;

import java.io.IOException;
import java.net.Socket;

public class Server {
	
	/**
	 * 用来保存服务器的流对象
	 */
	private SocketStream ss;
	
	/**
	 * 服务器的socket
	 */
	private Socket socket;
	
	public SocketStream getSocketStream() {
		return ss;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * 指定socket的构造方法
	 * @param socket 传入服务的socket
	 * @throws IOException
	 */
	public Server(Socket socket) throws IOException {
		this.socket=socket;
		ss=new SocketStream(socket);
	}
	
}
