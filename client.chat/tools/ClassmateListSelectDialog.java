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
			this.setTitle("����");
			this.setSize(new Dimension(200, 300));
			this.setLocationRelativeTo(null);
			getContentPane().setLayout(new BorderLayout());
			
			this.add(new JLabel("��ѡ�������:"),"North");
			
			//���ɸ�ѡ��
			String [] names =usernameList.split("&");
			JCheckBox[] boxs = new JCheckBox[names.length];
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(names.length, 1));
			panel.setBorder(new TitledBorder("���ߵ���"));
			for(int i=0 ; i<names.length; i++)
			{
				boxs[i] = new JCheckBox(names[i]);
				panel.add(boxs[i]);
			}
			this.add(panel,"Center");
			
			//��Ӱ�ť
			JButton sureB,cancelB;
			sureB = new JButton("ȷ��");
			cancelB = new JButton("ȡ��");
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
