package tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ClassmateListSelectDialog {

	public static String lock ="lock";
	public String selectedUsernamelist ="";
	public ClassmateListSelectDialog(String usernameList) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				new SelectDialog(usernameList);
				
			}
		}).start();
	}
	
	@SuppressWarnings("serial")
	private class SelectDialog extends JDialog
	{
		public SelectDialog(String usernameList) {
			this.genalizeGui(usernameList);
		}
		private void genalizeGui(String usernameList)
		{
			this.setTitle("组聊");
			this.setSize(new Dimension(200, 300));
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(new BorderLayout());
			
			this.add(new JLabel("请选择加入者:"),"North");
			
			//生成复选框
			String [] names =usernameList.split("&");
			JCheckBox[] boxs = new JCheckBox[names.length];
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(names.length, 1));
			panel.setBorder(new TitledBorder("在线的人"));
			for(int i=0 ; i<names.length; i++)
			{
				boxs[i] = new JCheckBox(names[i]);
				panel.add(boxs[i]);
			}
			this.add(panel,"Center");
			
			//添加按钮
			JButton sureB,cancelB;
			sureB = new JButton("确定");
			cancelB = new JButton("取消");
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(sureB);
			buttonPanel.add(cancelB);
			this.add(buttonPanel, "South");
			
			
			sureB.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
							synchronized (lock) {
								for (int i = 0; i < names.length; i++) {
									if (boxs[i].isSelected()) {
										selectedUsernamelist += boxs[i].getText() + "&";
									}
								}
								// TODO System Output Test Block
								System.out.println("value has set = "
										+ selectedUsernamelist.substring(0, selectedUsernamelist.length() - 1));
								selectedUsernamelist = selectedUsernamelist.substring(0,
										selectedUsernamelist.length() - 1);
								lock.notify();
								
							}
							dispose();
						}
				});
			
			cancelB.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					synchronized (lock) {
						lock.notify();
						dispose();
					}
				}
			});
			setVisible(true);
		}
	}
	
}
