package socket;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

public class Server {
	/*
	 * 林思鑫
	 * 用来接收别人的连接
	 * 换句话说,是创建连接
	 */
	private ServerSocket serverSocket;
	public Server(int port) {//指定一个端口
		try {
			serverSocket=new ServerSocket(port);
System.out.println("当前监听的端口是:"+serverSocket.getLocalPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "创建serverSocket失败");
		}
		try {
			serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "接收客户端连接失败");
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server(55677);
	}

}
