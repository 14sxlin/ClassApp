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

	private ArrayList<Socket> OnlineSocketList;//ͨ��Socket�Ϳ���ָ����ѯ��Ӧ����Ϣ��
    private SocketStream ss;
    private ServerSocket serverSocket;
	public MockServer(JFrame host) throws IOException {
		
		super(host);
		super.setTitle("������");
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
		
//����ӵ�createConnection��������ȥ
		
		//�½�һ��serverSocket,���ܻ���ֶ˿��Ѿ���ռ�õ�����
		serverSocket = new ServerSocket(MockPort.PORT);
System.out.println("���ڵȴ�����");
		
//����ӵ�waitConnect(ArrayList<Socket>socket)������

		//��������֮��,�˿����½���ȴ�״̬
		while(true)
		{
			Socket temp=serverSocket.accept();
			OnlineSocketList.add(temp);
			ss=new SocketStream(temp);
			ss.pw.println("���ӷ������ɹ�");
			ss.pw.flush();//�ǵ�����Ҫflush,Ҫ��Ȼ�ͻ��˽��ղ�����Ϣ
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
	
	
	//�ڲ���SocketStream
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
