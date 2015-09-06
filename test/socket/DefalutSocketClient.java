package socket;

import java.io.IOException;
import java.net.Socket;

public class DefalutSocketClient implements MySocketClient{
	protected Socket socket;
	public DefalutSocketClient() {
		
	}
	public static void main(String [] args) throws IOException
	{
		new DefalutSocketClient().connect("127.0.0.1", 5566);
	}
	@Override
	public void  connect(String ip, int port) throws IOException {
		// TODO Auto-generated method stub
		this.socket=new Socket(ip, port);
	}
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
}
