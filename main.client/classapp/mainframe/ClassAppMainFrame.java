package classapp.mainframe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import classapp.login.LoginDialog;
import gui.OfficeInfoListPanel;
import main.clietn.pubchat.PubChatRoomMainFrame;
import object.GroupsChatManager;
import object.LogoutEvent;


/**
 * 主窗口,客户使用
 * @author 林思鑫
 *
 */
@SuppressWarnings("serial")
public class ClassAppMainFrame extends JFrame implements ActionListener{
	
	public static String username;
	public static GroupsChatManager groupChatManager;
	private JPanel messageArea,buttonArea;
	private String [] buttonstr= {"聊天室","文件互传","发送公告"};
	private JButton [] buttons;
	private JToolBar toolbar;
	private PubChatRoomMainFrame pubChatRoom;
	private OfficeInfoListPanel officePanel;
	
	/**
	 * 默认的构造函数
	 */
	public ClassAppMainFrame(String username) {
		ClassAppMainFrame.username = username;
		groupChatManager = new GroupsChatManager();
		guiDesign();
		this.addWindowListener(new LogoutEvent(pubChatRoom.getClient()));
	}
	
	/**
	 * 界面布置
	 */
	private void guiDesign()
	{
		this.setTitle("我们这一班--"+username);
		LoginDialog.dim=getToolkit().getScreenSize();
		this.setBounds(LoginDialog.dim.width/2-250, LoginDialog.dim.height/2-200, 500, 400);
		
		//添加公告栏
		messageArea=new JPanel(new GridLayout(1, 2));
		messageArea.setBorder(new TitledBorder("公告栏"));
		officePanel = new OfficeInfoListPanel();
		messageArea.add(officePanel);
		
		//添加按钮区
		buttonArea=new JPanel();
		buttons=new JButton[buttonstr.length];
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i]=new JButton(buttonstr[i]);
			buttonArea.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
		//添加工具栏
		toolbar=new JToolBar();
		this.add(toolbar,"South");
		toolbar.add(new JButton("签到"));
		
		//添加组件
		JSplitPane jsplit=new JSplitPane(JSplitPane.VERTICAL_SPLIT, messageArea, buttonArea);
		jsplit.setDividerLocation(this.getHeight()*2/3);
		this.add(jsplit);
		
		//构造聊天室界面,但是一开始不可见
		pubChatRoom = new PubChatRoomMainFrame(username);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

//	public static void main(String[] args) {
//		new ClassAppMainFrame("2223");
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getActionCommand().equals("聊天室"))
		{
			pubChatRoom.gui.setVisible(true);
		}
	}
}
