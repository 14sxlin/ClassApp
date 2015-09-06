package socket;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;


public class TestSocketSendRecevie {
	private MockSocketClinet mockClient;
	private MockSocketServer mockServer;
	
	//内部mock类,都实现了流MySocketStream 不用一个MockStream类
	public class MockSocketClinet implements MySocketClient,MySocketStream
	{
		public int count=0;
		private InputStream in;
		private OutputStream out;
		public  Socket socket;
		public MockSocketClinet() {

		}
		public void connect(String ip, int port) throws IOException {
			// TODO Auto-generated method stub
			this.socket= new Socket(ip,port);
		}
		@Override
		public InputStream getInStream() {
			// TODO Auto-generated method stub
			return this.in;
		}
		@Override
		public OutputStream getOutStream() {
			// TODO Auto-generated method stub
			return this.out;
		}
		@Override
		public void closeStream() throws IOException {
			// TODO Auto-generated method stub
			in.close();
			out.close();
			count++;
		}
		@Override
		public void setStream(Socket socket) throws IOException {
			// TODO Auto-generated method stub
			if(socket!=null)
			{	this.in=socket.getInputStream();
				this.out=socket.getOutputStream();
			}else throw new NullPointerException("Socket is null!");
		}
		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub
			this.socket.close();
		}
	}
	
	public class MockSocketServer implements MySocketServer,MySocketStream
	{
		public int count=0;
		private InputStream in;
		private OutputStream out;
		public Socket socket;
		public MockSocketServer()
		 {
		}
		 

		@Override
		public InputStream getInStream() {
			// TODO Auto-generated method stub
			return this.in;
		}
		
		@Override
		public OutputStream getOutStream() {
			// TODO Auto-generated method stub
			return this.out;
		}
		
		public void closeStream() throws IOException {
			// TODO Auto-generated method stub
			in.close();
			out.close();
			count++;
		}

		@Override
		public void setStream(Socket socket) throws IOException {
			// TODO Auto-generated method stub
		}

		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub
			this.socket.close();
		}


		@SuppressWarnings("resource")
		@Override
		public void createConnection(int port) {
			// TODO Auto-generated method stub
			try {
				this.socket=new ServerSocket(port).accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Before
	public void setUp()
	{
//		final int port=5566;
		//连接两个socket
		mockServer=new MockSocketServer();
		mockClient=new MockSocketClinet();
		try {
			//以本机作为测试
			mockServer.setStream(mockServer.socket);	
			mockClient.setStream(mockClient.socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test(timeout=1000)
	public void testServerSend_ClinetReceiveOK() throws IOException {		
		PrintWriter pr=new PrintWriter(mockServer.getOutStream());
		pr.write("it works");
		BufferedReader br=new BufferedReader(new InputStreamReader(mockClient.getInStream()));
		String line=br.readLine();
		mockClient.closeStream();
		mockServer.closeStream();
		
		assertEquals(1, mockClient.count);
		assertEquals(1, mockServer.count);
		assertEquals(line,"it works");
		
	}
	
	@Test(timeout=1000)
	public void testClientSend_ServerReceiveOK() throws IOException {	
		PrintWriter pr=new PrintWriter(mockClient.getOutStream());
		pr.write("it works");
		BufferedReader br=new BufferedReader(new InputStreamReader(mockServer.getInStream()));
		String line=br.readLine();
		mockClient.closeStream();
		mockServer.closeStream();
		
		assertEquals(1, mockClient.count);
		assertEquals(1, mockServer.count);
		assertEquals(line,"it works");
	}

}
