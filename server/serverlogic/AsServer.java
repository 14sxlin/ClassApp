/**
 * 
 */
package serverlogic;

import java.io.IOException;
import java.net.SocketException;

/**
 * 这个抽象类是用来约定作为服务器需要的方法
 * @author 林思鑫
 */
public interface AsServer{
	
	/**
	 * 开启服务
	 * @param port 服务的端口
	 * @throws SocketException 
	 * @throws IOException 
	 */
	void startService(int port) throws SocketException, IOException;
	
	
	/**
	 * 当有人注册的时候要更新总的注册用户的列表
	 */
	void registerUpdate();

	
}
