package api.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;

import object_client_server.Client;
import threadData.ThreadDataTransfer;

/**
 * ��������Socket����
 * @author ��˼��
 *
 */
public class ServerForPubChatRoom implements AsServer{
	
	/**
	 * ��������ServerSocket
	 */
	private ServerSocket serverSocket;

	
	/**
	 * �������������û����б�
	 */
	public ArrayList<String> userNameList;
	
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
	 * ��������ͻ���
	 */
	ArrayList<Client> clientList;
	
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
	public ServerForPubChatRoom(ThreadDataTransfer tdt)  {
		userNameList=new ArrayList<>();
		clientList=new ArrayList<>();
		this.tdt=tdt;
	}
	
	/**
	 * ���췽��
	 * @param textPane ������ʾ��Ϣ��,��Ҫ�ǲ���ʹ��
	 * @param tdt ����紫���߳��ڵ����ݵ��н�
	 */
	public ServerForPubChatRoom(ThreadDataTransfer tdt,JTextArea textPane)  {
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
				Thread tempThread=new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Client client=new Client(currentSocket);
							
							synchronized(this)
							{
								counter++;
							}
							
							//��ͻ��˷������ӳɹ�����Ϣ
							client.getSocketStream().getPrintWriter().println("���ӷ������ɹ�\n");
							client.getSocketStream().getPrintWriter().flush();
							
							//����ͷ��Ϣ
							String line=client.getSocketStream().getBufferReader().readLine();
							
							if(textPane != null)
								textPane.append(line+"\n");
							
//							handleHeaderInfo(line, currentSocket,
//									socketMap, userNameList);
							
							//���·��������������
							if(tdt!=null)
								tdt.updateState(counter, userNameList);
							
							
							//��ȡ�û���
							client.setUserName(searchUserName(line));
							
							//���ͻ��˼��뵽�б���ȥ
							clientList.add(client);
							
							if(textPane != null)
								textPane.append("ueserNameList="+listToString(userNameList)+"\n");
							
//							//���û���������Ӧ���̺߳���
//							synchronized(this)
//							{	
//								socketStreamMap.put(currentUserName, ss);	
//								threadMap.put(currentUserName , currentThread);
//							}
							
							
							//��ͻ��˷��������б����Ϣ
							
							
							//������Ϣ ����ʹ�� ���Ӧ�÷������
							try {
								if(textPane != null)
									while((line=client.getSocketStream().getBufferReader().readLine()) != null)
									{
										textPane.append(line+"\n");
System.out.println("invoke3");
									}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				});
				tempThread.start();//�̱߳�������,�����һֱ����������
							
			}
			
		} catch (IOException e) {
			e.printStackTrace();
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
	 * ֹͣ���еķ���,���Ӧ�ø����еĿͻ��˷����˳���Ϣ,Ȼ���ÿͻ����˳�,֮���Լ���ֹͣsocket
	 * @throws IOException serverSocket.close()������Ĵ���,���������ߴ���
	 */
	synchronized public void stopService() throws IOException
	{
//		if(ss != null)
//			ss.closeStream();
		this.serverSocket.close();//���ָ�����ǲ���ȥ�����Ǹ��˿���
		
		Iterator<Client> it = clientList.iterator();
		while(it.hasNext())
		{
			it.next().getSocketStream().closeStream();
		}
		
		if ( textPane != null )
			textPane.append("�����ѹر�\n");
	}
	
	/**
	 * ��������Ϣ��ָ���Ŀͻ���
	 * @param username ָ��������Ϣ���û���
	 * @param message Ҫ������Ϣ
	 */
	public void sendMessage(Client client, String message)
	{
		if (client != null )
		{
			client.getSocketStream().getPrintWriter().println(message);
			client.getSocketStream().getPrintWriter().flush();
		}
	}

	/**
	 * �����б���Ļ�ͻ�������Ϣ
	 * @param message
	 */
	public void sendAllClient(String message)
	{
		Iterator<Client> it = clientList.iterator();
		while(it.hasNext())
		{
			PrintWriter pw=it.next().getSocketStream().getPrintWriter();
			pw.println(message);
			pw.flush();
		}
	}
	
	
	@Override
	public void registerUpdate() {
		// TODO Auto-generated method stub
		
	}

}

