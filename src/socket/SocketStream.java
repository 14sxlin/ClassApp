package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class SocketStream {
	/*
	 * ��˼��
	 * ������ȡ����������
	 */
	public OutputStream out;
	public InputStream in;
	public  Socket socket;
	public SocketStream(Socket socket) {//���socket����Ҫ���ӵ�socket,�ù��췽��socket(String ip ,int port)
		try {
			this.socket=socket;
			in=this.socket.getInputStream();
			out=this.socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "�����û�д�");
			e.printStackTrace();
		}
System.out.println("���ӳɹ�!");
		
	}
	public SocketStream(String host,int port)
	{
		try {
			new SocketStream(new Socket(host, port));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "�����û�д�");
			e.printStackTrace();
		}
	}
	
	//�ر����Ӻ���
	public void disposeLink() throws IOException
	{
//		in.close();
//		out.close();
//		socket.close();
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		SocketStream test=new SocketStream(Path.TESTPATH	, 55677);
		test.disposeLink();
	}

}
