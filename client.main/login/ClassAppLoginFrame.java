package login;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mainframe.ClassAppMainFrame;

/**
 * �û���¼�Ľ���
 * @author ��˼��
 *
 */
@SuppressWarnings("serial")
public class ClassAppLoginFrame extends JFrame{

	public static Dimension dim;
	private JTextField userName;
	private JPasswordField password;
	private JButton loginButton,forgetButton;
	
	public ClassAppLoginFrame() {
		super();
		drawGui();
	}
	
	private void drawGui()
	{
		
		dim=getToolkit().getScreenSize();
		this.setTitle("��¼");
		this.setLocation(dim.width/2-150,dim.height/2-50);
		this.setSize(300, 100);
		this.setLayout(new GridLayout(2,3));
		this.add(new JLabel("�û���:"));
		this.add(userName=new JTextField(30));
		this.add(loginButton=new JButton("��¼"));
		loginButton.addActionListener(new ButtonListener());
		loginButton.setFocusable(false);
		this.add(new JLabel("����:"));
		this.add(password=new JPasswordField(30));
		password.addKeyListener(new EnterListener());
		this.add(forgetButton=new JButton("��������"));
		forgetButton.setFocusable(false);
		// TODO Auto-generated catch block
//		forgetButton.addActionListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * ��֤�û��������벢������Ӧ�ķ�Ӧ
	 */
	private void loginVerify()
	{
		// TODO Auto-generated catch block ����Ӧ�ô����ݿ��ѯ
		if(String.copyValueOf(password.getPassword()).equals("123"))//ֻҪ������123 �Ϳ��Ե�¼
		{
			new ClassAppMainFrame(userName.getText());
			this.dispose();
		}else
		{
			JOptionPane.showMessageDialog(this, "�û������������");
			password.setText("");
		}			
	}
	
	/**
	 * ��¼��ť����
	 * @author LinSixin sparrowxin@sina.cn
	 *
	 */
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			loginVerify();			
		}
		
	}
	
	/**
	 * �س�������Ӧ
	 * @author LinSixin sparrowxin@sina.cn
	 *
	 */
	private class EnterListener implements KeyListener
	{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				loginVerify();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	}
	
	public static void main(String args[])
	{
		new ClassAppLoginFrame();
	}
}
