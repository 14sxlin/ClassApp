package api.client.groupChatRoom;

import java.io.IOException;

import api.client.pubChatRoom.PubChatRoomLogic;
import gui.groupChatRoom.GroupChatGUI;
import headinfoFilter.HeadType;
import object.Server;

public class GroupChatRoomLogic extends PubChatRoomLogic {

	private GroupChatGUI gui;
	private String username;
	
	
	public GroupChatRoomLogic(Server server,GroupChatGUI gui) {
		this.gui = gui;
		super.jTextArea = gui.chatArea;
		super.setServer(server);
//		this.publogic = publogic;
		gui.setTitle("Ë½(×é)ÁÄ-----"+username);
	}

	@Override
	public void startConnectServer(String serverIp, int serverPort) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendLoginInfo(Server server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendLogoutInfo(Server server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveMessageFromServer(StringBuilder storeString) throws IOException {
		super.receiveMessageFromServer(storeString);
	}

	@Override
	public void sendMessageToServer(String message) {
		String groupMessage = HeadType.GROUP;
		for( int i=0; i<gui.groupmember.getModel().getSize(); i++)
		{
			groupMessage+=(gui.groupmember.getModel().getElementAt(i)+"&");
		}
		int index = groupMessage.length()-1;
		groupMessage = groupMessage.substring(0, index);
		groupMessage+=":"+message+"#";
		// TODO System Output Test Block
		System.out.println(" groupmessage =  "+groupMessage);
		super.sendMessageToServer(groupMessage);		
	}

	@Override
	public Server getServer() {
		// TODO Auto-generated method stub
		return null;
	}

}
