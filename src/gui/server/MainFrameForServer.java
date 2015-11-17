package gui.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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

/**
 * �������Ľ���,������ʹ��
 * @author ��˼��
 *
 */
public class MainFrameForServer extends JFrame {

	/**
	 * �汾1.0
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	/**
	 * ������ʾ���ߵ�����
	 */
	public JTextField counterTextField;
	
	public  DefaultListModel<String> listmodel;
	public  JList <String>list_1;
	
	public  JTextArea textPane;
	/**
	 * ��������İ�ť,��¶����
	 */
	public JButton startServiceButton;
	public JTextField textField;
	public JButton sendbutton;
	
	/**
	 * Launch the application.
	 * @param args �����в���
	 */
	public static void main(String[] args) {
		new MainFrameForServer();
	}

	/**
	 * Create the frame.
	 */
	public MainFrameForServer() {
		guiDesign();
	}
	
	/**
	 * gui����
	 */
	private void guiDesign()
	{
		this.setTitle("������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		startServiceButton = new JButton("\u5F00\u542F\u670D\u52A1");

		panel.add(startServiceButton);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		sendbutton = new JButton("\u53D1\u9001");
		panel.add(sendbutton);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.8);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		//gui���

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
		
		listmodel=new DefaultListModel<>();
		list_1=new JList<>(listmodel);
		panel_1.add(list_1);
		listmodel.addListDataListener(new ListDataListener() {
			
			@Override
			public void intervalRemoved(ListDataEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void intervalAdded(ListDataEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void contentsChanged(ListDataEvent e) {
				list_1.setModel(listmodel);
			}
		});
		
		this.setVisible(true);		
	}
	
	/**
	 * ��������������б�ģ�͵�ֵ��
	 * @param list Ҫ���õ��б�ģ��
	 * @return ����һ���б�ģ��
	 * @deprecated ������һ���н���������,���������
	 */
	public DefaultListModel<String> setListModel(ArrayList<String> list)
	{
		if(list!=null)
		{
			Iterator<String> it=list.iterator();
			while(it.hasNext())
			{
				listmodel.addElement(it.next());
			}
			return listmodel;
		}
		else return null;
	}

	

}
