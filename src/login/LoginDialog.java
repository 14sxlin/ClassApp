package login;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginDialog extends JDialog implements ActionListener {
	/*
	 * 用户登录界面
	 */
	public static Dimension dim;
	private JTextField userName;
	private JPasswordField password;
	private JButton loginButton,forgetButton;
	public LoginDialog() {
		super();
		dim=getToolkit().getScreenSize();
		this.setTitle("登录");
		this.setLocation(dim.width/2-150,dim.height/2-50);
		this.setSize(300, 100);
		this.setLayout(new GridLayout(2,3));
		this.add(new JLabel("用户名:"));
		this.add(userName=new JTextField(30));
		this.add(loginButton=new JButton("登录"));
		loginButton.addActionListener(this);
		this.add(new JLabel("密码:"));
		this.add(password=new JPasswordField(30));
		this.add(forgetButton=new JButton("忘记密码"));
		forgetButton.addActionListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[])
	{
		new LoginDialog();
	}
}
