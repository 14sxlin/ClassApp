package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class PublicChatRoom extends JDialog implements ActionListener {
	/*
	 * ����������
	 */
	public static boolean online_only;
	protected JTextArea jTextArea;
	private JTextField jTextField;
	private JButton sendButton;
	private JComboBox<String>searchCombo;	
	private JToolBar toolBar;
	private JRadioButton online,all;
	private JList<String> classmateList;
	private DefaultListModel< String> classModel;
	public PublicChatRoom(JFrame host) {
		this.setTitle("������һ��-Ⱥ��");
		this.setSize(500, 400);
		this.setLocationRelativeTo(host);
		
		GridBagLayout bagLayout=new GridBagLayout();
		this.setLayout(bagLayout);
		
		//������������
		GridBagConstraints constraints=new 
				GridBagConstraints(0, 0, 6, 8, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		jTextArea=new JTextArea();
		this.add(jTextArea, constraints);
		
		//�ı������
		jTextField=new JTextField();
		constraints=new GridBagConstraints(0, 9, 4, 1, 1, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		this.add(jTextField, constraints);		
		//���Ͱ�ť
		constraints=new GridBagConstraints(5, 9, 1, 1, 0, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		sendButton=new JButton("����");
		sendButton.addActionListener(this);
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
//		JScrollPane jScrollPane=new JScrollPane(classmateList);//����������֮���С��ʾ������
		classmateList.setBackground(Color.lightGray);
		this.add(classmateList, constraints);
		
		//������
		toolBar=new JToolBar();
		constraints=new GridBagConstraints(0, 10, 8, 1, 0, 0.1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
		this.add(toolBar, constraints);
		
		this.setOnline_Only();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void setOnline_Only()
	{
		if (all!=null) {
			if (all.isSelected())
				online_only = false;
			else
				online_only = true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PublicChatRoom(null);
	}
}
