package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;

import bean.OfficeInfo;

@SuppressWarnings("serial")
public class JTextPaneListRenderer extends JTextPane implements ListCellRenderer<OfficeInfo> {

	@Override
	public Component getListCellRendererComponent(
			JList<? extends OfficeInfo> list, 
			OfficeInfo value, 
			int index,
			boolean isSelected, boolean cellHasFocus) {
		this.setText(value.date+'\n'+(index+1)+"    "+value.title+'\n');
		this.setEditable(false);
//		this.setForeground(!isSelected?Color.blue:Color.red);
//		this.setBackground(isSelected?Color.white:Color.white);//必须也指定这个下面才有用
		if(index %2==0)
		{	this.setBackground(Color.darkGray);
			this.setForeground(Color.cyan);
		}else
		{
			this.setBackground(Color.cyan);
			this.setForeground(Color.darkGray);
		}
		if(isSelected)
			this.setForeground(Color.white);
		
		return this;
	}

}
