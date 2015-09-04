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
	private String [] strMenus= {"������ɫ","���������¼","�������"};
	private JMenuBar bar;
	private JTextPane chatArea;
	private JTextField chatFiled;
	private JButton sendButton,faceButton;
	private JList<String> groupmember;
	private DefaultListModel<String>listModel;
	public GroupChat(JFrame jframe) {
		this.setTitle("˽/����");
		LoginDialog.dim=getToolkit().getScreenSize();
		this.setBounds(LoginDialog.dim.width/2-250, LoginDialog.dim.height/2-200, 500, 400);

		
		//���Ӳ˵���
		bar=new JMenuBar();
		this.setJMenuBar(bar);
		jMenus=new JMenu[strMenus.length];
		for(int i=0;i<strMenus.length;i++)
		{
			jMenus[i]=new JMenu(strMenus[i]);
			bar.add(jMenus[i]);
			jMenus[i].addActionListener(this);
		}
		
		//�����������
		chatArea=new JTextPane();
		SimpleAttributeSet set=new SimpleAttributeSet();
		Document doc=chatArea.getDocument();
		int length=doc.getLength();
		
		//���Ӱ�ť
		JPanel panel=new JPanel();
		panel.add(chatFiled=new JTextField(30));
		panel.add(sendButton=new JButton("����"));
		panel.add(faceButton=new JButton("����"));
		this.add(panel, "South");
		
		//���ӳ�Ա���
		groupmember=new JList<String>(listModel=new DefaultListModel<>());
		
		//�ָ����
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
