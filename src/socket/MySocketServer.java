package socket;

import java.io.IOException;
import java.net.Socket;

/**
 * 用来创建Socket的服务端,等待其他socket的接入
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
