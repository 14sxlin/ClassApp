package server_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �ͻ��˵�socket����
 * @author ��˼��
 *
 */
public class TcpSocketClient {
	/**
	 * �ͻ����û�������,������־�ͻ���
	 */
	private String userName;
	
	/**
	 * ����������ӵ�stream
	 */
	private SocketStream ss; 
	
	/**
	 * ָ����������IP
	 */
	private String serverIp;
	
	/**
	 * ָ���������Ķ˿�
	 */
	private int serverPort;
	
	/**
	 * ����������ӵ�socket
	 */
	private Socket clientSocket;
	
	/**
	 * ���췽��
	 * @param userName ����ͻ��˵��û���
	 * @param serverIp	�����������IP��ַ
	 */
	public TcpSocketClient(String userName,String serverIp,int serverPort) {
		this.userName=userName;
		this.serverIp=serverIp;
		this.serverPort=serverPort;
	}
	
	/**
	 * ��ʼ���ӷ�����
	 * @throws IOException �׳��쳣�õ��������ദ��
	 */
	public void startConnectServer() throws IOException
	{
		try {
			//�������ӷ�����
			this.clientSocket=new Socket(this.serverIp, this.serverPort);
			ss=new SocketStream(clientSocket);
			
			//����ͷ��Ϣ
			sendHeaderInfo(ss.pw);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * �ڲ�socketStream��,���ڻ�ȡsocket����
	 */
	private class SocketStream{
		private BufferedReader br;
		private PrintWriter pw;
		public SocketStream(Socket socket) throws IOException {
			this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw=new PrintWriter(socket.getOutputStream());
		}
		@SuppressWarnings("unused")
		private void closeStream() throws IOException
		{
			if(br!=null)
				this.br.close();
			if(pw!=null)
				this.pw.close();
		}
	}
	
	/**
	 * ����ͷ��Ϣ��������
	 * @param pw ָ���������������
	 */
	private void sendHeaderInfo(PrintWriter pw)
	{
		//Ŀǰֻ��������Ϣ
		pw.println("#head#"+"UserName="+this.userName
				+"&Ip="+this.clientSocket.getInetAddress().getHostAddress());
		pw.flush();
	}
}
