package gui.client;

import java.io.IOException;

import server_client.ServerInfo;
import server_client.TcpSocketClient;

public class ClientGUI {
	/**
	 * 使用一个client,用来管理socket的连接
	 */
	private TcpSocketClient client;
	/**
	 * 公共聊天室的用户界面
	 */
	private PublicChatRoomForUser gui;
	
	public ClientGUI() {
		gui=new  PublicChatRoomForUser(null);
		
		//连接服务器
		try {
			client=new TcpSocketClient("小林子",gui.jTextArea);
			client.startConnectServer(ServerInfo.SERVER_LOCAL_IP, ServerInfo.PORT);
		} catch (IOException e) {
			gui.jTextArea.append("服务器关闭了");
		}
	}
	
	public static void main(String []args)
	{
		new ClientGUI();
	}
}
