package headInfoProcesser.factory;

import java.util.ArrayList;

import headInfoProcesser.processer.HeadInfoProcesser;
import headInfoProcesser.processer.LoginProcesser;
import headInfoProcesser.processer.LogoutProcesser;
import object.Client;

public class ProcesserFactory {
	
	/**
	 * 用来调用下面提醒的方法
	 */
	public static  ArrayList<Client> memberList;
	
	public ProcesserFactory() {
		
	}
	
	/**
	 * 设置用户列表
	 * @param clientList
	 */
	public static  void setMemeberList(ArrayList<Client> clientList)
	{
		memberList=clientList;
	}
	
	/**
	 * 根据条件创建相应的过滤器
	 * @param Type 传入的判断条件,用包含来判断
	 * @return 返回相应的过滤器
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
