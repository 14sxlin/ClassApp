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
 * 客户端的公共聊天室的界面
 * @author 林思鑫
 *
 */
	public class PubChatRoomMainFrame {
	
		/**
	 * 使用一个client,用来管理socket的连接
	 */
	private PubChatRoomLogic logic;
	/**
	 * 公共聊天室的用户界面
	 */
	public GuiForPublicChatRoom gui;
	
	/**
	 * 用来保存服务器发送过来的信息
	 */
	private StringBuilder storeString;
	
	/**
	 * 用来保存自己的用户名
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
	 * 连接服务器
	 */
	public void connectServer()
	{
		//连接服务器
		try {
			logic=new PubChatRoomLogic(username,gui);
			logic.startConnectServer(ServerInfo.SERVER_LOCAL_IP, ServerInfo.PORT);
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
			logic.receiveMessageFromServer(storeString);
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
						JOptionPane.showMessageDialog(null, "请选择用户");
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请选择用户");
				}
				
			}
		});

	}

	public PubChatRoomLogic getClient() {
		return logic;
	}
}
