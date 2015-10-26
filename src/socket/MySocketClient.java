package socket;

import java.io.IOException;

/**
 * 用来连接指定的计算机
 * @author 林思鑫
 *
 */
public interface MySocketClient extends MySocketStream{
	/**
	 * 连接指定的socket通道
	 * @param ip 指定对方的IP
	 * @param port 指定对方的端口
	 * @throws IOException
	 */
	void connect(String ip,int port) throws IOException;	
	/**
	 * 关闭socket通道
	 * @throws IOException
	 */
	void close() throws IOException;
	
}
