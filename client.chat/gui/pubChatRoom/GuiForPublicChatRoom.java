package gui.pubChatRoom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import object.ChatDialog;
import object.ClassmateListRender;

/**
 * ����������,���ͻ���ʹ�õ�
 * @author ��˼��
 *
 */
public class GuiForPublicChatRoom extends ChatDialog {

	/**
	 * �汾1.0
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ���õ�,��¶�������ñ��������
	 */
	public  JTextPane my_jtextPane;
	
	/**
	 * ���õķ��Ͱ�ť,��¶����
	 */
	public  JButton sendButton;
	
	/**
	 * �������ĵİ�ť
	 */
	public JButton joinGroupButton;
	
	public JTextField jTextField;
	private JToolBar toolBar;
	private JRadioButton online,all;
	public JList<String> classmateList;
	
	
	/**
	 * Ĭ�ϵĹ��캯��,����һ�����������ҵĴ���
	 * @param username �û���
	 */
	public GuiForPublicChatRoom(String username) {
		
		guiDesign(username);
		
	}
	
	
	/**
	 * gui���趨
	 * @param host ָ��������
	 */
	private void guiDesign(String username)
	{
		this.setTitle("Ⱥ��---"+username);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int) (dim.getWidth()/2.5), (int) (dim.getHeight()/2));
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		
		//��ߵ����
		GridBagLayout bagLayout=new GridBagLayout();
		JPanel panel1 = new JPanel(bagLayout);
		
		//�ı�չʾ��
		GridBagConstraints constraints=new 
				GridBagConstraints(0, 0, 6, 8, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		my_jtextPane = new JTextPane();
		my_jtextPane.setEditable(false);
//		jTextArea.setPreferredSize(new Dimension(55, 50));
		panel1.add(new JScrollPane(my_jtextPane), constraints);
		
		//�ı������
		jTextField=new JTextField();
		constraints=new GridBagConstraints(0, 9, 4, 1, 0.7, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		panel1.add(jTextField, constraints);		
		
		//���Ͱ�ť
		constraints=new GridBagConstraints(5, 9, 1, 1, 0.1, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		sendButton=new JButton("����");
		panel1.add(sendButton, constraints);
		
		
		//�ұߵ����
		bagLayout=new GridBagLayout();
		JPanel panel2 = new JPanel(bagLayout);
		
		//��ѡ��ť
		constraints=new GridBagConstraints(0, 0, 1, 1, 0, 0.1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		ButtonGroup buttonGroup=new ButtonGroup();
		online=new JRadioButton("����");
		all=new JRadioButton("ȫ��");
		buttonGroup.add(online);
		buttonGroup.add(all);
		all.setSelected(true);
		JPanel jPanel=new JPanel();
		jPanel.add(new JLabel("��ʾ:"));
		jPanel.add(online);
		jPanel.add(all);
		panel2.add(jPanel, constraints);
		
		//ͬѧ�б�
		constraints=new GridBagConstraints(0, 4, 1, 5, 0,  7, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		classmateList = new JList<>(new DefaultListModel<>());
		classmateList.setCellRenderer(new ClassmateListRender(username));
		classmateList.setToolTipText("ʹ��crtl+�����ѡ,����Ҽ�����ȫ��ѡ��");
		JScrollPane sc = new JScrollPane(classmateList);
		panel2.add(sc, constraints);
		classmateList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1)
				{
				}else
				{
					classmateList.clearSelection();
				}
			}
		});
		
		//�������ĵİ�ť
		constraints=new GridBagConstraints(0, 2, 1, 1, 0, 0.1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		joinGroupButton = new JButton("�����������");
		panel2.add(joinGroupButton, constraints);
		

		//�����û��Լ��ı�ǩ
		constraints=new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		JLabel label = new JLabel(username+"(��)");
		label.setHorizontalAlignment(JLabel.CENTER);
		panel2.add(label, constraints);
		
		//��ӷָ��
		JSplitPane jsplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				panel1, panel2);
		jsplit.setDividerLocation(this.getWidth()*7/10);
		jsplit.setDividerSize(1);
		this.add(jsplit, "Center");
		
		//������
		toolBar=new JToolBar();
		this.add(toolBar,"South");
		
		//ֻ������,���㵱���˷�����Ϣ������ʱ���ܹ����ܵ�
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}
	
	public static void main(String[]  args)
	{
		new GuiForPublicChatRoom("").setVisible(true);
	}
	
}
