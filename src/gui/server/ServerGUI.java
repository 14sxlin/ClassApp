package gui.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import server_client.ServerInfo;
import server_client.TcpSocketServer;
import threadData.ThreadDataTransfer;

public class ServerGUI {
	/**
	 * �û�ͼ�ν���
	 */
	MainFrameForServer gui;
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
	public ServerGUI() {
		gui=new MainFrameForServer();
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
		
//		synchronized(gui.counterTextField)
//		{
//			Timer timer=new Timer(5, new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					//������ʾ��״̬
//					server.setState( gui.counterTextField ,  gui.listmodel);
//				}
//			});
//			timer.start();
//		}
	}
	
	public static void main(String []args)
	{
		new ServerGUI();
	}
}
