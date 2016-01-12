package tools;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import object.Client;
import object.HeadType;
import object.TimeAddtion;

public class ClientsManager {
	
	
	/**
	 * 用来记录在线人数
	 */
	public static  int counter=0;
	
	/**
	 * 用来保存客户端
	 */
	public static ArrayList<Client> clientList = new ArrayList<>();
	
	/**
	 * 用来保存在线用户名列表
	 */
	public static ArrayList<String> userNameList = new ArrayList<>();
	
	/**
	 * 想所有的客户端发送消息
	 * @param message 要发送的消息
	 */
	public static void sendAllClient(String message,boolean sendTime)
	{
		Iterator<Client> it = clientList.iterator();
		while(it.hasNext())
		{
			PrintWriter pw=it.next().getSocketStream().getPrintWriter();
			if(sendTime)
			{
				pw.println(TimeAddtion.getTime()+message);
			}else
			{
				pw.println(message);
			}
			pw.flush();
		}
	}
	
	/**
	 * 用来发信息给指定的客户端
	 * @param username 指定发送信息的用户名
	 * @param message 要发的信息
	 * @param addTime true则加入时间
	 */
	public static void sendMessage(String username, String message,boolean addTime)
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
			if(addTime)
			{
				client.getSocketStream().getPrintWriter().println(TimeAddtion.getTime()+message);
				client.getSocketStream().getPrintWriter().flush();
			}else
			{
				client.getSocketStream().getPrintWriter().println(message);
				client.getSocketStream().getPrintWriter().flush();
			}
		}
	}

	/**
	 * 用来更新用户名列表的
	 * 其实主要的操作都是在于clientsList列表
	 * 而userNameList是有clientsList生成的
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
	 * 发送列表信息给客户端
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
		sendAllClient(returnString,false);
	}
}
