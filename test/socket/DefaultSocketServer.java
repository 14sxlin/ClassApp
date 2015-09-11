package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class DefaultSocketServer implements  MySocketServer{
	/*
	 * 林思鑫
	 * 用来接收别人的连接
	 * 换句话说,是创建连接,继承了SocketServer接口
	 * 然后由SocketStream来返回输入输出流
	 */
	private ServerSocket serverSocket;
	private InputStream in;
	private OutputStream out;
	public DefaultSocketServer() {//构造方法指定一个端口

	}
	
	public Socket createConnection(int port)
	{
		try {
			serverSocket=new ServerSocket(port);
System.out.println("当前监听的端口是:"+serverSocket.getLocalPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "创建serverSocket失败");
		}
		try {
			return serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "接收客户端连接失败");
			return null;
		}
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[])
	{
		new DefaultSocketServer().createConnection(5566);
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
