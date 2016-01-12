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
	 * 连接服务器
	 */
	public void connectServer()
	{
		//连接服务器
		try {
			logic=new PubChatRoomLogic(username,gui);
			logic.startConnectServer(ServerInfo.SERVER_IP, ServerInfo.PORT);
		} catch (IOException e) {
			try {
				logic.startConnectServer(ServerInfo.SERVER_LOCAL, ServerInfo.PORT);
			} catch (IOException e1) {
				gui.my_jtextPane.setText("服务器关闭了");
			}
			
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
						
						//发送组聊消息给服务器
						long time = System.currentTimeMillis();
						groupinfo =(HeadType.GROUP+username+"!"+
								groupinfo+":"+time+"#");
						logic.sendMessageToServer(groupinfo);
						
						//弹出组聊窗口
						GroupChatMainFrame temp =
								new GroupChatMainFrame(time,getSelectedUsername(), logic);
						ClassAppMainFrame.groupChatManager.add(temp);
						
						//邀请加入的按钮的事件
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
										JOptionPane.showMessageDialog(gui, "没人在线");
									}else
									{
										//新建选人的窗口,并且将信息发送到服务器
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
																System.out.println(" 进入等待 ");
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
						//这个我想看看无限分流性能不能成立
						//结果是轮流接收的
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
						JOptionPane.showMessageDialog(null, "请选择用户");
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请选择用户");
				}
				
			}
		});

		
	}

	/**
	 * 获取选中的人加入到讨论组里面
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
