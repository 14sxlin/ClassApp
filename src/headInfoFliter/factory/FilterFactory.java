package headInfoFliter.factory;

import java.util.ArrayList;

import headInfoFliter.fliter.HeadInfoFilter;
import headInfoFliter.fliter.LoginFilter;
import headInfoFliter.fliter.LogoutFilter;
import object_client_server.Client;

public class FilterFactory {
	
	/**
	 * ���������������ѵķ���
	 */
	public static  ArrayList<Client> memberList;
	
	public FilterFactory() {
		
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
	public static  HeadInfoFilter createFilter(String filterType)
	{
		if(filterType.equals("login") )
			return new LoginFilter(memberList);
		if(filterType.equals("logout") )
			return new LogoutFilter(memberList);
		else return null;
	}
}
