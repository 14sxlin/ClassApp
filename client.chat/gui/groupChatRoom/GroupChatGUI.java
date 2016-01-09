package gui.groupChatRoom;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import classapp.login.LoginDialog;
import classapp.mainframe.ClassAppMainFrame;
import object.ChatDialog;

/**
 * 组聊的窗口,给客户端使用的
 * @author 林思鑫
 *
 */
@SuppressWarnings("serial")
public class GroupChatGUI extends ChatDialog  {
	
	// TODO Auto-generated catch block
//	private JButton[] buttons;
//	private String [] strButton= {"导出聊天记录","邀请加入"};
	
	private JToolBar bar;
	private JLabel melabel;
	
	/**
	 * 默认的构造函数
	 */
	public GroupChatGUI() {
		super();
		setGui();
	}
	
	private void setGui()
	{
		this.setTitle("私/组聊-----"+ ClassAppMainFrame.username);
		LoginDialog.dim=getToolkit().getScreenSize();
		this.setBounds(LoginDialog.dim.width/2-250, LoginDialog.dim.height/2-200, 500, 400);
//		myColor=Color.blue;
//		otherColor=Color.red;
		
		//增加菜单栏
		bar=new JToolBar();
		this.add(bar,"North");
//		buttons=new JButton[strButton.length];
//		for(int i=0;i<strButton.length;i++)
//		{
//			buttons[i]=new JButton(strButton[i]);
//			bar.add(buttons[i]);
//			buttons[i].addActionListener(this);
//		}
		
		//增加聊天面板
		super.textArea.setEditable(false);
		
		//增加按钮
		JPanel panel=new JPanel();
		panel.add(super.textField);
		panel.add(sendButton=new JButton("发送"));
//		panel.add(faceButton=new JButton("表情"));
//		faceButton.addActionListener(this);
		this.add(panel, "South");
		
		JPanel memberpanel = new JPanel();
		memberpanel.setLayout(new BorderLayout());
		//增加自己用户名的标签
		melabel = new JLabel(ClassAppMainFrame.username+"(我)");
		melabel.setAlignmentX(CENTER_ALIGNMENT);
		memberpanel.add(melabel,"North");
		
		//增加成员面板
		classmateList=new JList<String>(new DefaultListModel<String>());
		memberpanel.add(classmateList,"Center");
		memberpanel.setBorder(new TitledBorder("讨论成员"));
		
		
		//分割面板
		JSplitPane jSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, super.textArea, memberpanel);
		jSplitPane.setOneTouchExpandable(true);
		this.setVisible(true);
		jSplitPane.setDividerLocation(this.getWidth()*2/3);
		this.add(jSplitPane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	//给JTextPanel设置文本,可以设置单行的颜色
	public void setText(String massage,Color color)
	{
		try {
			SimpleAttributeSet set=new SimpleAttributeSet();
			Document doc=super.textArea.getDocument();
			int length=doc.getLength();
			StyleConstants.setForeground(set, color);
			doc.insertString(length, massage, set);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GroupChatGUI();
	}

}
