/**
 * 
 */
package server_client;

import java.util.ArrayList;
import java.util.HashMap;

import socket.MySocketClient;
import socket.MySocketServer;

/**
 * 这个抽象类是用来约定作为服务器需要的方法
 * @author 林思鑫
 *  
 */
public abstract class AsServer implements MySocketServer{
	/**
	 * 连接到服务器的客户端的列表,里面保存的是学号的字符串<br>
	 * 用来标识哪些人在线
	 */
	protected ArrayList<String>onlineList;
	/**
	 * 保存学号及其对应的Client对象
	 */
	protected HashMap<String, MySocketClient> ipMap;
	/**
	 * 让两个客户端之间实现信息的发送
	 * @param c1 客户端1
	 * @param c2 客户端2
	 */
	abstract protected void  sendMessage(MySocketClient c1,MySocketClient c2);
	/**
	 * 当有人退出的时候要更新一下在线列表
	 */
	abstract protected void  updateList();
	/**
	 * 将指定的客户端添加到在线列表
	 * @param client 指定的客户端
	 */
	abstract protected void  addNewClient(MySocketClient client);
}
