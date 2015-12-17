package api.server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import object_client_server.Client;

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
			pw.println(message);
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
			client.getSocketStream().getPrintWriter().println(message);
			client.getSocketStream().getPrintWriter().flush();
		}
	}
}
