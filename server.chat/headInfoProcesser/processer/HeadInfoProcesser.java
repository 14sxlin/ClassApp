package headInfoProcesser.processer;

import object.Client;

/**
 * 头信息过滤器的抽象类
 * @author 林思鑫
 *
 */
public abstract class HeadInfoProcesser {
		
	/**
	 * 应该调用参数是String的方法
	 * 另一个方法调用会抛出异常
	 */
	public static  final int STRING = 1;
	/**
	 * 应该调用参数是Client的方法
	 * 另一个方法调用会抛出异常
	 */
	public static  final int CLIENT = 2;
	
	/**
	 * 下面有两个process方法
	 * type指定了调用哪种方法
	 */
	protected int type;
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
	
	
	public int getType()
	{
		return type;
	}
}
