package object;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class ChatDialog extends JFrame{
	public JTextArea textArea;
	public JTextField textField;
	public  JList<String> classmateList;
	public JButton sendButton;
	
	public ChatDialog() {
		textArea = new JTextArea();
		textField = new JTextField(30);
		classmateList = new JList<>();
		sendButton = new JButton("·¢ËÍ");
	}

}
