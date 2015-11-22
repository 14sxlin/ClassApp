package gui.pubChatRoom.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import login.LoginDialog;


/**
 * 主窗口,客户使用
 * @author 林思鑫
 *
 */
@SuppressWarnings("serial")
public class ClassAppMainFrame extends JFrame implements ActionListener{
	
	public static boolean admim;
	private JPanel messageArea,buttonArea;
	private JList<String> classList,stuList;
	private DefaultListModel< String> classModel,stuModel;
	private String [] buttonstr= {"聊天室","文件互传","发送公告"};
	private JButton [] buttons;
	private JToolBar toolbar;
	
	/**
	 * 默认的构造函数
	 */
	public ClassAppMainFrame() {
		guiDesign();
	}
	
	/**
	 * 界面布置
	 */
	private void guiDesign()
	{
		this.setTitle("我们这一班");
		LoginDialog.dim=getToolkit().getScreenSize();
		this.setBounds(LoginDialog.dim.width/2-250, LoginDialog.dim.height/2-200, 500, 400);
		
		//添加公告栏
		messageArea=new JPanel(new GridLayout(1, 2));
		messageArea.setBorder(new TitledBorder("公告栏"));
		classModel=new DefaultListModel<>();
		stuModel=new DefaultListModel<>();
		classList=new JList<>(classModel);
		stuList=new JList<>(stuModel);
		messageArea.add(classList);
		messageArea.add(stuList);
		
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
		jsplit.setDividerLocation(200);
		this.add(jsplit);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ClassAppMainFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
