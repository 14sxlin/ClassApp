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
 * 林思鑫
 * 即作为客户端,也作为服务端,可以接收别人的请求
 */
public class MockPublicChatRoom_Server extends GuiForPublicChatRoom {

	private static final long serialVersionUID = 1L;
	
	private InputStream in;
	private OutputStream out;
	private Socket socket;
	public MockPublicChatRoom_Server(JFrame host) throws IOException {
		super(host);
		super.setTitle("服务端");		
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
	//初始化的方法,规定了先连接然后获得流
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
		pr.println("连接成功");
		jTextArea.append("连接成功");
	}
	public void close() throws IOException {
		// TODO Auto-generated method stub
		closeStream();
		this.socket.close();
	}

}
