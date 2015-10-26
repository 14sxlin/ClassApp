package socket;

import java.io.IOException;
import java.net.Socket;

/**
 * 
 * 接口,作为Socket的客户端
 * @author 林思鑫
 */
public interface MySocketServer extends MySocketStream {
	/**
	 * 在指定的端口创建连接
	 * @param port 指定服务器的端口
	 * @return 返回一个被客户端连接的对象
	 */
	Socket createConnection(int port);		
	/**
	 * 关闭socket连接
	 * @throws IOException
	 */
	void close() throws IOException;
}
