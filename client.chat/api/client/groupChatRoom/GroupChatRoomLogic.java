package api.client.groupChatRoom;

import api.client.pubChatRoom.PubChatRoomLogic;
import classapp.mainframe.ClassAppMainFrame;
import object.HeadType;
import object.Server;

public class GroupChatRoomLogic extends PubChatRoomLogic{

	private PubChatRoomLogic pubLogic;
	private long mark;
	
	public GroupChatRoomLogic(long mark,PubChatRoomLogic logic) {
		this.mark = mark;
		this.pubLogic = logic;
		this.server = logic.getServer();

	}

//	@Override
//	public void receiveMessageFromServer(StringBuilder storeString) throws IOException {
//		try {
//			if (server != null && server.getSocketStream()!=null) {
//				String line="";
//				while ((line = server.getSocketStream().getBufferReader().readLine()) != null) {
//					if( line.contains(HeadType.LIST))
//					{
//						try {
//							
//							//֪ͨgui�е�list�仯
//							gui.classmateList.setModel(ListInfoProcesser.createListModel(userName,line));
//							
//						} catch (NullPointerException e) {
//							e.printStackTrace();
//						}
//						storeString.append(line + "\n");
//						gui.textPane.setCaretPosition(gui.textPane.getDocument().getLength());
//					}					
//					else//ʵ�������е����������?  ʵ������û�е��õ�// TODO Auto-generated catch block
//					{
//						storeString.append(line + "\n");
//						if(isMyself(line))
//						{
//							try {
//								int i = gui.textPane.getDocument().getLength();
//								SimpleAttributeSet set = new SimpleAttributeSet();
//								StyleConstants.setForeground(set, Color.red);
//								gui.textPane.getDocument().insertString(i, line+"\n", set);
//							} catch (BadLocationException e) {
//								e.printStackTrace();
//							}
//								
//						}else
//						{
//							
//							try {
//								int i = gui.textPane.getDocument().getLength();
//								SimpleAttributeSet set = new SimpleAttributeSet();
//								StyleConstants.setForeground(set, Color.red);
//								gui.textPane.getDocument().insertString(i, line+"\n", set);
//								
//							} catch (BadLocationException e) {
//								e.printStackTrace();
//							}
//						}
//						gui.textPane.setCaretPosition(gui.textPane.getDocument().getLength());
//						
//							// TODO System Output Test Block
//							System.out.println(" �е��õ��ڲ����� �������!!!!!!!! ");
//					}
//				}
//			}else throw new NullPointerException();
//		} catch (SocketException e) {
//			gui.textPane.setText("�����Ͽ�����\n");
//		}
//	}

	@Override
	/**
	 * Ĭ�����Ѿ�����������
	 * ���ĵ��߼��ڵ���������ĵ��߼�����ʵ��
	 */
	public void sendMessageToServer(String message) {
		pubLogic.sendMessageToServer(message);
	}
	
	/**
	 * ���;���gsendͷ����Ϣ
	 * @param message
	 */
	public void sendCodedMessage(String message)
	{
		String groupMessage = HeadType.GSEND;
		groupMessage+="!"+this.mark;
		groupMessage+=":"+message+"#";
		sendMessageToServer(groupMessage);
	}
	
	/**
	 * �����Լ�������
	 * @param message
	 */
	public void sendMessageWithName(String message)
	{
		String groupMessage = HeadType.GSEND;
		groupMessage+="!"+this.mark;
		groupMessage+=":"+ClassAppMainFrame.username+"  ˵: "+message+"#";
		sendMessageToServer(groupMessage);
	}
	

	@Override
	public Server getServer() {
		return pubLogic.getServer();
	}

}
