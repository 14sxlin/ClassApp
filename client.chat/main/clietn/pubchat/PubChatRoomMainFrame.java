package main.clietn.pubchat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import api.client.pubChatRoom.PubChatRoomLogic;
import api.server.ServerInfo;
import gui.pubChatRoom.GuiForPublicChatRoom;
import main.client.groupchat.GroupChatMainDialog;

/**
 * �ͻ��˵Ĺ��������ҵĽ���
 * @author ��˼��
 *
 */
	public class PubChatRoomMainFrame {
	
		/**
	 * ʹ��һ��client,��������socket������
	 */
	private PubChatRoomLogic logic;
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
			logic=new PubChatRoomLogic(username,gui);
			logic.startConnectServer(ServerInfo.SERVER_LOCAL_IP, ServerInfo.PORT);
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
			logic.receiveMessageFromServer(storeString);
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
				logic.sendMessageToServer(gui.jTextField.getText());
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
					logic.sendMessageToServer(gui.jTextField.getText());
//					gui.jTextArea.append(gui.jTextField.getText()+"\n");
					gui.jTextField.setText("");
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		gui.joinGroupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> selectedlist = gui.classmateList.getSelectedValuesList();
				if(selectedlist != null)
				{
					String groupinfo = "";
					Iterator<String> it = selectedlist.iterator();
					while(it.hasNext())
					{
						groupinfo+=(it.next()+"&");
					}
					
					if(!groupinfo.equals(""))
					{
						groupinfo = groupinfo.substring(0, groupinfo.length()-1);
						// TODO System Output Test Block
						System.out.println(" groupinfo =  "+groupinfo);
						new GroupChatMainDialog(logic.getServer(),username, groupinfo);
					}
					else
						JOptionPane.showMessageDialog(null, "��ѡ���û�");
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "��ѡ���û�");
				}
				
			}
		});

	}

	public PubChatRoomLogic getClient() {
		return logic;
	}
}
