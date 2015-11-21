package main_frame.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import api.client.TcpSocketClient;
import api.server.ServerInfo;
import gui.client.GuiForPublicChatRoom;

/**
 * �ͻ��˵Ĺ��������ҵĽ���
 * @author think
 *
 */
	public class PubChatRoomMainFrame {
	/**
	 * ʹ��һ��client,��������socket������
	 */
	private TcpSocketClient client;
	/**
	 * ���������ҵ��û�����
	 */
	private GuiForPublicChatRoom gui;
	
	/**
	 * ����������������͹�������Ϣ
	 */
	private StringBuilder storeString;
	
	public PubChatRoomMainFrame() {
		
		gui=new  GuiForPublicChatRoom(null);
		
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				connectServer();
				receiveMessageFromServer();
			}
		});
		thread.start();
		
		//�����������������Ϣ ����ʹ��
		gui.sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(gui.jTextField.getText());
				gui.jTextArea.append(gui.jTextField.getText());
				gui.jTextField.setText("");
			}
		});
	}
	
	/**
	 * ���ӷ�����
	 */
	private void connectServer()
	{
		//���ӷ�����
		try {
			client=new TcpSocketClient("С����",gui.jTextArea);
			client.startConnectServer(ServerInfo.SERVER_LOCAL_IP, ServerInfo.PORT);
		} catch (IOException e) {
			gui.jTextArea.append("�������ر���");
		}		
	}
	
	/**
	 * ��������������Ϣ
	 */
	private void receiveMessageFromServer()
	{
		try {
			client.receiveMessageFromServer(storeString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String []args)
	{
		new PubChatRoomMainFrame();
	}
}
