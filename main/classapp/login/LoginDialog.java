package classapp.login;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classapp.mainframe.ClassAppMainFrame;

/**
 * �û���¼�Ľ���
 * @author ��˼��
 *
 */
@SuppressWarnings("serial")
public class LoginDialog extends JDialog implements ActionListener {

	public static Dimension dim;
	private JTextField userName;
	private JPasswordField password;
	private JButton loginButton,forgetButton;
	public LoginDialog() {
		super();
		dim=getToolkit().getScreenSize();
		this.setTitle("��¼");
		this.setLocation(dim.width/2-150,dim.height/2-50);
		this.setSize(300, 100);
		this.setLayout(new GridLayout(2,3));
		this.add(new JLabel("�û���:"));
		this.add(userName=new JTextField(30));
		this.add(loginButton=new JButton("��¼"));
		loginButton.addActionListener(this);
		this.add(new JLabel("����:"));
		this.add(password=new JPasswordField(30));
		this.add(forgetButton=new JButton("��������"));
		forgetButton.addActionListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("��¼"))
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
		
	}
	
	public static void main(String args[])
	{
		new LoginDialog();
	}
}
