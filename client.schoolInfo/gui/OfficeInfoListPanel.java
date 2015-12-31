package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import bean.OfficeInfo;
import tool.AutoOfficeTools;

public class OfficeInfoListPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel currentLabel;
	private JList<OfficeInfo> list ;
	private DefaultListModel<OfficeInfo> model;
	private JButton nextButton,lastButton;
	private int currentIndex = 1;
	private JTextField jumpfield;
	private JScrollPane scrollPane;

	private JButton jumpButton;
	
	public OfficeInfoListPanel() {
		
		this.setGui();
	}
	
	private void setGui()
	{
		this.setLayout(new BorderLayout());
		
		refreshList(AutoOfficeTools.getInfoList(1));
		list = new JList<>(this.model);
		list.setCellRenderer(new JTextPaneListRenderer());
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				if(e.getClickCount()== 2)
				{
					try {
						Desktop.getDesktop().browse(
								new URI(AutoOfficeTools.getDetailWebsite((OfficeInfo) list.getSelectedValue())));
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					} 
				}
			}
		});
		this.add(scrollPane=new JScrollPane(list),"Center");
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(currentLabel = new JLabel("�� "+currentIndex+ " ҳ"));
		toolbar.add(lastButton = new JButton("��һҳ"));
		toolbar.add(nextButton = new JButton("��һҳ"));
		lastButton.addActionListener(new ButtonEvent(false));
		nextButton.addActionListener(new ButtonEvent(true));
		
		toolbar.add(new JLabel("��ת"));
		toolbar.add(jumpfield = new JTextField(10)); 
		jumpfield.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					jump();
				}
			}
		});
		toolbar.add(jumpButton = new JButton("��ת"));
		jumpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jump();
			}
			
		});
		this.add(toolbar,"South");
	}
	
	private void jump() {
		try {
			int index = Integer.parseInt(jumpfield.getText());
			if( index>=1 && index<=AutoOfficeTools.TOTALINDEX)
				refreshList(AutoOfficeTools.getInfoList(index));
			else 	JOptionPane.showMessageDialog(null, "������Χ" );
			jumpfield.setText("");
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "��������" );
					
		}
	}
	private class ButtonEvent implements ActionListener{
		private boolean isNextButton = false;
		
		public ButtonEvent(boolean isNextButton) {
			this.isNextButton = isNextButton;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if( !isNextButton)
			{	
				if(currentIndex>1)
					refreshList(AutoOfficeTools.getInfoList(--currentIndex));
				
			}else
			{
				if(currentIndex<AutoOfficeTools.TOTALINDEX)
					refreshList(AutoOfficeTools.getInfoList(++currentIndex));
			}
		}
	}
	
	/**
	 * �����б���ֵ
	 * @param valueList �칫�Զ�����Ϣ������
	 */
	private void refreshList(ArrayList<OfficeInfo> valueList)
	{
		if(model!= null)
			model.removeAllElements();
		else {
			model = new DefaultListModel<>();
			}
		for( OfficeInfo info: valueList)
			model.addElement(info);
		if(scrollPane != null)
		{	JScrollBar bar = scrollPane.getVerticalScrollBar();
			bar.setValue(0);
		}
		
		if(currentLabel != null)
			currentLabel.setText("�� "+currentIndex+ " ҳ");
	}

}