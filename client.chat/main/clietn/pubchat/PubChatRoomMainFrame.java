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
import classapp.mainframe.ClassAppMainFrame;
import gui.pubChatRoom.GuiForPublicChatRoom;
import headinfoFilter.HeadType;
import main.client.groupchat.GroupChatMainFrame;
import object.AsClient;
import object.ServerInfo;

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
			logic.startConnectServer(ServerInfo.SERVER_IP, ServerInfo.PORT);
		} catch (IOException e) {
			try {
				logic.startConnectServer(ServerInfo.SERVER_LOCAL, ServerInfo.PORT);
			} catch (IOException e1) {
				gui.my_jtextPane.setText("�������ر���");
			}
			
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
				if(!gui.jTextField.getText().equals(""))
				{
					logic.sendMessageWithName(gui.jTextField.getText());
					gui.jTextField.setText("");
				}
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
					if(!gui.jTextField.getText().equals(""))
					{
						logic.sendMessageWithName(gui.jTextField.getText());
						gui.jTextField.setText("");
					}
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
						//����������Ϣ��������
						long time = System.currentTimeMillis();
						groupinfo =(HeadType.GROUP+username+"!"+
								groupinfo+":"+time+"#");
						logic.sendMessageToServer(groupinfo);
						GroupChatMainFrame temp =
								new GroupChatMainFrame(time,getSelectedUsername(), logic);
						ClassAppMainFrame.groupChatManager.add(temp);
						
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

	/**
	 * ��ȡѡ�е��˼��뵽����������
	 * @return
	 */
	private String getSelectedUsername()
	{
		String temp="";
		for( int i=0; i<gui.classmateList.getModel().getSize(); i++)
		{
			if(gui.classmateList.isSelectedIndex(i))
				temp+=(gui.classmateList.getModel().getElementAt(i)+"&");
		}
		temp = temp.substring(0, temp.length()-1);
		return temp;
	}
	
	public AsClient getClient() {
		return logic;
	}

	
}
