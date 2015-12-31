package headInfoProcesser.factory;

import java.util.ArrayList;

import headInfoProcesser.processer.HeadInfoProcesser;
import headInfoProcesser.processer.LoginProcesser;
import headInfoProcesser.processer.LogoutProcesser;
import object.Client;

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
	 * @param Type ������ж�����,�ð������ж�
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
