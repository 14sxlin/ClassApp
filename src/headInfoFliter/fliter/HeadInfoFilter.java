package headInfoFliter.fliter;

import java.util.ArrayList;

import object_client_server.Client;

/**
 * 头信息过滤器的抽象类
 * @author 林思鑫
 *
 */
public abstract class HeadInfoFilter {
		
	/**
	 * 适用于网列表中添加对象
	 * @param client 传入一个Client对象
	 * @return 传回新的客户端列表
	 */
	public abstract ArrayList<Client> process(Client client);
	
	/**
	 * 适用于删除列表中的对象
	 * @param userName 传入用户名
	 * @return 返回新的客户端列表
	 */
	public abstract ArrayList<Client> process(String userName);
}
