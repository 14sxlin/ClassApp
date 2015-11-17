package server_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

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
	 * ����������ӵ�socket
	 */
	private Socket serverSocket;
	
	/**
	 * ��������罻���ı���
	 */
	private JTextArea jTextArea;
	
	/**
	 * ���췽��
	 * @param userName ����ͻ��˵��û���
	 */
	public TcpSocketClient(String userName) {
		this.userName=userName;
	}
	
	/**
	 * ���췽��
	 * @param userName �û���
	 * @param jTextArea �ⲿ�ı���
	 */
	public TcpSocketClient(String userName,JTextArea jTextArea) {
		this(userName);
		this.jTextArea=jTextArea;
	}
	
	/**
	 * ��ʼ���ӷ�����,����ͷ��Ϣ���������������
	 * @param serverIp ��������ip��ַ
	 * @param serverPort �������ķ���˿�
	 * @throws IOException �׳��쳣�õ��������ദ��
	 */
	public void startConnectServer(String serverIp,int serverPort) throws IOException
	{
		try {
			//�������ӷ�����
			this.serverSocket=new Socket(serverIp, serverPort);
			ss=new SocketStream(serverSocket);
			
			//����ͷ��Ϣ
			sendHeaderInfo(ss.pw);
		
			//��ȡ����������Ϣ,����ʹ��
			String line;
			while( (line=ss.br.readLine()) != null)
			{
				this.jTextArea.append(line+"\n");
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * �ڲ�socketStream��,���ڻ�ȡsocket����
	 */
	public class SocketStream{
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
				+"&Ip="+this.serverSocket.getInetAddress().getHostAddress());
		pw.flush();
	}

	
	/**
	 * �������õĽӿ�,���մ�Զ����������Ϣ
	 * @return ���������Ϣ
	 * @throws IOException ��������ȥ����
	 */
	public String recevieMessage() throws IOException
	{
		return ss.br.readLine();
	}
	
	/**
	 * ��¶������Ľӿ�,���ڷ�����Ϣ��������
	 * @param message Ҫ���͵���Ϣ
	 */
	public void sendMessage(String message)
	{
		ss.pw.println(message);
		ss.pw.flush();
	}
}
