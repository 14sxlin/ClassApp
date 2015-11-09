package gui.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import server_client.ServerInfo;
import server_client.TcpSocketServer;
import threadData.ThreadDataTransfer;

public class ServerGUI {
	/**
	 * 用户图形界面
	 */
	MainFrameForServer gui;
	/**
	 * 服务器变量
	 */
	private TcpSocketServer server;
	
	/**
	 * 用来与server中的线程的数据交互
	 */
	private ThreadDataTransfer tdt;
	
	/**
	 * 默认的构造方法
	 */
	public ServerGUI() {
		gui=new MainFrameForServer();
		tdt=new ThreadDataTransfer();
		tdt.setField(gui.counterTextField, gui.listmodel);
		
		//注册监听器,点击开启服务的时候启动线程,并控制关闭线程
		gui.startServiceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("开启服务"))
				{
					gui.startServiceButton.setText("关闭服务");
					Thread thread=new Thread(new Runnable() {
						
						@Override
						public void run() {
							server=new TcpSocketServer(tdt,gui.textPane);	
							server.startService(ServerInfo.PORT);
						}
					});
					thread.start();					
				}
				else//这个需要完善一下
				{
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
		
//		synchronized(gui.counterTextField)
//		{
//			Timer timer=new Timer(5, new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					//设置显示的状态
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
