package api.client.pubChatRoom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JList;

import classapp.mainframe.ClassAppMainFrame;
import gui.pubChatRoom.GuiForPublicChatRoom;
import headinfoFilter.HeadType;
import object.AsClient;
import object.Server;
import tools.GroupChatProcesser;
import tools.ListInfoProcesser;

/**
 * �ͻ��˵�socket����
 * @author ��˼��
 *
 */
public class PubChatRoomLogic implements AsClient {
	/**
	 * �ͻ����û�������,������־�ͻ���
	 */
	protected String userName;
	
	/**
	 * ����������
	 */
	protected Server server;
	
	public void setServer(Server server) {
		this.server = server;
	}
	
	/**
	 * ������ʾ���߻����ߵ��û�
	 */
	public JList<String> clientList;
	
	/**
	 * ��gui����ͨ��,һ������
	 */
	public GuiForPublicChatRoom gui;
	/**
	 * ���췽��
	 * @param userName ����ͻ��˵��û���
	 */
	public PubChatRoomLogic(String userName,GuiForPublicChatRoom gui) {
		this.userName=userName;
		this.gui = gui;
	}
	
	public PubChatRoomLogic() {
	}
	
	/**
	 * ��ʼ���ӷ�����,����ͷ��Ϣ���������������
	 * @param serverIp ��������ip��ַ
	 * @param serverPort �������ķ���˿�
	 * @throws IOException �׳��쳣�õ��������ദ��
	 */
	@Override
	public void startConnectServer(String serverIp,int serverPort) throws IOException
	{
		try {
			//�������ӷ�����
			server=new Server( new Socket(serverIp, serverPort));
			
			//����ͷ��Ϣ
			sendLoginInfo(server);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void sendLoginInfo(Server server)
	{
		//Ŀǰֻ��������Ϣ
		server.getSocketStream().getPrintWriter().println(HeadType.LOGIN+this.userName+"#");
		server.getSocketStream().getPrintWriter().flush();
	}
	
	@Override
	public void sendLogoutInfo(Server server) {
		server.getSocketStream().getPrintWriter().println(HeadType.LOGOUT+this.userName+"#");
		server.getSocketStream().getPrintWriter().flush();
	}

	/**
	 * ��������������Ϣ
	 * @param message Ҫ���͵���Ϣ
	 */
	@Override
	public void sendMessageToServer(String message)
	{
		if(server.getSocketStream() != null)
		{
			PrintWriter pw=server.getSocketStream().getPrintWriter();
			pw.println(message);
			pw.flush();
		}
	}
	
	/**
	 * �����Լ�������
	 * @param message
	 */
	public void sendMessageWithName(String message)
	{
		sendMessageToServer(ClassAppMainFrame.username+" ˵:  "+message);
	}


	/**
	 * ���շ��������͹�������Ϣ
	 * ����ǲ���ͷ��Ϣ,�����ͷ��ϢҪ������Ӧ�Ĵ���
	 * @param storeString ����������յ�����Ϣ
	 * @exception IOException ���������Ͽ����ӵ�ʱ��ᴥ�����
	 */
	@Override
	public void receiveMessageFromServer(StringBuilder storeString) throws IOException {
		try {
			if (server != null && server.getSocketStream()!=null) {
				String line="";
				while ((line = server.getSocketStream().getBufferReader().readLine()) != null) {
					if( line.contains(HeadType.LIST))
					{
						try {
							
							//֪ͨgui�е�list�仯
							gui.classmateList.setModel(ListInfoProcesser.createListModel(userName,line));
							
						} catch (NullPointerException e) {
							e.printStackTrace();
						}
						storeString.append(line + "\n");
						gui.jTextArea.append("���յ�list��Ϣ"+"\n");
						gui.jTextArea.append(line+"\n");
					}
					else if( line.contains(HeadType.GROUP) ||  line.contains(HeadType.GSEND))//����������Ϣ
					{
						GroupChatProcesser.process(line, this);
						gui.jTextArea.append("���յ�group��Ϣ"+"\n");
						gui.jTextArea.append(line+"\n");
					}
					
					else
					{
						storeString.append(line + "\n");
						gui.jTextArea.append(line+"\n");
					}
					
				} 
			}
		} catch (SocketException e) {
			gui.jTextArea.setText("�����Ͽ�����\n");
		}
	}

	public Server getServer() {
		return this.server;
	}
	
}
