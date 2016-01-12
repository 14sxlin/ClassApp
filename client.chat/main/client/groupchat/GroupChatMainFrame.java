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
		
		//�����б�
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
	 * ��username1&username2���ַ���ת��������
	 * @param username
	 * @return ��������
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
	 * �ѳ�Ա��������������ת����model
	 * ����list�����
	 */
	private void updateList()
	{
		if(gui != null)
		{
			DefaultListModel<String> model = new DefaultListModel<>();
			for(String name: nameList)
			{
				if(!name.equals(ClassAppMainFrame.username))//ȥ���Լ�������
					model.addElement(name);
			}
			gui.classmateList.setModel(model);
		}
	}
	
	/**
	 * ����Ϣ��ӵ������ȥ
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
			if (isIn) //��������
			{
				DefaultListModel<String> listModel = (DefaultListModel<String>) gui.classmateList.getModel();
				String[] names = listString.split("&");
				if (listModel == null)
					listModel = new DefaultListModel<>();
				for (int i = 0; i < names.length; i++) {
					if (!listModel.contains(names[i])) //���ظ������Ч
						listModel.addElement(names[i]);
				}
				gui.classmateList.setModel(listModel);
			} else//�ǳ������
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
