package main_frame.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import api.client.ClientForPubChatRoom;
import api.server.ServerInfo;
import gui.pubChatRoom.client.GuiForPublicChatRoom;

/**
 * 客户端的公共聊天室的界面
 * @author think
 *
 */
	public class PubChatRoomMainFrame {
	
		/**
	 * 使用一个client,用来管理socket的连接
	 */
	private ClientForPubChatRoom client;
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
		storeString=new StringBuilder();
		
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				connectServer();
				receiveMessageFromServer();
			}
		});
		thread.start();
		
		addListener();
	}
	
	/**
	 * 连接服务器
	 */
	public void connectServer()
	{
		//连接服务器
		try {
			client=new ClientForPubChatRoom("小李子",gui.jTextArea);
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

	
	/**
	 * 实现gui的button和textfiled的监听
	 */
	private void addListener()
	{
		gui.sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(gui.jTextField.getText());
				gui.jTextArea.append(gui.jTextField.getText()+"\n");
				gui.jTextField.setText("");
			}
		});
		
		gui.jTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					client.sendMessageToServer(gui.jTextField.getText());
					gui.jTextArea.append(gui.jTextField.getText()+"\n");
					gui.jTextField.setText("");
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	}
	
	public static void main(String []args)
	{
		new PubChatRoomMainFrame();
	}
}
