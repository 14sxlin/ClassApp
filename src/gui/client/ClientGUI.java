package gui.client;

import java.io.IOException;

import server_client.ServerInfo;
import server_client.TcpSocketClient;

public class ClientGUI {
	/**
	 * ʹ��һ��client,��������socket������
	 */
	private TcpSocketClient client;
	/**
	 * ���������ҵ��û�����
	 */
	private PublicChatRoomForUser gui;
	
	public ClientGUI() {
		gui=new  PublicChatRoomForUser(null);
		
		//���ӷ�����
		try {
			client=new TcpSocketClient("С����",gui.jTextArea);
			client.startConnectServer(ServerInfo.SERVER_LOCAL_IP, ServerInfo.PORT);
		} catch (IOException e) {
			gui.jTextArea.append("�������ر���");
		}
	}
	
	public static void main(String []args)
	{
		new ClientGUI();
	}
}
