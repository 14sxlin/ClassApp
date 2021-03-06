package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import object.ServerInfo;

/**
 * 服务器的界面,服务器使用
 * @author 林思鑫
 *
 */
public class GuiForServer extends JFrame {

	/**
	 * 版本1.0
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	/**
	 * 用来显示在线的人数
	 */
	public JTextField counterTextField;
	
	public  DefaultListModel<String> listmodel;
	public  JList <String>list_1;
	
	public  JTextArea textPane;
	/**
	 * 启动服务的按钮,暴露在外
	 */
	public JButton startServiceButton;
	public JTextField textField;
	public JButton sendbutton;
	public JComboBox<String> ipComboBox;
	private JTextField portText;
	private JLabel lblip;
	private JLabel label_1;
	private JLabel label_2;
	
	/**
	 * Launch the application.
	 * @param args 命令行参数
	 */
	public static void main(String[] args) {
		new GuiForServer();
	}

	/**
	 * Create the frame.
	 */
	public GuiForServer() {
		guiDesign();
	}
	
	/**
	 * gui绘制
	 */
	private void guiDesign()
	{
		this.setTitle("服务器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		ipComboBox = new JComboBox<String>();
		ipComboAddItem();
		
		lblip = new JLabel("\u670D\u52A1\u5668IP:");
		panel.add(lblip);
		panel.add(ipComboBox);
		
		label_1 = new JLabel("\u670D\u52A1\u7AEF\u53E3:");
		panel.add(label_1);
		
		portText = new JTextField();
		panel.add(portText);
		portText.setColumns(10);
		portText.setText(""+ServerInfo.PORT);
		portText.setEditable(false);
		
		startServiceButton = new JButton("\u5F00\u542F\u670D\u52A1");
		panel.add(startServiceButton);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.6);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		textPane = new JTextArea();
		scrollPane.setViewportView(textPane);
		textPane.setLineWrap(true);
		textPane.setEditable(false);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setBorder(new TitledBorder(null, "\u5728\u7EBF\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GREEN));
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("\u5728\u7EBF\u4EBA\u6570");
		toolBar.add(label);
		
		counterTextField = new JTextField();
		counterTextField.setEditable(false);
		toolBar.add(counterTextField);
		counterTextField.setColumns(1);
		
		label_2 = new JLabel("\u53D1\u9001\u5168\u5C40\u6D88\u606F:");
		toolBar.add(label_2);
		
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(10);
		
		sendbutton = new JButton("\u53D1\u9001");
		toolBar.add(sendbutton);
		
		listmodel=new DefaultListModel<>();
		list_1=new JList<>(listmodel);
		panel_1.add(list_1);
		listmodel.addListDataListener(new ListDataListener() {
			
			@Override
			public void intervalRemoved(ListDataEvent e) {
				
			}
			
			@Override
			public void intervalAdded(ListDataEvent e) {
				
			}
			
			@Override
			public void contentsChanged(ListDataEvent e) {
				list_1.setModel(listmodel);
			}
		});
		
		this.setVisible(true);		
	}
	
	private void ipComboAddItem()
	{
		if(ipComboBox != null)
		{
			ipComboBox.addItem("10.21.30.30");
			ipComboBox.setEditable(false);
		}
	}
	
}
