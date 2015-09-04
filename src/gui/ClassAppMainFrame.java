package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ClassAppMainFrame extends JFrame implements ActionListener{
	/*
	 * �༶�����������
	 */
	public static boolean admim;
	private JPanel messageArea,buttonArea;
	private JList<String> classList,stuList;
	private DefaultListModel< String> classModel,stuModel;
	private String [] buttonstr= {"������","�ļ�����","���͹���"};
	private JButton [] buttons;
	private JToolBar toolbar;
	public ClassAppMainFrame() {
		this.setTitle("������һ��");
		LoginDialog.dim=getToolkit().getScreenSize();
		this.setBounds(LoginDialog.dim.width/2-250, LoginDialog.dim.height/2-200, 500, 400);
		//���ӹ�����
		messageArea=new JPanel(new GridLayout(1, 2));
		messageArea.setBorder(new TitledBorder("������"));
		classModel=new DefaultListModel<>();
		stuModel=new DefaultListModel<>();
		classList=new JList<>(classModel);
		stuList=new JList<>(stuModel);
		messageArea.add(classList);
		messageArea.add(stuList);
		//���Ӱ�ť��
		buttonArea=new JPanel();
		buttons=new JButton[buttonstr.length];
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i]=new JButton(buttonstr[i]);
			buttonArea.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		//���ӹ�����
		toolbar=new JToolBar();
		this.add(toolbar,"South");
		toolbar.add(new JButton("ǩ��"));
		
		//�������
		JSplitPane jsplit=new JSplitPane(JSplitPane.VERTICAL_SPLIT, messageArea, buttonArea);
		jsplit.setDividerLocation(200);
		this.add(jsplit);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setAdmin()//����admin��ֵ
	{
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClassAppMainFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}