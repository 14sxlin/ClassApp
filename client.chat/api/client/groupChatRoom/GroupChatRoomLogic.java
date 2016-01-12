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
//							//通知gui中的list变化
//							gui.classmateList.setModel(ListInfoProcesser.createListModel(userName,line));
//							
//						} catch (NullPointerException e) {
//							e.printStackTrace();
//						}
//						storeString.append(line + "\n");
//						gui.textPane.setCaretPosition(gui.textPane.getDocument().getLength());
//					}					
//					else//实际上是有调用这个方法?  实际上是没有调用的// TODO Auto-generated catch block
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
//							System.out.println(" 有点用到内部方法 设想成立!!!!!!!! ");
//					}
//				}
//			}else throw new NullPointerException();
//		} catch (SocketException e) {
//			gui.textPane.setText("与服务断开连接\n");
//		}
//	}

	@Override
	/**
	 * 默认是已经建好了组了
	 * 组聊的逻辑在点击邀请组聊的逻辑里面实现
	 */
	public void sendMessageToServer(String message) {
		pubLogic.sendMessageToServer(message);
	}
	
	/**
	 * 发送具有gsend头的信息
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
	 * 加上自己的名字
	 * @param message
	 */
	public void sendMessageWithName(String message)
	{
		String groupMessage = HeadType.GSEND;
		groupMessage+="!"+this.mark;
		groupMessage+=":"+ClassAppMainFrame.username+"  说: "+message+"#";
		sendMessageToServer(groupMessage);
	}
	

	@Override
	public Server getServer() {
		return pubLogic.getServer();
	}

}
