package headInfoProcesser.processer;

import object.Client;

/**
 * 头信息过滤器的抽象类
 * @author 林思鑫
 *
 */
public abstract class HeadInfoProcesser {
		
	/**
	 * 适用于往列表中添加对象
	 * @param client 传入一个Client对象
	 * @throws Exception 
	 */
	public abstract void process(Client client) throws Exception;
	
	/**
	 * 适用于删除列表中的对象
	 * @param userName 传入用户名
	 * @throws Exception 
	 */
	public abstract void process(String userName) throws Exception;
}
