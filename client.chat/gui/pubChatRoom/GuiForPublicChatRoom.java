package gui.pubChatRoom;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * 公共聊天室,给客户端使用的
 * @author 林思鑫
 *
 */
public class GuiForPublicChatRoom extends JDialog {

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
	
	/**
	 * 加入组聊的按钮
	 */
	private JButton joinGroupButton;
	
	public JTextField jTextField;
//	private JComboBox<String>searchCombo;	
	private JToolBar toolBar;
	private JRadioButton online,all;
	public JList<String> classmateList;
	
//	/**
//	 * 用来通知Jlist变化的类
//	 */
//	public static ClientGuiNotifier clientGuiNotifier;
	
	/**
	 * 默认的构造函数,创建一个公共聊天室的窗口
	 * @param username 用户名
	 */
	public GuiForPublicChatRoom(String username) {
		
		guiDesign(username);
//		clientGuiNotifier = new ClientGuiNotifier(classmateList);
		
	}
	
	
	/**
	 * gui的设定
	 * @param host 指定父容器
	 */
	private void guiDesign(String username)
	{
		this.setTitle("群聊---"+username);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		
		//左边的面板
		GridBagLayout bagLayout=new GridBagLayout();
		JPanel panel1 = new JPanel(bagLayout);
		
		//文本展示区
		GridBagConstraints constraints=new 
				GridBagConstraints(0, 0, 6, 8, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		jTextArea=new JTextArea();
		jTextArea.setEditable(false);
		panel1.add(new JScrollPane(jTextArea), constraints);
		
		//文本输入框
		jTextField=new JTextField();
		constraints=new GridBagConstraints(0, 9, 4, 1, 0.7, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		panel1.add(jTextField, constraints);		
		
		//发送按钮
		constraints=new GridBagConstraints(5, 9, 1, 1, 0.1, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		sendButton=new JButton("发送");
		panel1.add(sendButton, constraints);
		
		
		//右边的面板
		bagLayout=new GridBagLayout();
		JPanel panel2 = new JPanel(bagLayout);
		
		//单选按钮
		constraints=new GridBagConstraints(0, 0, 1, 1, 0, 0.1, GridBagConstraints.CENTER, 
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
		panel2.add(jPanel, constraints);
		
		//同学列表
		constraints=new GridBagConstraints(0, 3, 1, 5, 0,  7, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		classmateList = new JList<>(new DefaultListModel<>());
		classmateList.setCellRenderer(new ClassmateListRender(username));
		classmateList.setToolTipText("使用crtl+点击多选,鼠标右键撤销全部选中");
		panel2.add(new JScrollPane(classmateList), constraints);
		classmateList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1)
				{
					// TODO Auto-generated catch block
				}else
				{
					classmateList.clearSelection();
				}
			}
		});
		
		//加入组聊的按钮
		constraints=new GridBagConstraints(0, 2, 1, 1, 0, 0.1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		joinGroupButton = new JButton("邀请加入组聊");
		panel2.add(joinGroupButton, constraints);

		//添加分割窗口
		JSplitPane jsplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				panel1, panel2);
		jsplit.setDividerLocation(this.getWidth()*2/3);
		jsplit.setDividerSize(1);
		jsplit.setEnabled(false);
		this.add(jsplit, "Center");
		
		//工具栏
		toolBar=new JToolBar();
		this.add(toolBar,"South");
		
		//只是隐藏,方便当有人发送消息过来的时候能够接受到
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}
	
	public static void main(String[]  args)
	{
		new GuiForPublicChatRoom("").setVisible(true);
	}
	
}
