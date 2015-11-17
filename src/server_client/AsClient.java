package server_client;

import java.io.IOException;
import java.io.PrintWriter;

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
	void startConnectServer(String serverIp,int serverPort) throws IOException;

	/**
	 *发送本机的信息给服务器
	 * @param out 指定要发送的输出流
	 */
	 void sendHeaderInfo(PrintWriter out);
	
	/**
	 * 监听服务器发送过来的消息
	 * @param storeString 用来保存已经发过来的信息
	 * @throws IOException 
	 */
	void receiveMessageFromServer(StringBuilder storeString) throws IOException;
	
	/**
	 * 向服务器发送消息
	 * @param message 要发送的信息
	 */
	void sendMessageToServer(String message);
}
