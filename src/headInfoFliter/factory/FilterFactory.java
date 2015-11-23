package headInfoFliter.factory;

import java.util.ArrayList;

import headInfoFliter.fliter.HeadInfoFilter;
import headInfoFliter.fliter.LoginFilter;
import headInfoFliter.fliter.LogoutFilter;
import object_client_server.Client;

public class FilterFactory {
	
	/**
	 * 用来调用下面提醒的方法
	 */
	public static  ArrayList<Client> memberList;
	
	public FilterFactory() {
		
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
	 * @param filterType 传入的判断条件
	 * @return 返回相应的过滤器
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
