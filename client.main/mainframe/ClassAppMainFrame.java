package mainframe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import gui.OfficeInfoListPanel;
import login.ClassAppLoginFrame;
import manager.GroupsChatManager;
import manager.LogoutEvent;
import pubchatmain.PubChatRoomMainFrame;


/**
 * ������,�ͻ�ʹ��
 * @author ��˼��
 *
 */
@SuppressWarnings("serial")
public class ClassAppMainFrame extends JFrame implements ActionListener{
	
	public static String username;
	public static GroupsChatManager groupChatManager;
	private JPanel messageArea,buttonArea;
	private String [] buttonstr= {"������","�ļ�����","���͹���"};
	private JButton [] buttons;
	private JToolBar toolbar;
	private PubChatRoomMainFrame pubChatRoom;
	private OfficeInfoListPanel officePanel;
	
	/**
	 * Ĭ�ϵĹ��캯��
	 */
	public ClassAppMainFrame(String username) {
		ClassAppMainFrame.username = username;
		groupChatManager = new GroupsChatManager();
		guiDesign();
		this.addWindowListener(new LogoutEvent(pubChatRoom.getClient()));
	}
	
	/**
	 * ���沼��
	 */
	private void guiDesign()
	{
		this.setTitle("������һ��--"+username);
		ClassAppLoginFrame.dim=getToolkit().getScreenSize();
		this.setBounds(ClassAppLoginFrame.dim.width/2-250, ClassAppLoginFrame.dim.height/2-250, 500, 500);
		
		//���ӹ�����
		messageArea=new JPanel(new GridLayout(1, 2));
		messageArea.setBorder(new TitledBorder("������"));
		officePanel = new OfficeInfoListPanel();
		messageArea.add(officePanel);
		
		//���Ӱ�ť��
		buttonArea=new JPanel();
		buttons=new JButton[buttonstr.length];
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i]=new JButton(buttonstr[i]);
			buttonArea.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
		//���ӹ�����
		toolbar=new JToolBar();
		this.add(toolbar,"South");
		toolbar.add(new JButton("ǩ��"));
		
		//�������
		JSplitPane jsplit=new JSplitPane(JSplitPane.VERTICAL_SPLIT, messageArea, buttonArea);
		jsplit.setDividerLocation(this.getHeight()*7/10);
		this.add(jsplit);
		
		//���������ҽ���,����һ��ʼ���ɼ�
		pubChatRoom = new PubChatRoomMainFrame(username);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

//	public static void main(String[] args) {
//		new ClassAppMainFrame("2223");
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getActionCommand().equals("������"))
		{
			pubChatRoom.gui.setVisible(true);
		}
	}
}