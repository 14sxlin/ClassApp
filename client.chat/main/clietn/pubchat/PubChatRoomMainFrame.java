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
import main.client.groupchat.GroupChatMainFrame;
import object.AsClient;
import object.HeadType;
import object.ServerInfo;
import tools.ClassmateListSelectDialog;

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
	
	private String onlineList ="";
	
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
						
						//�������Ĵ���
						GroupChatMainFrame temp =
								new GroupChatMainFrame(time,getSelectedUsername(), logic);
						ClassAppMainFrame.groupChatManager.add(temp);
						
						//�������İ�ť���¼�
						temp.getGui().buttons[0].addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
									String namelist ="";
									for(int i=0 ;i<gui.classmateList.getModel().getSize(); i++)
									{
										if(gui.classmateList.getModel().getElementAt(i) != null)
											onlineList+=(gui.classmateList.getModel().getElementAt(i)+"&");
									}
									if(onlineList.equals("")||onlineList==null) {
										JOptionPane.showMessageDialog(gui, "û������");
									}else
									{
										//�½�ѡ�˵Ĵ���,���ҽ���Ϣ���͵�������
										new Thread(new Runnable() {
											
											@Override
											public void run() {
												try {
													onlineList = onlineList.substring(0 , onlineList.length()-1);
													ClassmateListSelectDialog select = 
															new ClassmateListSelectDialog(onlineList);
													
													synchronized (ClassmateListSelectDialog.lock) {
															if(select.selectedUsernamelist.equals(""))
															{	
																// TODO System Output Test Block
																System.out.println(" ����ȴ� ");
																ClassmateListSelectDialog.lock.wait();
															
															}
															
															System.out.println(" namelist =  "+namelist);
															String headinfo =
																	HeadType.GIN+temp.getMark()+":"+select.selectedUsernamelist+"#";
															// TODO System Output Test Block
															System.out.println(" headinfo =  "+headinfo);
															logic.sendMessageToServer(headinfo);
														}
												} catch (InterruptedException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												
											}
										}).start();
									}
								}
						});
					
						
						// TODO Auto-generated catch block
						//������뿴�����޷������ܲ��ܳ���
						//������������յ�
//						new Thread(new Runnable() {
//							
//							@Override
//							public void run() {
//								try {
//									temp.startReceiveMessage();
//								} catch (IOException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								
//							}
//						}).start();
						
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
	 * @return username1 & username2
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
