package gui.groupChatRoom;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import login.LoginDialog;

/**
 * 组聊的窗口,给客户端使用的
 * @author 林思鑫
 *
 */
@SuppressWarnings("serial")
public class GroupChat extends JDialog implements ActionListener {
	
	private JButton[] buttons;
	private String [] strButton= {"设置颜色","导出聊天记录","邀请加入"};
	private JToolBar bar;
	private JTextPane chatArea;
	private JTextField chatFiled;
	private JButton sendButton,faceButton;
	private JList<String> groupmember;
	private DefaultListModel<String>listModel;
	private Color myColor,otherColor;
	
	/**
	 * 默认的构造函数
	 * @param jframe 需要指定父容器,也可以为null
	 */
	public GroupChat(JFrame jframe) {
		setGui();
	}
	
	private void setGui()
	{
		this.setTitle("私/组聊");
		LoginDialog.dim=getToolkit().getScreenSize();
		this.setBounds(LoginDialog.dim.width/2-250, LoginDialog.dim.height/2-200, 500, 400);
		myColor=Color.blue;
		otherColor=Color.red;
		
		//增加菜单栏
		bar=new JToolBar();
		this.add(bar,"North");
		buttons=new JButton[strButton.length];
		for(int i=0;i<strButton.length;i++)
		{
			buttons[i]=new JButton(strButton[i]);
			bar.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
		//增加聊天面板
		chatArea=new JTextPane();
//		SimpleAttributeSet set=new SimpleAttributeSet();
//		Document doc=chatArea.getDocument();
//		int length=doc.getLength();
		
		//增加按钮
		JPanel panel=new JPanel();
		panel.add(chatFiled=new JTextField(30));
		panel.add(sendButton=new JButton("发送"));
		panel.add(faceButton=new JButton("表情"));
		sendButton.addActionListener(this);
		faceButton.addActionListener(this);
		this.add(panel, "South");
		
		//增加成员面板
		groupmember=new JList<String>(listModel=new DefaultListModel<String>());
		
		//分割面板
		JSplitPane jSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chatArea, groupmember);
		jSplitPane.setOneTouchExpandable(true);
		this.setVisible(true);
		jSplitPane.setDividerLocation(this.getWidth()*2/3);
		this.add(jSplitPane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
//	//发送聊天语句
//	public void send(String massage)
//	{
//		PrintWriter wr=new PrintWriter(socketStream.out,true);
//		wr.println(massage);
//		this.setText(massage, myColor);
//		chatFiled.setText("");
//	}
	
//	//接收聊天信息
//	public void receive()
//	{
//		String line="";
//		while(socketStream.socket.isConnected())
//		{
//			BufferedReader br=new BufferedReader(new InputStreamReader(socketStream.in));
//			try {
//				line=br.readLine();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}		
//			this.setText(line, otherColor);
//		}
//	}
	
	//给JTextPanel设置文本,可以设置单行的颜色
	public void setText(String massage,Color color)
	{
		try {
			SimpleAttributeSet set=new SimpleAttributeSet();
			Document doc=chatArea.getDocument();
			int length=doc.getLength();
			StyleConstants.setForeground(set, color);
			doc.insertString(length, massage, set);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
