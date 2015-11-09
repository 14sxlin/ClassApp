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
 * ����������,���ͻ���ʹ�õ�
 * @author ��˼��
 *
 */
public class PublicChatRoomForUser extends JDialog {

	/**
	 * �汾1.0
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ���õ�,��¶�������ñ��������
	 */
	public  JTextArea jTextArea;
	
	/**
	 * ���õķ��Ͱ�ť,��¶����
	 */
	public  JButton sendButton;
	
	public JTextField jTextField;
	private JComboBox<String>searchCombo;	
	private JToolBar toolBar;
	private JRadioButton online,all;
	private JList<String> classmateList;
	private DefaultListModel< String> classModel;
	
	/**
	 * Ĭ�ϵĹ��캯��,����һ�����������ҵĴ���
	 * @param host ָ����parent����,����Ϊnull
	 */
	public PublicChatRoomForUser(JFrame host) {
		
		guiDesign(host);
	}
	
	
	/**
	 * gui���趨
	 * @param host ָ��������
	 */
	private void guiDesign(JFrame host)
	{
		this.setTitle("������һ��-Ⱥ��");
		this.setSize(500, 400);
		this.setLocationRelativeTo(host);
		
		GridBagLayout bagLayout=new GridBagLayout();
		this.setLayout(bagLayout);
		
		//�ı�չʾ��
		GridBagConstraints constraints=new 
				GridBagConstraints(0, 0, 6, 8, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		jTextArea=new JTextArea();
		jTextArea.setEditable(false);
		this.add(new JScrollPane(jTextArea), constraints);
		
		//�ı������
		jTextField=new JTextField();
		constraints=new GridBagConstraints(0, 9, 4, 1, 1, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		this.add(jTextField, constraints);		
		
		//���Ͱ�ť
		constraints=new GridBagConstraints(5, 9, 1, 1, 0, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		sendButton=new JButton("����");
		this.add(sendButton, constraints);
		
		//������
		constraints=new GridBagConstraints(6, 0, 2, 1, 0, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		searchCombo=new JComboBox<>();
		this.add(searchCombo, constraints);
		
		//��ѡ��ť
		constraints=new GridBagConstraints(6, 1, 2, 1, 0, 0, GridBagConstraints.CENTER, 
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
		this.add(jPanel, constraints);
		
		//ͬѧ�б�
		constraints=new GridBagConstraints(6, 2, 2, 8, 0, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		classModel=new DefaultListModel<String>();
		classmateList=new JList<String>(classModel);
		classmateList.setBackground(Color.lightGray);
		this.add(classmateList, constraints);
		
		//������
		toolBar=new JToolBar();
		constraints=new GridBagConstraints(0, 10, 8, 1, 0, 0.1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		this.add(toolBar, constraints);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
}
