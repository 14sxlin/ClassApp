package tools;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import headinfoFilter.HeadType;
import object.Client;
import object.TimeAddtion;

public class ClientsManager {
	
	
	/**
	 * ������¼��������
	 */
	public static  int counter=0;
	
	/**
	 * ��������ͻ���
	 */
	public static ArrayList<Client> clientList = new ArrayList<>();
	
	/**
	 * �������������û����б�
	 */
	public static ArrayList<String> userNameList = new ArrayList<>();
	
	/**
	 * �����еĿͻ��˷�����Ϣ
	 * @param message Ҫ���͵���Ϣ
	 */
	public static void sendAllClient(String message)
	{
		Iterator<Client> it = clientList.iterator();
		while(it.hasNext())
		{
			PrintWriter pw=it.next().getSocketStream().getPrintWriter();
			pw.println(TimeAddtion.getTime()+message);
			pw.flush();
		}
	}
	
	/**
	 * ��������Ϣ��ָ���Ŀͻ���
	 * @param username ָ��������Ϣ���û���
	 * @param message Ҫ������Ϣ
	 */
	public static void sendMessage(String username, String message)
	{
		Client client = null;
		Iterator<Client> it = clientList.iterator();
		
		while( it.hasNext() )
		{
			Client temp = it.next();
			if(temp.getUserName().equals(username))
				client = temp;
		}
		
		if (client != null )
		{
			client.getSocketStream().getPrintWriter().println(TimeAddtion.getTime()+message);
			client.getSocketStream().getPrintWriter().flush();
		}
	}

	/**
	 * ���������û����б���
	 * ��ʵ��Ҫ�Ĳ�����������clientsList�б�
	 * ��userNameList����clientsList���ɵ�
	 */
	public synchronized static void updateNameList()
	{
		userNameList.removeAll(userNameList);
		Iterator<Client> it = clientList.iterator();
		while (it.hasNext()) {
			userNameList.add(it.next().getUserName());
		}
		counter = userNameList.size();
	}
	
	/**
	 * �����б���Ϣ���ͻ���
	 */
	public static void sendAllListMessage()
	{
		String returnString;
		synchronized (userNameList) {
			returnString = HeadType.LIST;
			Iterator<String> it = userNameList.iterator();
			while (it.hasNext()) {
				returnString = returnString + it.next() + "&";
			}
			returnString = returnString.substring(0, returnString.length() - 1)+"#";
		}
		sendAllClient(returnString);
	}
}