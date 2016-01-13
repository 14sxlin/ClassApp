package manager;

import java.io.IOException;
import java.net.Socket;

import object.SocketStream;

/**
 * ������������������ӽ����Ŀͻ��˵���
 * @author ��˼��
 *
 */
public class Client {
	
	/**
	 * ������־�ͻ��˵��û���
	 */
	private String userName;
	
	/**
	 * �ͻ��˵�ip
	 */
	private String ip;
	
	/**
	 * �ͻ��˵�Socket
	 */
	private Socket socket;
	
	/**
	 * �������������ͻ��˵�ͨ�ŵ���
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
	 * ����socket�Ĺ��췽��
	 * @param socket ָ���ͻ��˵�socket
	 * @throws IOException
	 */
	public Client(Socket socket) throws IOException {
		
		this.socket=socket;
		ss=new SocketStream(socket);
	}

}
