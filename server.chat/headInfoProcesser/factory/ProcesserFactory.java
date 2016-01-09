package headInfoProcesser.factory;

import java.util.ArrayList;

import headInfoProcesser.processer.GroupProcesser;
import headInfoProcesser.processer.HeadInfoProcesser;
import headInfoProcesser.processer.LoginProcesser;
import headInfoProcesser.processer.LogoutProcesser;
import object.Client;

public class ProcesserFactory {
	
	public  ArrayList<Client> memberList;
	
	public ProcesserFactory(ArrayList<Client> clientList) {
		this.memberList = clientList;
	}
	
	
	/**
	 * ��������������Ӧ�Ĺ�����
	 * @param Type ������ж�����,�ð������ж�
	 * @return ������Ӧ�Ĺ�����
	 */
	public HeadInfoProcesser createProcesser(String type)
	{
		if(type.contains("login") )
			return new LoginProcesser(memberList);
		if(type.contains("logout") )
			return new LogoutProcesser(memberList);
		if(type.contains("group")||type.contains("gsend"))
			return new GroupProcesser();
		else return null;
	}
}
