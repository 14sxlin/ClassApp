package main_frame.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.SocketException;

import api.server.ClientsManager;
import api.server.ServerForPubChatRoom;
import api.server.ServerInfo;
import gui.pubChatRoom.server.GuiForServer;
import threadData.ThreadDataTransfer;

/**
 * 服务器的管理器
 * @author 林思鑫
 *
 */
public class ServerMainFraim {
	
	/**
	 * 用户图形界面
	 */
	private GuiForServer gui;

	/**
	 * 服务器变量
	 */
	private ServerForPubChatRoom server;
	
	/**
	 * 线程对象
	 */
	private Thread currentThread;
	
	/**
	 * 用来与server中的线程的数据交互
	 */
	private ThreadDataTransfer tdt;
	
	/**
	 * 默认的构造方法
	 */
	public ServerMainFraim() {
		gui=new GuiForServer();
		addListener();
	}
	
	/**
	 * 开始提供服务
	 */
	public void startService()
	{
		tdt=new ThreadDataTransfer();
		tdt.setField(gui.counterTextField, gui.listmodel);
		
		//注册监听器,点击开启服务的时候启动线程,并控制关闭线程
		gui.startServiceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("开启服务"))
				{
					gui.startServiceButton.setText("关闭服务");
					currentThread=new Thread(new Runnable() {
						
						@Override
						public void run() {
							server=new ServerForPubChatRoom(tdt,gui.textPane);	
							try {
								server.startService(ServerInfo.PORT);
							} catch (SocketException e) {
								// TODO Auto-generated catch block
								gui.textPane.append("服务器不再接收连接\n");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					currentThread.start();					
				}
				else//这个需要完善一下
				{// TODO Auto-generated catch block
					try {
						server.stopService();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gui.startServiceButton.setText("开启服务");
				}
			}
			
		});	
	}
	
	/**
	 * 实现gui的button和textfiled的监听
	 */
	private void addListener()
	{
		gui.sendbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientsManager.sendAllClient(gui.textField.getText());
				if( gui.textField.getText() != "" )
					gui.textPane.append(gui.textField.getText()+"\n");
				gui.textField.setText("");
			}
		});
		
		gui.textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					ClientsManager.sendAllClient(gui.textField.getText());
					if( gui.textField.getText() != "" )
						gui.textPane.append(gui.textField.getText()+"\n");
					gui.textField.setText("");
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	}
	
	public static void main(String []args)
	{
		new ServerMainFraim().startService();
	}

}
