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
 * 用户登录的界面
 * @author 林思鑫
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
		this.setTitle("登录");
		this.setLocation(dim.width/2-150,dim.height/2-50);
		this.setSize(300, 100);
		this.setLayout(new GridLayout(2,3));
		this.add(new JLabel("用户名:"));
		this.add(userName=new JTextField(30));
		this.add(loginButton=new JButton("登录"));
		loginButton.addActionListener(new ButtonListener());
		loginButton.setFocusable(false);
		this.add(new JLabel("密码:"));
		this.add(password=new JPasswordField(30));
		password.addKeyListener(new EnterListener());
		this.add(forgetButton=new JButton("忘记密码"));
		forgetButton.setFocusable(false);
		// TODO Auto-generated catch block
//		forgetButton.addActionListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * 验证用户名和密码并作出相应的反应
	 */
	private void loginVerify()
	{
		// TODO Auto-generated catch block 这里应该从数据库查询
		if(String.copyValueOf(password.getPassword()).equals("123"))//只要密码是123 就可以登录
		{
			new ClassAppMainFrame(userName.getText());
			this.dispose();
		}else
		{
			JOptionPane.showMessageDialog(this, "用户名或密码错误");
			password.setText("");
		}			
	}
	
	/**
	 * 登录按钮动作
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
	 * 回车动作响应
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
