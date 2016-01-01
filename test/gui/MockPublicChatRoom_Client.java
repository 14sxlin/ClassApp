package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFrame;

import gui.pubChatRoom.client.GuiForPublicChatRoom;


@SuppressWarnings("serial")
public class MockPublicChatRoom_Client extends GuiForPublicChatRoom  {

	private InputStream in;
	private OutputStream out;
	public Socket socket;
	public MockPublicChatRoom_Client(JFrame host) {
			// TODO Auto-generated constructor stub
		super("");
		this.setTitle("客户端");
		try {
			connect("127.0.0.1", 55890);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Deprecated
	//init规定了执行的顺序
	public void init(String ip,int port)
	{
		try {
			connect(ip, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(socket!=null)
			try {
				this.setStream(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else throw new NullPointerException("无法连接到客户端");
		
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
		try {
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setStream(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		in=socket.getInputStream();
		out=socket.getOutputStream();
	}

	public void connect(String ip, int port) throws IOException {
		 this.socket=new Socket(ip, port);
		 this.setStream(socket);
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub
		closeStream();
		this.socket.close();
	}
	
	public void receiveHello() throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String line="";
		while((line=br.readLine())!=null)
		{
			jTextArea.append(line);
		}
	}
	
	public static void main(String args[])
	{
		try {
			MockPublicChatRoom_Client c=new MockPublicChatRoom_Client(null);
			c.receiveHello();
			c.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
