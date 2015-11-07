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
import java.util.ArrayList;

import javax.swing.JFrame;

import gui.PublicChatRoom;
import socket.MySocketClient;

@SuppressWarnings("serial")
public class MockServer extends PublicChatRoom implements AsServer {

	private ArrayList<Socket> OnlineSocketList;//通过Socket就可以指定查询相应的信息了
    private SocketStream ss;
    private ServerSocket serverSocket;
	public MockServer(JFrame host) throws IOException {
		
		super(host);
		super.setTitle("服务器");
		this.setResizable(false);
		this.addWindowListener(new WindowListener() {
			
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
//				Iterator<Socket> it=OnlineSocketList.iterator();
//				while(it.hasNext())
//					try {
//						Socket temp=it.next();
//						temp.getInputStream().close();
//						temp.getOutputStream().close();
//						temp.close();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
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
		OnlineSocketList=new ArrayList<>();
		
//下面加到createConnection方法里面去
		
		//新建一个serverSocket,可能会出现端口已经被占用的问题
		serverSocket = new ServerSocket(MockPort.PORT);
System.out.println("正在等待连接");
		
//这里加到waitConnect(ArrayList<Socket>socket)方法中

		//建立连接之后,端口重新进入等待状态
		while(true)
		{
			Socket temp=serverSocket.accept();
			OnlineSocketList.add(temp);
			ss=new SocketStream(temp);
			ss.pw.println("连接服务器成功");
			ss.pw.flush();//记得这里要flush,要不然客户端接收不到消息
			this.jTextArea.append(ss.br.readLine()+"\n");
			ss.closeStream();
		}
	}

	@Override
	public Socket createConnection(int port) {
		// TODO Auto-generated method stub
		return null;
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
		ss.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		ss.pw=new PrintWriter(socket.getOutputStream());
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
	

	@SuppressWarnings("unused")
	private void receiveMessage() throws IOException
	{
		String line;
		while((line=ss.br.readLine())!=null||true)
		{
			super.jTextArea.append(line);
		}
		
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
			new MockServer(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
