package api.client.groupChatRoom;

import java.awt.Color;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import api.client.pubChatRoom.PubChatRoomLogic;
import headinfoFilter.HeadType;
import object.ChatDialog;
import object.Server;
import tools.ListInfoProcesser;

public class GroupChatRoomLogic extends PubChatRoomLogic{

	private ChatDialog gui;
	private PubChatRoomLogic pubLogic;
	private long mark;
	
	public GroupChatRoomLogic(long mark,ChatDialog gui,PubChatRoomLogic logic) {
		this.mark = mark;
		this.gui = gui;
		this.pubLogic = logic;
		this.server = logic.getServer();

	}

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
						gui.textPane.setCaretPosition(gui.textPane.getText().length());
					}					
					else//ʵ�������е����������?  ʵ������û�е��õ�// TODO Auto-generated catch block
					{
						storeString.append(line + "\n");
						if(isMyself(line))
						{
							try {
								int i = gui.textPane.getDocument().getLength();
								SimpleAttributeSet set = new SimpleAttributeSet();
								StyleConstants.setForeground(set, Color.red);
								gui.textPane.getDocument().insertString(i, line+"\n", set);
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
								
						}else
						{
							
							try {
								int i = gui.textPane.getDocument().getLength();
								SimpleAttributeSet set = new SimpleAttributeSet();
								StyleConstants.setForeground(set, Color.blue);
								gui.textPane.getDocument().insertString(i, line+"\n", set);
								
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
						}
						gui.textPane.setCaretPosition(gui.textPane.getDocument().getLength());
					}
				}
			}else throw new NullPointerException();
		} catch (SocketException e) {
			gui.textPane.setText("�����Ͽ�����\n");
		}
	}

	@Override
	/**
	 * Ĭ�����Ѿ�����������
	 * ���ĵ��߼��ڵ���������ĵ��߼�����ʵ��
	 */
	public void sendMessageToServer(String message) {
		String groupMessage = HeadType.GSEND;
		groupMessage+=""+this.mark;
		groupMessage+=":"+message+"#";
		
		pubLogic.sendMessageToServer(groupMessage);
	}
	
	/**
	 * �����Լ�������
	 * @param message
	 */
	public void sendMessageWithName(String message)
	{
		super.sendMessageWithName(message);
	}
	

	@Override
	public Server getServer() {
		return pubLogic.getServer();
	}

}
