package server_client;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;

import gui.PublicChatRoom;
import socket.MySocketClient;

@SuppressWarnings("serial")
public class MockClient extends PublicChatRoom implements AsClient {

	private Socket socketToServer;
	@SuppressWarnings("unused")
	private Socket socketToOther;
	private int serverPort;
	public MockClient(JFrame host) throws IOException {
		super(host);
		this.setTitle("客户端");
		this.setResizable(false);
		
		//创建自己的ServerSocket
		this.socketToOther=this.createConnection(serverPort);
		super.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					closeStream();
					socketToServer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		try {
			socketToServer=new Socket(MockPort.LOCAL_IP, MockPort.PORT);
			SocketStream ss=new SocketStream(socketToServer);
			ss.pw.println("UserName="+Math.random()*100%10
					+";ServerPort="+serverPort+";");
			ss.pw.flush();
		    ss.closeStream();
			String line=null;
			while((line=ss.br.readLine())!=null||true)
			{
				super.jTextArea.append(line);
			}
		} catch (SocketException e1) {
			this.jTextArea.append("服务器关闭");
		}
	}

	@Override
	public Socket connect(String ip, int port) throws IOException {
		return new Socket(ip, port);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

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
		//maybe don't need this class
	}

	@Override
	public Socket createConnection(int port) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket temp=new ServerSocket(port);
		return temp.accept();
	}

	@Override
	public String sendDestinationHeader(MySocketClient client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMyIp() {
		return this.socketToServer.getInetAddress().getHostAddress();
	}

	
	//内部类SocketStream
	private class SocketStream{
		private BufferedReader br;
		private PrintWriter pw;
		public SocketStream(Socket socket) throws IOException {
			this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw=new PrintWriter(socket.getOutputStream());
		}
		private void closeStream() throws IOException
		{
			this.br.close();
			this.pw.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			new MockClient(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
