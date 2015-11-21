package main_frame.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import api.client.TcpSocketClient;
import api.server.ServerInfo;
import gui.client.GuiForPublicChatRoom;

/**
 * 客户端的公共聊天室的界面
 * @author think
 *
 */
	public class PubChatRoomMainFrame {
	/**
	 * 使用一个client,用来管理socket的连接
	 */
	private TcpSocketClient client;
	/**
	 * 公共聊天室的用户界面
	 */
	private GuiForPublicChatRoom gui;
	
	/**
	 * 用来保存服务器发送过来的信息
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
		
		//可以向服务器发送消息 测试使用
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
	 * 连接服务器
	 */
	private void connectServer()
	{
		//连接服务器
		try {
			client=new TcpSocketClient("小李子",gui.jTextArea);
			client.startConnectServer(ServerInfo.SERVER_LOCAL_IP, ServerInfo.PORT);
		} catch (IOException e) {
			gui.jTextArea.append("服务器关闭了");
		}		
	}
	
	/**
	 * 监听服务器的消息
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
