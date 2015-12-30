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
	
	public OfficeInfoListPanel() {
		
		this.setGui();
	}
	
	private void setGui()
	{
		this.setLayout(new BorderLayout());
		
		refreshList(AutoOfficeTools.getInfoList(1));
		list = new JList<>(this.model);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				if(e.getClickCount()== 2)
				{
					try {
						Desktop.getDesktop().browse(
								new URI(AutoOfficeTools.getDetailWebsite((OfficeInfo) list.getSelectedValue())));
					} catch (IOException | URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
			}
		});
		this.add(new JScrollPane(list),"Center");
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(currentLabel = new JLabel("当前在第 "+currentIndex+ " 页"));
		toolbar.add(lastButton = new JButton("上一页"));
		toolbar.add(nextButton = new JButton("下一页"));
		lastButton.addActionListener(new ButtonEvent(false));
		nextButton.addActionListener(new ButtonEvent(true));
		
		toolbar.add(new JLabel("跳转"));
		toolbar.add(jumpfield = new JTextField(10)); 
		jumpfield.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					try {
						int index = Integer.parseInt(jumpfield.getText());
						if( index>=1 && index<=AutoOfficeTools.TOTALINDEX)
							refreshList(AutoOfficeTools.getInfoList(index));
						else 	JOptionPane.showMessageDialog(null, "超出范围" );
						jumpfield.setText("");
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "输入数字" );
								
					}
				}
				
			}
		});
		
		this.add(toolbar,"South");
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
	 * 更新列表的值
	 * @param valueList 办公自动化信息的数组
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
		if(currentLabel != null)
			currentLabel.setText("当前在第 "+currentIndex+ " 页");
	}

}
