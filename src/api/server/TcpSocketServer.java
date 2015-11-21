package api.server;

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

import javax.swing.JTextArea;

import threadData.ThreadDataTransfer;

/**
 * ��������Socket����
 * @author ��˼��
 *
 */
public class TcpSocketServer implements AsServer{
	
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
	
	/**
	 * ������ʾ��ȡ�ͻ��˵ķ��͵�����������Ϣ
	 */
	public JTextArea textPane;
	
	/**
	 * ����������������
	 * ���Ⱪ¶
	 */
	public int counter=0;
	
	/**
	 * �������浱ǰ���߳�
	 */
	private Thread currentThread;
	
	/**
	 * ������ʾ�Ƿ��������ⲿ�������ݵĹ���
	 */
	public boolean outSwing;

	/**
	 * ���������߳��е�ֵ������
	 */
	private ThreadDataTransfer tdt;
	
	/**
	 * ���췽��
	 * @param tdt �н����ݴ�����
	 */
	public TcpSocketServer(ThreadDataTransfer tdt)  {
		socketMap=new HashMap<>();
		userNameList=new ArrayList<>();
		threadMap=new HashMap<>();
		socketStreamMap=new HashMap<>();
		
		this.tdt=tdt;
	}
	
	/**
	 * ���췽��
	 * @param textPane ������ʾ��Ϣ��,��Ҫ�ǲ���ʹ��
	 * @param tdt ����紫���߳��ڵ����ݵ��н�
	 */
	public TcpSocketServer(ThreadDataTransfer tdt,JTextArea textPane)  {
		this(tdt);
		this.textPane=textPane;
	}
	
	/**
	 * ��������,���տͻ��˵�����,���̵߳Ĵ���
	 * @param port ��ʹ�õĶ˿�
	 */
	@Override
	public  void startService(int port)
	{
		try {
			//�½�һ��serverSocket,���ܻ���ֶ˿��Ѿ���ռ�õ�����
			serverSocket=new ServerSocket(port);
			
			if ( textPane != null )
				textPane.append("���ڵȴ�����\n");
			
			//��ʼ�����������µ��߳�,�������ӳɹ�����Ϣ,����ͻ��˷�����ͷ��Ϣ
			while(true)
			{
				Socket currentSocket=serverSocket.accept();
				currentThread=new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							ss=new SocketStream(currentSocket);
							
							synchronized(this)
							{
								counter++;
							}
							
							//��ͻ��˷������ӳɹ�����Ϣ
							ss.pw.println("���ӷ������ɹ�\n");
							ss.pw.flush();
							
							//����ͷ��Ϣ
							String line=ss.br.readLine();
							
							if(textPane != null)
								textPane.append(line+"\n");
							
							handleHeaderInfo(line, currentSocket,
									socketMap, userNameList);
							
							//���·��������������
							if(tdt!=null)
								tdt.updateState(counter, userNameList);
							
							
							//��ȡ�û���
							currentUserName=searchUserName(line);
							
							if(textPane != null)
								textPane.append("ueserNameList="+listToString(userNameList)+"\n");
							
							//���û���������Ӧ���̺߳���
							synchronized(this)
							{	
								socketStreamMap.put(currentUserName, ss);	
								threadMap.put(currentUserName , currentThread);
							}
							
							
							//��ͻ��˷��������б����Ϣ
							
							
							//������Ϣ ����ʹ�� ���Ӧ�÷������
							try {
								if(textPane != null)
									while((line=ss.br.readLine()) != null)
									{
										textPane.append(line+"\n");
									}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				});
				currentThread.start();//�̱߳�������,�����һֱ����������
							
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
	private  class SocketStream{
		private BufferedReader br;
		private PrintWriter pw;
		public SocketStream(Socket socket) throws IOException {
			this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw=new PrintWriter(socket.getOutputStream());
		}
		@SuppressWarnings("unused")
		public  void closeStream() throws IOException
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
	 * ��������ͷ��Ϣ,��ȡ�ͻ��˵���Ϣ,Ȼ���û���Ϣ��ӵ��û����б���
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
	 * ֹͣ���еķ���,���Ӧ�ø����еĿͻ��˷����˳���Ϣ,Ȼ���ÿͻ����˳�,֮���Լ���ֹͣsocket
	 * @throws IOException serverSocket.close()������Ĵ���,���������ߴ���
	 */
	@SuppressWarnings("deprecation")
	synchronized public void stopService() throws IOException
	{
//		if(ss != null)
//			ss.closeStream();
		Iterator<String> it=userNameList.iterator();
		String username;
		while(it.hasNext())
		{
			username=it.next();
			try {
				socketMap.get(username).close();
				threadMap.get(username).stop();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.serverSocket.close();//���ָ�����ǲ���ȥ�����Ǹ��˿���
			
		if ( textPane != null )
			textPane.append("�����ѹر�\n");
	}
	
	/**
	 * ��������Ϣ��ָ���Ŀͻ���
	 * @param username ָ��������Ϣ���û���,��*�Ŵ��������е������û�����
	 * @param message Ҫ������Ϣ
	 */
	public void sendMessage(String username, String message)
	{
		if (username != "*") {
			SocketStream tempss = socketStreamMap.get(username);
			if (tempss != null) {
				tempss.pw.println(message);
				tempss.pw.flush();
			} 
		}else
		{
			SocketStream tempss;
			Iterator<String> it=userNameList.iterator();
			while( it.hasNext() )
			{
				tempss = socketStreamMap.get( it.next() );
				tempss.pw.println(message);
				tempss.pw.flush();
			}
		}
	}

	@Override
	public void notifyLogin() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLogout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerUpdate() {
		// TODO Auto-generated method stub
		
	}

}

