package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class DefaultSocketServer {
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
	
	public static void main(String args[])
	{
		new DefaultSocketServer().createConnection(5566);
	}
}
