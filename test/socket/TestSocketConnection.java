package socket;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;
@Deprecated
public class TestSocketConnection {
	
	public class MockServer extends Thread implements MySocketServer
	{
		private Socket socket;
		public MockServer() {
			// TODO Auto-generated constructor stub
		}

		@SuppressWarnings("resource")
		@Override
		public void createConnection(int port) {
			// TODO Auto-generated method stub
			try {
System.out.println("socket are creating!");
				this.socket=new ServerSocket(port).accept();
System.out.println("socket has created!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void run()
		{
			createConnection(port);
		}

		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class MockClient extends Thread implements MySocketClient
	{
		public Socket socket;

		public MockClient() {
		}

		@Override
		public void connect(String ip, int port) throws IOException {
			// TODO Auto-generated method stub
System.out.println("socket are connecting!");
			this.socket=new Socket(ip,port);
System.out.println("connected");
		}

		@Override
		public void close() throws IOException {
			
		}
		
		public void run()
		{
			try {
				connect("127.0.0.1", port);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private MockClient client;
	private MockServer server;
	private final int port=33768;
	
	@Test
//	(timeout=500)
	public void testConnection() throws IOException
	{
		server=new MockServer();
		server.run();
		client=new MockClient();	
		client.run();

		
		assertNotNull("server.socket must not null",server.socket);
		assertNotNull("client.socket must not null",client.socket);
	}
}
