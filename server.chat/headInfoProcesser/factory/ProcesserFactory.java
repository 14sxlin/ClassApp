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
	 * 根据条件创建相应的过滤器
	 * @param Type 传入的判断条件,用包含来判断
	 * @return 返回相应的过滤器
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
