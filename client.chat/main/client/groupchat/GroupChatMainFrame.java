package main.client.groupchat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import api.client.groupChatRoom.GroupChatRoomLogic;
import api.client.pubChatRoom.PubChatRoomLogic;
import classapp.mainframe.ClassAppMainFrame;
import gui.groupChatRoom.GroupChatGUI;

public class GroupChatMainFrame {

	private GroupChatRoomLogic logic;
	private GroupChatGUI gui ;
	public GroupChatGUI getGui() {
		return gui;
	}


	private String[] nameList;
	private long mark;
	private StringBuilder sb ;

	public GroupChatMainFrame(long mark,String usernamelist,PubChatRoomLogic logic) {
		this.mark = mark;
		gui = new GroupChatGUI();
		this.logic = new GroupChatRoomLogic(mark, logic);
		sb = new StringBuilder();
		
		//更新列表
		nameList = buildeList(usernamelist);
		updateList();
		
		gui.sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!gui.textField.getText().equals(""))
				{	
					GroupChatMainFrame.this.logic.sendMessageWithName(gui.textField.getText());
				    gui.textField.setText("");
				}
			}
		});
		
		gui.textField.addKeyListener(new KeyListener() {
			
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(!gui.textField.getText().equals(""))
					{	GroupChatMainFrame.this.logic.sendMessageWithName(gui.textField.getText());
					    gui.textField.setText("");
					}
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

	}
	
	
	public void startReceiveMessage() throws IOException
	{
		logic.receiveMessageFromServer(sb);
	}
	
	/**
	 * 把username1&username2的字符串转换成数组
	 * @param username
	 * @return 名称数组
	 */
	private String[]  buildeList(String usernamelist)
	{
		String[] temp = usernamelist.split("&");
		if(temp != null)
		{
			return temp;
		}
		else throw new NullPointerException();
	}
	
	/**
	 * 把成员变量的名称数组转换成model
	 * 更新list的组件
	 */
	private void updateList()
	{
		if(gui != null)
		{
			DefaultListModel<String> model = new DefaultListModel<>();
			for(String name: nameList)
			{
				if(!name.equals(ClassAppMainFrame.username))//去掉自己的名字
					model.addElement(name);
			}
			gui.classmateList.setModel(model);
		}
	}
	
	/**
	 * 把信息添加到面板上去
	 * @param message
	 */
	public void updateTextPane(String message)
	{
		if(logic.isMyself(message))
		{
			try {
				int i = gui.textPane.getDocument().getLength();
				SimpleAttributeSet set = new SimpleAttributeSet();
				StyleConstants.setForeground(set, Color.red);
				gui.textPane.getDocument().insertString(i, message+"\n", set);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
				
		}else
		{
			
			try {
				int i = gui.textPane.getDocument().getLength();
				SimpleAttributeSet set = new SimpleAttributeSet();
				StyleConstants.setForeground(set, Color.blue);
				gui.textPane.getDocument().insertString(i, message+"\n", set);
				
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		gui.textPane.setCaretPosition(gui.textPane.getDocument().getLength());
	}
	
   public void updateList(String listString,boolean isIn)
   {
		synchronized (gui.classmateList) {
			if (isIn) //登入的情况
			{
				DefaultListModel<String> listModel = (DefaultListModel<String>) gui.classmateList.getModel();
				String[] names = listString.split("&");
				if (listModel == null)
					listModel = new DefaultListModel<>();
				for (int i = 0; i < names.length; i++) {
					if (!listModel.contains(names[i])) //让重复添加无效
						listModel.addElement(names[i]);
				}
				gui.classmateList.setModel(listModel);
			} else//登出的情况
			{
				DefaultListModel<String> listModel = (DefaultListModel<String>) gui.classmateList.getModel();
				String[] names = listString.split("&");
				if (listModel == null)
					listModel = new DefaultListModel<>();
				for (int i = 0; i < names.length; i++) {
					listModel.removeElement(names[i]);
				}
				gui.classmateList.setModel(listModel);
			}
		}
		
   }

   
	public long getMark() {
		return mark;
	}
	
	
}
