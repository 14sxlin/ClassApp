package gui.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * 公共聊天室,给客户端使用的
 * @author 林思鑫
 *
 */
public class PublicChatRoomForUser extends JDialog {

	/**
	 * 版本1.0
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 公用的,暴露在外面让别的类引用
	 */
	public  JTextArea jTextArea;
	
	/**
	 * 公用的发送按钮,暴露在外
	 */
	public  JButton sendButton;
	
	public JTextField jTextField;
	private JComboBox<String>searchCombo;	
	private JToolBar toolBar;
	private JRadioButton online,all;
	private JList<String> classmateList;
	private DefaultListModel< String> classModel;
	
	/**
	 * 默认的构造函数,创建一个公共聊天室的窗口
	 * @param host 指定了parent容器,可以为null
	 */
	public PublicChatRoomForUser(JFrame host) {
		
		guiDesign(host);
	}
	
	
	/**
	 * gui的设定
	 * @param host 指定父容器
	 */
	private void guiDesign(JFrame host)
	{
		this.setTitle("我们这一班-群聊");
		this.setSize(500, 400);
		this.setLocationRelativeTo(host);
		
		GridBagLayout bagLayout=new GridBagLayout();
		this.setLayout(bagLayout);
		
		//文本展示区
		GridBagConstraints constraints=new 
				GridBagConstraints(0, 0, 6, 8, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		jTextArea=new JTextArea();
		jTextArea.setEditable(false);
		this.add(new JScrollPane(jTextArea), constraints);
		
		//文本输入框
		jTextField=new JTextField();
		constraints=new GridBagConstraints(0, 9, 4, 1, 1, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		this.add(jTextField, constraints);		
		
		//发送按钮
		constraints=new GridBagConstraints(5, 9, 1, 1, 0, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		sendButton=new JButton("发送");
		this.add(sendButton, constraints);
		
		//搜索框
		constraints=new GridBagConstraints(6, 0, 2, 1, 0, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		searchCombo=new JComboBox<>();
		this.add(searchCombo, constraints);
		
		//单选按钮
		constraints=new GridBagConstraints(6, 1, 2, 1, 0, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		ButtonGroup buttonGroup=new ButtonGroup();
		online=new JRadioButton("在线");
		all=new JRadioButton("全部");
		buttonGroup.add(online);
		buttonGroup.add(all);
		all.setSelected(true);
		JPanel jPanel=new JPanel();
		jPanel.add(new JLabel("显示:"));
		jPanel.add(online);
		jPanel.add(all);
		this.add(jPanel, constraints);
		
		//同学列表
		constraints=new GridBagConstraints(6, 2, 2, 8, 0, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		classModel=new DefaultListModel<String>();
		classmateList=new JList<String>(classModel);
		classmateList.setBackground(Color.lightGray);
		this.add(classmateList, constraints);
		
		//工具栏
		toolBar=new JToolBar();
		constraints=new GridBagConstraints(0, 10, 8, 1, 0, 0.1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		this.add(toolBar, constraints);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
}
