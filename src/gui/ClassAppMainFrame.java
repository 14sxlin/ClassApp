package gui;

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

@SuppressWarnings("serial")
public class ClassAppMainFrame extends JFrame implements ActionListener{
	/*
	 * 班级软件的主面板
	 */
	public static boolean admim;
	private JPanel messageArea,buttonArea;
	private JList<String> classList,stuList;
	private DefaultListModel< String> classModel,stuModel;
	private String [] buttonstr= {"聊天室","文件互传","发送公告"};
	private JButton [] buttons;
	private JToolBar toolbar;
	public ClassAppMainFrame() {
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
	
	public void setAdmin()//设置admin的值
	{
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClassAppMainFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
