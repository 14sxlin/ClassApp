package manager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public abstract class ChatDialog extends JFrame{
	public JTextPane textPane;
	public JTextField textField;
	public  JList<String> classmateList;
	public JButton sendButton;
	
	public ChatDialog() {
		textPane = new JTextPane();
		textField = new JTextField(30);
		classmateList = new JList<>();
		sendButton = new JButton("·¢ËÍ");
	}

}
