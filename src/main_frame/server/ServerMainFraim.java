package main_frame.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import api.server.ServerInfo;
import api.server.TcpSocketServer;
import gui.server.GuiForServer;
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
	GuiForServer gui;
	/**
	 * ����������
	 */
	private TcpSocketServer server;
	
	/**
	 * ������server�е��̵߳����ݽ���
	 */
	private ThreadDataTransfer tdt;
	
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public ServerMainFraim() {
		gui=new GuiForServer();
		gui.sendbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				server.sendMessage("*", gui.textField.getText());
				if( gui.textField.getText() != "" )
					gui.textPane.append(gui.textField.getText()+"\n");
				gui.textField.setText("");
			}
		});
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
					Thread thread=new Thread(new Runnable() {
						
						@Override
						public void run() {
							server=new TcpSocketServer(tdt,gui.textPane);	
							server.startService(ServerInfo.PORT);
						}
					});
					thread.start();					
				}
				else//�����Ҫ����һ��
				{
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
	
	
	public static void main(String []args)
	{
		new ServerMainFraim().startService();
	}

}
