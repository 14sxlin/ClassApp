package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class DefaultSocketServer implements  MySocketServer{
	/*
	 * ��˼��
	 * �������ձ��˵�����
	 * ���仰˵,�Ǵ�������,�̳���SocketServer�ӿ�
	 * Ȼ����SocketStream���������������
	 */
	private ServerSocket serverSocket;
	
	public DefaultSocketServer() {//���췽��ָ��һ���˿�

	}
	
	public Socket createConnection(int port)
	{
		try {
			serverSocket=new ServerSocket(port);
System.out.println("��ǰ�����Ķ˿���:"+serverSocket.getLocalPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "����serverSocketʧ��");
		}
		try {
			return serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "���տͻ�������ʧ��");
			return null;
		}
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[])
	{
		new DefaultSocketServer().createConnection(5566);
	}

	@Override
	public InputStream getInStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream getOutStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeStream() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStream(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		
	}

	
}
