/**
 * 
 */
package api.server;

/**
 * 这个抽象类是用来约定作为服务器需要的方法
 * @author 林思鑫
 */
public interface AsServer{
	
	/**
	 * 开启服务
	 * @param port 服务的端口
	 */
	void startService(int port);
	
	
	/**
	 * 当有人注册的时候要更新总的注册用户的列表
	 */
	void registerUpdate();

	
}
