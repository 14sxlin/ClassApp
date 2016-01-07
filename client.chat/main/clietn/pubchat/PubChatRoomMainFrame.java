package main.clietn.pubchat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import api.client.pubChatRoom.PubChatRoomLogic;
import api.server.ServerInfo;
import gui.pubChatRoom.GuiForPublicChatRoom;

/**
 * �ͻ��˵Ĺ��������ҵĽ���
 * @author ��˼��
 *
 */
	public class PubChatRoomMainFrame {
	
		/**
	 * ʹ��һ��client,��������socket������
	 */
	private PubChatRoomLogic client;
	/**
	 * ���������ҵ��û�����
	 */
	public GuiForPublicChatRoom gui;
	
	/**
	 * ����������������͹�������Ϣ
	 */
	private StringBuilder storeString;
	
	/**
	 * ���������Լ����û���
	 */
	private String username;
	
	public PubChatRoomMainFrame(String username) {
		
		gui=new  GuiForPublicChatRoom(username);
		storeString=new StringBuilder();
		this.username = username ;
		
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
	 * ���ӷ�����
	 */
	public void connectServer()
	{
		//���ӷ�����
		try {
			client=new PubChatRoomLogic(username,gui);
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

	
	/**
	 * ʵ��gui��button��textfiled�ļ���
	 */
	private void addListener()
	{
		gui.sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessageToServer(gui.jTextField.getText());
//				gui.jTextArea.append(gui.jTextField.getText()+"\n");
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
//					gui.jTextArea.append(gui.jTextField.getText()+"\n");
					gui.jTextField.setText("");
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
	}

	public PubChatRoomLogic getClient() {
		return client;
	}
}
