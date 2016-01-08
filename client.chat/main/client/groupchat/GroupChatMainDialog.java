package main.client.groupchat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;

import api.client.groupChatRoom.GroupChatRoomLogic;
import gui.groupChatRoom.GroupChatGUI;
import object.Server;

public class GroupChatMainDialog {

	private GroupChatRoomLogic logic;
	private GroupChatGUI gui ;
	private String[] nameList;
	
	public GroupChatMainDialog(Server server,String myusername,String usernamelist) {
		gui = new GroupChatGUI(myusername);
		logic = new GroupChatRoomLogic(server,gui);
		nameList = buildeList(usernamelist);
		updateList();
		gui.sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!gui.chatFiled.getText().equals(""))
				{	logic.sendMessageToServer(gui.chatFiled.getText());
//				    gui.chatArea.setText(gui.chatFiled.getText());
				    gui.chatFiled.setText("");
				}
			}
		});
		gui.chatFiled.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(!gui.chatFiled.getText().equals(""))
					{	logic.sendMessageToServer(gui.chatFiled.getText());
					    gui.chatArea.setText(gui.chatFiled.getText());
					    gui.chatFiled.setText("");
					}
				}
				
			}
		});
	}
	
	
	private String[]  buildeList(String username)
	{
		String[] temp = username.split("&");
		if(temp != null)
		return temp;
		else throw new NullPointerException();
	}
	
	private void updateList()
	{
		if(gui != null)
		{
			DefaultListModel<String> model = new DefaultListModel<>();
			for(String name: nameList)
			{
				model.addElement(name);
			}
			gui.groupmember.setModel(model);
		}
	}
	
}
