package mainframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.SocketException;

import gui.GuiForServer;
import object.ServerInfo;
import serverlogic.ServerLogic;
import threadData.ThreadDataTransfer;
import tools.ClientsManager;

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
	private ServerLogic server;
	
	/**
	 * �̶߳���
	 */
	private Thread currentThread;
	
	/**
	 * ������server�е��̵߳����ݽ���
	 */
	public static  ThreadDataTransfer tdt;
	
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public ServerMainFraim() {
		gui=new GuiForServer();
		addListener();
	}
	
	/**
	 * ��ʼ�ṩ����
	 * @param open trueʱֱ�ӿ������� false��Ҫ�ֶ�����
	 */
	public void startService(boolean open)
	{
		tdt=new ThreadDataTransfer();
		tdt.setField(gui.counterTextField, gui.listmodel);
		
		if(open)
		{
			runService();
		}
		
		//ע�������,������������ʱ�������߳�,�����ƹر��߳�
		gui.startServiceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("��������"))
				{
					runService();					
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
	 * ʵ��gui����ļ���
	 */
	private void addListener()
	{
		gui.sendbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientsManager.sendAllClient(gui.textField.getText(),true);
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
					ClientsManager.sendAllClient(gui.textField.getText(),true);
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
	
	/**
	 * ��ʼ���տͻ��˵�����
	 */
	private void runService() {
		gui.startServiceButton.setText("�رշ���");
		currentThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				server=new ServerLogic(gui);	
				try {
					server.startService(ServerInfo.PORT);
				} catch (SocketException e) {
					e.printStackTrace();
					gui.textPane.append("���������ٽ�������\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		currentThread.start();
	}
	
	public static void main(String []args)
	{
		new ServerMainFraim().startService(false);
	}

}
