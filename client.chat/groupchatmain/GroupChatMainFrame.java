package groupchatmain;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import groupChatRoomGui.GroupChatGUI;
import groupChatRoomLogic.GroupChatRoomLogic;
import mainframe.ClassAppMainFrame;
import object.HeadType;
import pubChatLogic.PubChatRoomLogic;

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

		addGroupoutListener();
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
	
	/**
	 * 更新组聊面板的讨论组成员
	 * @param listString 成员列表字符串
	 * @param isLogin 是不是登录
	 */
   public void updateList(String listString,boolean isLogin)
   {
	   // TODO System Output Test Block
	System.out.println(" 更新列表接收到的列表信息 =  "+listString);
		synchronized (gui.classmateList) {
			if (isLogin) //登入的情况
			{
				DefaultListModel<String> listModel = (DefaultListModel<String>) gui.classmateList.getModel();
				String[] names = listString.split("&");
				if (listModel == null)
					listModel = new DefaultListModel<>();
				for (int i = 0; i < names.length; i++) {
					if (!listModel.contains(names[i].trim())) //让重复添加无效
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
   
   /**
    * 给gui添加退出的时候发送组聊中的退出信息的监听器
    * 并将chatgroup中对应的组聊去掉
    */
   private void addGroupoutListener()
   {
	   gui.addWindowListener(new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			String groupoutinfo = HeadType.GOUT+getMark()+":"+ClassAppMainFrame.username+"#";
			// TODO System Output Test Block
			System.out.println(" group out info =  "+groupoutinfo+"\n");
			logic.sendMessageToServer(groupoutinfo);
			ClassAppMainFrame.groupChatManager.remove(getMark());
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
   }

   
	public long getMark() {
		return mark;
	}
	
	
}
