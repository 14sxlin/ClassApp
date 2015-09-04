package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

public class GroupChat extends JDialog implements ActionListener {
	private JMenu jMenus[];
	private String [] strMenus= {"设置颜色","导出聊天记录","邀请加入"};
	private JMenuBar bar;
	private JTextPane chatArea;
	private JTextField chatFiled;
	private JButton sendButton,faceButton;
	private JList<String> groupmember;
	private DefaultListModel<String>listModel;
	public GroupChat(JFrame jframe) {
		this.setTitle("私/组聊");
		LoginDialog.dim=getToolkit().getScreenSize();
		this.setBounds(LoginDialog.dim.width/2-250, LoginDialog.dim.height/2-200, 500, 400);

		
		//增加菜单栏
		bar=new JMenuBar();
		this.setJMenuBar(bar);
		jMenus=new JMenu[strMenus.length];
		for(int i=0;i<strMenus.length;i++)
		{
			jMenus[i]=new JMenu(strMenus[i]);
			bar.add(jMenus[i]);
			jMenus[i].addActionListener(this);
		}
		
		//增加聊天面板
		chatArea=new JTextPane();
		SimpleAttributeSet set=new SimpleAttributeSet();
		Document doc=chatArea.getDocument();
		int length=doc.getLength();
		
		//增加按钮
		JPanel panel=new JPanel();
		panel.add(chatFiled=new JTextField(30));
		panel.add(sendButton=new JButton("发送"));
		panel.add(faceButton=new JButton("表情"));
		this.add(panel, "South");
		
		//增加成员面板
		groupmember=new JList<String>(listModel=new DefaultListModel<>());
		
		//分割面板
		JSplitPane jSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chatArea, groupmember);
		jSplitPane.setOneTouchExpandable(true);
		this.setVisible(true);
		jSplitPane.setDividerLocation(this.getWidth()*2/3);
		this.add(jSplitPane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GroupChat(null);
	}

}
