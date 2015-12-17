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
import main_frame.Client.PubChatRoomMainFrame;


/**
 * ������,�ͻ�ʹ��
 * @author ��˼��
 *
 */
@SuppressWarnings("serial")
public class ClassAppMainFrame extends JFrame implements ActionListener{
	
	public static boolean admim;
	private JPanel messageArea,buttonArea;
	private JList<String> classList,stuList;
	private DefaultListModel< String> classModel,stuModel;
	private String [] buttonstr= {"������","�ļ�����","���͹���"};
	private JButton [] buttons;
	private JToolBar toolbar;
	private PubChatRoomMainFrame pubChatRoom;
	
	/**
	 * Ĭ�ϵĹ��캯��
	 */
	public ClassAppMainFrame(String username) {
		guiDesign(username);
	}
	
	/**
	 * ���沼��
	 */
	private void guiDesign(String username)
	{
		this.setTitle("������һ��--"+username);
		LoginDialog.dim=getToolkit().getScreenSize();
		this.setBounds(LoginDialog.dim.width/2-250, LoginDialog.dim.height/2-200, 500, 400);
		
		//��ӹ�����
		messageArea=new JPanel(new GridLayout(1, 2));
		messageArea.setBorder(new TitledBorder("������"));
		classModel=new DefaultListModel<>();
		stuModel=new DefaultListModel<>();
		classList=new JList<>(classModel);
		stuList=new JList<>(stuModel);
		messageArea.add(classList);
		messageArea.add(stuList);
		
		//��Ӱ�ť��
		buttonArea=new JPanel();
		buttons=new JButton[buttonstr.length];
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i]=new JButton(buttonstr[i]);
			buttonArea.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
		//��ӹ�����
		toolbar=new JToolBar();
		this.add(toolbar,"South");
		toolbar.add(new JButton("ǩ��"));
		
		//������
		JSplitPane jsplit=new JSplitPane(JSplitPane.VERTICAL_SPLIT, messageArea, buttonArea);
		jsplit.setDividerLocation(200);
		this.add(jsplit);
		
		//���������ҽ���,����һ��ʼ���ɼ�
		pubChatRoom = new PubChatRoomMainFrame(username);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

//	public static void main(String[] args) {
//		new ClassAppMainFrame();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getActionCommand().equals("������"))
		{
			pubChatRoom.gui.setVisible(true);
		}
	}
}
