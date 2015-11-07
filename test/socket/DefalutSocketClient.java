package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import server_client.AsClient;

@Deprecated
public class DefalutSocketClient implements AsClient, MySocketClient{
	protected Socket socket;
	private InputStream in;
	private OutputStream out;
	public DefalutSocketClient() {
		
	}
	public static void main(String [] args) throws IOException
	{
		new DefalutSocketClient().connect("127.0.0.1", 5571);
	}
	@Override
	public Socket  connect(String ip, int port) throws IOException {
		
		this.socket=new Socket(ip, port);
		this.setStream(socket);
		PrintWriter pw=new PrintWriter(out);
		pw.println(getMyIp());
		return socket;
	}
	@Override
	public void close() throws IOException {
		
		socket.close();
	}
	@Override
	public InputStream getInStream() {
		
		return in;
	}
	@Override
	public OutputStream getOutStream() {
		
		return out;
	}
	@Override
	public void closeStream() throws IOException {
		
		in.close();
		out.close();
	}
	@Override
	public void setStream(Socket socket) throws IOException {
		
		in=socket.getInputStream();
		out=socket.getOutputStream();
	}
	@Override
	public Socket createConnection(int port) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String sendDestinationHeader(MySocketClient client) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getMyIp() {
		String info="ip="+this.socket.getInetAddress().getHostAddress()+";";
		return info;
	}
}
