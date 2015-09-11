package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DefalutSocketClient implements MySocketClient{
	protected Socket socket;
	private InputStream in;
	private OutputStream out;
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
		socket.close();
	}
	@Override
	public InputStream getInStream() {
		// TODO Auto-generated method stub
		return in;
	}
	@Override
	public OutputStream getOutStream() {
		// TODO Auto-generated method stub
		return out;
	}
	@Override
	public void closeStream() throws IOException {
		// TODO Auto-generated method stub
		in.close();
		out.close();
	}
	@Override
	public void setStream(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		in=socket.getInputStream();
		out=socket.getOutputStream();
	}
}
