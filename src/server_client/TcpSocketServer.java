package server_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ��������Socket����
 * @author ��˼��
 *
 */
public class TcpSocketServer {
	
	/**
	 * ��������ServerSocket
	 */
	private ServerSocket serverSocket;
	
	/**
	 * �������������û���socketstream
	 */
	private HashMap<String, SocketStream> socketStreamMap;
	
	/**
	 * �����������ߵ��û�socket
	 */
	public HashMap<String, Socket> socketMap;
	
	/**
	 * ���������̵߳���Ϣ
	 */
	public HashMap<String, Thread> threadMap;
	
	/**
	 * �������������û����б�
	 */
	public ArrayList<String> userNameList;
	
	/**
	 * �ڲ���,���������������������Ϣ
	 */
	private SocketStream ss;
	
	/**
	 * ���浱ǰ���û���
	 */
	private String currentUserName;
	
	public TcpSocketServer() {
		socketMap=new HashMap<>();
		userNameList=new ArrayList<>();
		threadMap=new HashMap<>();
	}
	
	/**
	 * ��������,���տͻ��˵�����,���̵߳Ĵ���
	 */
	public  void startService(int port)
	{
		try {
			//�½�һ��serverSocket,���ܻ���ֶ˿��Ѿ���ռ�õ�����
			serverSocket=new ServerSocket(port);
			
			//��ʼ�����������µ��߳�
			while(true)
			{
				Socket currentSocket=serverSocket.accept();
				Thread currentThread=null;
				currentThread=new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							ss=new SocketStream(currentSocket);
							String line=ss.br.readLine();//��һ�ζ�ȡ����ͷ��Ϣ
							handleHeaderInfo(line, currentSocket,
									socketMap, userNameList);
							currentUserName=searchUserName(line);
						} catch (IOException e) {
							e.printStackTrace();
						}
					
					}
				});
				threadMap.put(currentUserName , currentThread);
				socketStreamMap.put(currentUserName, ss);				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ڲ���Socket��
	 * @author ��˼��
	 *
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
			this.br.close();
			this.pw.close();
		}
	}
	
	/**
	 * ��ͷ��Ϣ�л�ȡ�û�����Ϣ
	 * @param ���ַ����н�ȡ�û���
	 * @return ���ؿͻ��˵��û���
	 */
	private String searchUserName(String line)
	{
		int i=line.indexOf("UserName=");
		int length="UserName=".length();
		if(i!=-1)
			return line.substring(i+length,line.indexOf("&",length));
		else
			return "";
	}

	/**
	 * ��ȡ�û�����Ϣ�б��е������û���,ת�����ض���ʽ���ַ���(userName1&userName2&...&)
	 * @param nameList �����������û�����Ϣ���б�
	 * @return �ض���ʽ�������û����ַ���
	 */
	@SuppressWarnings("unused")
	private String listToString(ArrayList<String> nameList)
	{
		String returnString="";
		Iterator<String> it=nameList.iterator();
		while(it.hasNext())
		{
			returnString=returnString+it.next()+"&";
		}
		return returnString;
	}
	
	/**
	 * ��������ͷ��Ϣ,��ȡ�ͻ��˵���Ϣ,Ȼ���û���Ϣ��ӵ��б���
	 * @param line ���������ж�ȡ���ַ���
	 * @param currentSocket ������Ϣ������Socket
	 * @param socketMap ���Ŀ��
	 * @param userNameList ���Ŀ��
	 */
	private void handleHeaderInfo(String line,Socket currentSocket,
			Map<String, Socket> socketMap,ArrayList<String> userNameList)
	{
		//����ͷ��Ϣ
		if(line.substring(0, 6).equals("#head#"))
		{
			//��װsocketMap��userList��threadMap
			String userName=searchUserName(line);
			socketMap.put(userName, currentSocket);
			userNameList.add(userName);
		}
		
	}
	
	/**
	 * ֹͣ���еķ���
	 */
	@SuppressWarnings("deprecation")
	public void stopService()
	{
		Iterator<String> it=userNameList.iterator();
		String username;
		while(it.hasNext())
		{
			username=it.next();
			try {
				socketMap.get(username).close();
				threadMap.get(username).stop();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}

