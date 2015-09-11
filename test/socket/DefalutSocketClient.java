package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DefalutSocketClient implements MySocketClient,MySocketStream{
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
		// TODO Auto-generated method stub
		
	}
}
