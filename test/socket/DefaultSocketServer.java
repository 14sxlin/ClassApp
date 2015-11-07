package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import server_client.AsServer;

@Deprecated
public class DefaultSocketServer implements  AsServer, MySocketServer,Runnable{
	/*
	 * ��˼��
	 * �������ձ��˵�����
	 * ���仰˵,�Ǵ�������,�̳���SocketServer�ӿ�
	 * Ȼ����SocketStream���������������
	 */
	private ServerSocket serverSocket;
	private InputStream in;
	private OutputStream out;
	public DefaultSocketServer() {//���췽��ָ��һ���˿�
	}
	
	public Socket createConnection(int port)
	{
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("��ǰ�����Ķ˿���:" + serverSocket.getLocalPort());
			return serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) throws IOException
	{
		
		new DefaultSocketServer().run();
	}

	@Override
	public InputStream getInStream() {
		// TODO Auto-generated method stub
		return in;
	}

	@Override
	public OutputStream getOutStream() {
		// TODO Auto-generated method stub
		return out;
	}

	@Override
	public void closeStream() throws IOException {
		// TODO Auto-generated method stub
		in.close();
		out.close();
	}

	@Override
	public void setStream(Socket socket) throws IOException {
		in=socket.getInputStream();
		out=socket.getOutputStream();
	}

	@Override
	public void sendMessage(MySocketClient c1, MySocketClient c2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewClient() throws IOException {
		// TODO Auto-generated method stub
	}
	
	public void printList()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		DefaultSocketServer server=new DefaultSocketServer();
		while(true)
		{
			server.createConnection(5571);
			try {
				server.addNewClient();
			} catch (IOException e) {
				e.printStackTrace();
			}
			server.printList();
		}
	}

	
}
