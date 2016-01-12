package gui.groupChatRoom;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import classapp.login.ClassAppLoginFrame;
import classapp.mainframe.ClassAppMainFrame;
import object.ChatDialog;

/**
 * ���ĵĴ���,���ͻ���ʹ�õ�
 * @author ��˼��
 *
 */
@SuppressWarnings("serial")
public class GroupChatGUI extends ChatDialog  {
	
	// TODO Auto-generated catch block
	public  JButton[] buttons;
	private String [] strButton= {"�������"};
	
	private JToolBar bar;
	private JLabel melabel;
	
	/**
	 * Ĭ�ϵĹ��캯��
	 */
	public GroupChatGUI() {
		super();
		setGui();
	}
	
	private void setGui()
	{
		this.setTitle("˽/����-----"+ ClassAppMainFrame.username);
		ClassAppLoginFrame.dim=getToolkit().getScreenSize();
		this.setBounds(ClassAppLoginFrame.dim.width/2-250, ClassAppLoginFrame.dim.height/2-200, 500, 400);
		
		//���Ӳ˵���
		bar=new JToolBar();
		this.add(bar,"North");
		buttons=new JButton[strButton.length];
		for(int i=0;i<strButton.length;i++)
		{
			buttons[i]=new JButton(strButton[i]);
			bar.add(buttons[i]);
		};
		
		
		//�����������
		super.textPane.setEditable(false);
		
		//���Ӱ�ť
		JPanel panel=new JPanel();
		panel.add(super.textField);
		panel.add(sendButton=new JButton("����"));
//		panel.add(faceButton=new JButton("����"));
//		faceButton.addActionListener(this);
		this.add(panel, "South");
		
		JPanel memberpanel = new JPanel();
		memberpanel.setLayout(new BorderLayout());
		
		
		//�����Լ��û����ı�ǩ
		melabel = new JLabel(ClassAppMainFrame.username+"(��)");
		melabel.setHorizontalAlignment(JLabel.CENTER);
		memberpanel.add(melabel,"North");
		
		//���ӳ�Ա���
		classmateList=new JList<String>(new DefaultListModel<String>());
		memberpanel.add(new JScrollPane(classmateList),"Center");
		memberpanel.setBorder(new TitledBorder("���۳�Ա"));
		
		
		//�ָ����
		JSplitPane jSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				new JScrollPane(super.textPane), memberpanel);
		jSplitPane.setOneTouchExpandable(true);
		this.setVisible(true);
		jSplitPane.setDividerLocation(this.getWidth()*2/3);
		this.add(jSplitPane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	//��JTextPanel�����ı�,�������õ��е���ɫ
	public void setText(String massage,Color color)
	{
		try {
			SimpleAttributeSet set=new SimpleAttributeSet();
			Document doc=super.textPane.getDocument();
			int length=doc.getLength();
			StyleConstants.setForeground(set, color);//�ӹ�����
			doc.insertString(length, massage, set);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GroupChatGUI();
	}

}
