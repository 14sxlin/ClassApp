package server_client;

import socket.MySocketClient;
import socket.MySocketServer;
/**
 * <p>
 * 规定了作为客户端的方法,这个类也实现了Mysocket类
 * 这是为了在一对一连接的时候,能够接受到别人的连接
 * 这样在一对一连接的时候,就不用通过服务器转发信息了
 * </p>
 * @author 林思鑫
 *
 */
public abstract class AsClient implements MySocketClient,MySocketServer {
	/**
	 * 通过在输出流中增加标识,来让服务器确定发送的目标
	 * @param client 指定一个对象作为发送信息的目标<br>
	 * 					这个对象的获得是通过服务器传送到客户端的在线列表指定的.
	 * @return 返回一个标识,用来加入到输出流中
	 */
	abstract protected String sendDestinationHeader(MySocketClient client);
}
