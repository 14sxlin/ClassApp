package api.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.pubChatRoom.server.GuiForServer;
import headInfoProcesser.factory.ProcesserFactory;
import headInfoProcesser.processer.HeadInfoProcesser;
import headInfoProcesser.processer.LoginProcesser;
import headinfoFilter.HeadInfoFilter;
import object.Client;
import threadData.ThreadDataTransfer;
import tools.ClientsManager;

/**
 * ��������Socket����
 * @author ��˼��
 *
 */
public class ServerLogic implements AsServer{
	
	/**
	 * ��������ServerSocket
	 */
	private ServerSocket serverSocket;

	/**
	 * ������ͼ�ν��潻��
	 */
	private GuiForServer gui;
	
	/**
	 * ���������߳��е�ֵ������
	 */
	private ThreadDataTransfer tdt;
	
	/**
	 * ������鲢����ͷ��Ϣ
	 */
	private HeadInfoFilter filter;
	
	/**
	 * ����������Ӧ�Ĺ�����
	 */
	private ProcesserFactory factory;
	
//	/**
//	 * ���췽��
//	 * @param tdt �н����ݴ�����
//	 */
//	public ServerForPubChatRoom(ThreadDataTransfer tdt)  {
//		this.tdt=tdt;
//	}
	
	/**
	 * ���췽��
	 * @param textPane ������ʾ��Ϣ��,��Ҫ�ǲ���ʹ��
	 * @param tdt ����紫���߳��ڵ����ݵ��н�
	 */
	public ServerLogic(GuiForServer gui)  {
		this.gui = gui;
	}
	
	/**
	 * ��������,���տͻ��˵�����,���̵߳Ĵ���
	 * @param port ��ʹ�õĶ˿�
	 * @throws IOException 
	 */
	@Override
	public  void startService(int port) throws IOException
	{
			//�½�һ��serverSocket,���ܻ���ֶ˿��Ѿ���ռ�õ�����
			serverSocket=new ServerSocket(port);
			
			if ( gui.textPane != null )
				gui.textPane.append("���ڵȴ�����\n");
			
			startListen();
			
	}
	
	/**
	 * ��ʼ�����˿� �ÿͻ������ӽ���
	 * @throws IOException
	 */
	private void startListen() throws IOException
	{
		//��ʼ�����������µ��߳�,�������ӳɹ�����Ϣ,����ͻ��˷�����ͷ��Ϣ
		while(true)
		{
			Socket currentSocket = serverSocket.accept();
			Thread tempThread=new Thread(new Runnable() {
				@Override
				public void run() {
					
					Client client = null;
					try {
						client = new Client(currentSocket);
					} catch (IOException e2) {
						e2.printStackTrace();
					}

					
					//��ͻ��˷������ӳɹ�����Ϣ
					client.getSocketStream().getPrintWriter().println("���ӷ������ɹ�\n");
					client.getSocketStream().getPrintWriter().flush();
					
					//���տͻ��˷��͹�����ͷ��Ϣ
					//��Ϊ��һ�η���������Ϣ����ͷ��Ϣ,�������û���
					String line = null;
					try {
						line = client.getSocketStream().getBufferReader().readLine();
					
						if(gui.textPane != null)
							gui.textPane.append(line+"\n");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					//��ȡ�û���
					client.setUserName(searchUserName(line));
					
					synchronized (ClientsManager.class) {
						try {
							new LoginProcesser(ClientsManager.clientList).process(client);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						//���·��������������
						if (tdt != null)
							tdt.updateState(ClientsManager.clientList.size(), ClientsManager.userNameList);
						if (gui.textPane != null)
							gui.textPane.append("userNameList=" +ClientsManager.userNameList.toString()  + "\n");
					}
					
					//���տͻ��˵���Ϣ ������Ҫ����ͻ��˿��ܷ�������ͷ��Ϣ
					if(gui.textPane != null)
						try {
							while((line=client.getSocketStream().getBufferReader().readLine()) != null)
							{
								filter = new HeadInfoFilter(line);
								if(filter.isHeadInfo())
								{
									try {
										factory = new ProcesserFactory(ClientsManager.clientList);
										HeadInfoProcesser processer ;
										processer=factory.createProcesser(filter.filteType());	
										if(processer.getType() == HeadInfoProcesser.STRING)
											processer.process(filter.filteContent());
										else processer.process(client);
									} catch (Exception e) {
										e.printStackTrace();
									}
//									gui.textPane.append(line+"\n");
								}
								else
								{
//									gui.textPane.append(line+"\n");
									ClientsManager.sendAllClient(line);
								}
								
							}
						} catch (IOException e) {
							e.printStackTrace();
						}	
					
				}
			});
			tempThread.start();//�̱߳�������,�����һֱ����������
		}
	}
	
	/**
	 * ��ͷ��Ϣ�л�ȡ�û�����Ϣ
	 * @param ���ַ����н�ȡ�û���
	 * @return ���ؿͻ��˵��û���
	 */
	private String searchUserName(String line)
	{
		int i=line.indexOf("content=");
		int length="content=".length();
		if(i!=-1)
			return line.substring(i+length,line.indexOf("#",length));
		else
			return "";
	}

	
	
	/**
	 * ֹͣ���еķ���,���Ӧ�ø����еĿͻ��˷����˳���Ϣ,Ȼ���ÿͻ����˳�,֮���Լ���ֹͣsocket
	 * @throws IOException serverSocket.close()������Ĵ���,���������ߴ���
	 */
	public void stopService() throws IOException
	{
		//���Ĺر��е㸴�� ������Ϊ���Ĺر���������� ��Ҫ�ú��о�һ��
//		Iterator<Client> it = clientList.iterator();
//		while(it.hasNext())
//		{
//			it.next().getSocketStream().closeStream();
//		}
//		
		if( this.serverSocket != null)
		{
			this.serverSocket.close();//���ָ�����ǲ���ȥ�����Ǹ��˿���
		}
		
	}

	@Override
	public void registerUpdate() {
		// TODO Auto-generated method stub
		
	}
	

}

