package server_client;

import java.io.OutputStream;

/**
 * <p>
 * 规定了作为客户端的方法
 * 在一对一连接的时候,能够接受到别人的连接
 * 这样在一对一连接的时候,就不用通过服务器转发信息了
 * </p>
 * @author 林思鑫
 *
 */
public interface AsClient  {
	
	/**
	 * 开始连接服务器
	 */
	void startConnectServer();

	/**
	 *发送本机的信息给服务器
	 * @param out 指定要发送的输出流
	 */
	 void sendHeaderInfo(OutputStream out);

	 /**
	 * 向服务器发送退出的消息
	 */
	void sendLogoutInfo(OutputStream out);
}
