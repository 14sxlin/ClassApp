package object_client_server;

import java.io.IOException;
import java.net.Socket;

public class Server {
	
	/**
	 * ���������������������
	 */
	private SocketStream ss;
	
	/**
	 * ��������socket
	 */
	private Socket socket;
	
	public SocketStream getSocketStream() {
		return ss;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * ָ��socket�Ĺ��췽��
	 * @param socket ��������socket
	 * @throws IOException
	 */
	public Server(Socket socket) throws IOException {
		this.socket=socket;
		ss=new SocketStream(socket);
	}
	
}
