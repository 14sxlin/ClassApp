package headInfoFliter.factory;

import java.util.ArrayList;

import headInfoFliter.fliter.HeadInfoProcesser;
import headInfoFliter.fliter.LoginProcesser;
import headInfoFliter.fliter.LogoutProcesser;
import object_client_server.Client;

public class ProcesserFactory {
	
	/**
	 * ���������������ѵķ���
	 */
	public static  ArrayList<Client> memberList;
	
	public ProcesserFactory() {
		
	}
	
	/**
	 * �����û��б�
	 * @param clientList
	 */
	public static  void setMemeberList(ArrayList<Client> clientList)
	{
		memberList=clientList;
	}
	
	/**
	 * ��������������Ӧ�Ĺ�����
	 * @param filterType ������ж�����
	 * @return ������Ӧ�Ĺ�����
	 */
	public static  HeadInfoProcesser createFilter(String type)
	{
		if(type.contains("login") )
			return new LoginProcesser(memberList);
		if(type.contains("logout") )
			return new LogoutProcesser(memberList);
		else return null;
	}
}
