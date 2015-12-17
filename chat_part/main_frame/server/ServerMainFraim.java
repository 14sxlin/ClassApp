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
 * �������Ĺ�����
 * @author ��˼��
 *
 */
public class ServerMainFraim {
	
	/**
	 * �û�ͼ�ν���
	 */
	private GuiForServer gui;

	/**
	 * ����������
	 */
	private ServerForPubChatRoom server;
	
	/**
	 * �̶߳���
	 */
	private Thread currentThread;
	
	/**
	 * ������server�е��̵߳����ݽ���
	 */
	private ThreadDataTransfer tdt;
	
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public ServerMainFraim() {
		gui=new GuiForServer();
		addListener();
	}
	
	/**
	 * ��ʼ�ṩ����
	 */
	public void startService()
	{
		tdt=new ThreadDataTransfer();
		tdt.setField(gui.counterTextField, gui.listmodel);
		
		//ע�������,������������ʱ�������߳�,�����ƹر��߳�
		gui.startServiceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("��������"))
				{
					gui.startServiceButton.setText("�رշ���");
					currentThread=new Thread(new Runnable() {
						
						@Override
						public void run() {
							server=new ServerForPubChatRoom(tdt,gui.textPane);	
							try {
								server.startService(ServerInfo.PORT);
							} catch (SocketException e) {
								// TODO Auto-generated catch block
								gui.textPane.append("���������ٽ�������\n");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					currentThread.start();					
				}
				else//�����Ҫ����һ��
				{// TODO Auto-generated catch block
					try {
						server.stopService();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gui.startServiceButton.setText("��������");
				}
			}
			
		});	
	}
	
	/**
	 * ʵ��gui��button��textfiled�ļ���
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
