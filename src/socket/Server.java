package socket;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

public class Server {
	/*
	 * ��˼��
	 * �������ձ��˵�����
	 * ���仰˵,�Ǵ�������
	 */
	private ServerSocket serverSocket;
	public Server(int port) {//ָ��һ���˿�
		try {
			serverSocket=new ServerSocket(port);
System.out.println("��ǰ�����Ķ˿���:"+serverSocket.getLocalPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "����serverSocketʧ��");
		}
		try {
			serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "���տͻ�������ʧ��");
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server(55677);
	}

}
