package api.client.pubChatRoom;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JList;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import classapp.mainframe.ClassAppMainFrame;
import gui.pubChatRoom.GuiForPublicChatRoom;
import object.AsClient;
import object.HeadType;
import object.Server;
import tools.GroupChatListProcesser;
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
		try {
			server.getSocketStream().getPrintWriter().println(HeadType.LOGIN+this.userName+"#");
			server.getSocketStream().getPrintWriter().flush();
		} catch (NullPointerException e) {
		}
	}
	
	@Override
	public void sendLogoutInfo(Server server) {
		try {
			server.getSocketStream().getPrintWriter().println(HeadType.LOGOUT+this.userName+"#");
			server.getSocketStream().getPrintWriter().flush();
		} catch (NullPointerException e) {
		}
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
					try {
						
						// TODO System Output Test Block
						addStringWithColor(line, Color.cyan);
						
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if( line.contains(HeadType.LIST))
					{
						try {
							
							//֪ͨgui�е�list�仯
							gui.classmateList.setModel(ListInfoProcesser.createListModel(userName,line));
							
						} catch (NullPointerException e) {
							e.printStackTrace();
						}
						storeString.append(line + "\n");
					}
					else if( line.contains(HeadType.GROUP) ||  line.contains(HeadType.GSEND) )//����������Ϣ
					{
						GroupChatProcesser.process(line, this);
					}
					else if(line.contains(HeadType.GIN) ||  line.contains(HeadType.GOUT))//��������˳�����Ϣ
					{
						GroupChatListProcesser.updateList(line);
					}
					else
					{
						storeString.append(line + "\n");
						if(isMyself(line))
						{
							try {
								addStringWithColor(line , Color.blue);
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
								
						}else
						{
							
							try {
								addStringWithColor(line, Color.red);
								
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
						}
						
					}
					try {
						gui.my_jtextPane.setCaretPosition(gui.my_jtextPane.getDocument().getLength());
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						//ʲô���?
						e.printStackTrace();
					}
				} 
			}
		} catch (SocketException e) {
			gui.my_jtextPane.setText("�����Ͽ�����\n");
		}
	}

	private void addStringWithColor(String line,Color color) throws BadLocationException {
		int i = gui.my_jtextPane.getDocument().getLength();
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setForeground(set, color);
		gui.my_jtextPane.getDocument().insertString(i, line+"\n", set);
	}

	public Server getServer() {
		return this.server;
	}

	@Override
	public boolean isMyself(String message) {
		
		if(ClassAppMainFrame.username == null)
			return false;
		else
		{
			int i = message.indexOf(" ˵");
			if(i != -1)
			{
				 try {
					if (message.substring(i-ClassAppMainFrame.username.length() , i).equals(ClassAppMainFrame.username ))
					{	
						return true;
					}
						else return false;
				} catch (Exception e) {
					
					return false;
				}
				
			}else return false;
			
		}
	}
	
}
