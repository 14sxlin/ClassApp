package gui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import gui.client.GuiForPublicChatRoom;


/*
 * ��˼��
 * ����Ϊ�ͻ���,Ҳ��Ϊ�����,���Խ��ձ��˵�����
 */
public class MockPublicChatRoom_Server extends GuiForPublicChatRoom {

	private static final long serialVersionUID = 1L;
	
	private InputStream in;
	private OutputStream out;
	private Socket socket;
	public MockPublicChatRoom_Server(JFrame host) throws IOException {
		super(host);
		super.setTitle("�����");		
		this.createConnection(55890);
	}

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		try {
			MockPublicChatRoom_Server ser=new MockPublicChatRoom_Server(null);
			ser.sendHello();
			ser.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Deprecated
	//��ʼ���ķ���,�涨��������Ȼ������
	public void init(int port) throws IOException
	{
		createConnection(port);
		try {
			this.setStream(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public InputStream getInStream() {
		// TODO Auto-generated method stub
		return in;
	}

	public OutputStream getOutStream() {
		// TODO Auto-generated method stub
		return out;
	}

	public void closeStream() throws IOException {
		// TODO Auto-generated method stub
		in.close();
		out.close();
	}

	public void setStream(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		in=socket.getInputStream();
		out=socket.getOutputStream();
	}

	@SuppressWarnings("resource")
	public void createConnection(int port) throws IOException {
		Socket socket=new ServerSocket(port).accept();
		this.setStream(socket);
		this.socket=socket;
	}

	public void sendHello()
	{
		PrintWriter pr=new PrintWriter(out, true);
		pr.println("���ӳɹ�");
		jTextArea.append("���ӳɹ�");
	}
	public void close() throws IOException {
		// TODO Auto-generated method stub
		closeStream();
		this.socket.close();
	}

}
