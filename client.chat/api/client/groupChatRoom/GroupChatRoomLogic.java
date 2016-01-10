package api.client.groupChatRoom;

import java.io.IOException;
import java.net.SocketException;

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
							
							//通知gui中的list变化
							gui.classmateList.setModel(ListInfoProcesser.createListModel(userName,line));
							
						} catch (NullPointerException e) {
							e.printStackTrace();
						}
						storeString.append(line + "\n");
						gui.textArea.setCaretPosition(gui.textArea.getText().length());
					}					
					else//实际上是有调用这个方法?  实际上是没有调用的// TODO Auto-generated catch block
					{
						storeString.append(line + "\n");
						gui.textArea.append(line+"\n");
						gui.textArea.setCaretPosition(gui.textArea.getText().length());
					}
					
				} 
			}else throw new NullPointerException();
		} catch (SocketException e) {
			gui.textArea.setText("与服务断开连接\n");
		}
	}

	@Override
	/**
	 * 默认是已经建好了组了
	 * 组聊的逻辑在点击邀请组聊的逻辑里面实现
	 */
	public void sendMessageToServer(String message) {
		String groupMessage = HeadType.GSEND;
		groupMessage+=""+this.mark;
		groupMessage+=":"+message+"#";
		
		pubLogic.sendMessageToServer(groupMessage);
	}
	
	/**
	 * 加上自己的名字
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
