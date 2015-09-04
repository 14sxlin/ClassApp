package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class SocketStream {
	/*
	 * 林思鑫
	 * 用来获取输入和输出流
	 */
	public OutputStream out;
	public InputStream in;
	public  Socket socket;
	public SocketStream(Socket socket) {//这个socket是想要连接的socket,用构造方法socket(String ip ,int port)
		try {
			this.socket=socket;
			in=this.socket.getInputStream();
			out=this.socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "服务端没有打开");
			e.printStackTrace();
		}
System.out.println("连接成功!");
		
	}
	public SocketStream(String host,int port)
	{
		try {
			new SocketStream(new Socket(host, port));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "服务端没有打开");
			e.printStackTrace();
		}
	}
	
	//关闭连接和流
	public void disposeLink() throws IOException
	{
//		in.close();
//		out.close();
//		socket.close();
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		SocketStream test=new SocketStream(Path.TESTPATH	, 55677);
		test.disposeLink();
	}

}
