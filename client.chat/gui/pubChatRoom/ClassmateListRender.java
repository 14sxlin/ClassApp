package gui.pubChatRoom;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class ClassmateListRender extends JCheckBox implements ListCellRenderer<String> 
																	{

	private String myUsername;
	private boolean ismyself;
	
	public ClassmateListRender(String username) {
		myUsername = username;
	}
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends String> list, 
			String value, 
			int index,
			boolean isSelected, 
			boolean cellHasFocus) {
		
		ismyself = value.equals(myUsername);
		this.setText(value);
		
		if(isSelected)
			this.setSelected(true);
		else
			this.setSelected(false);
		
		this.setBackground(index%2 ==0? Color.white:Color.lightGray);
		if(ismyself)
		{	
			this.setText(value+"  (Œ“)");
		}

		if(isSelected)
		{
			this.setBackground(Color.cyan);
		}
		
		return this;
	}

}
