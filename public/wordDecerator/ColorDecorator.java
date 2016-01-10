package wordDecerator;

import java.awt.Color;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ColorDecorator extends AbstractDecorator{


	private Color color ;
	
	public ColorDecorator(Color color) {
		this.color = color;
	}

	@Override
	public SimpleAttributeSet buildDecorator(SimpleAttributeSet set) {
		SimpleAttributeSet temp = new SimpleAttributeSet();
		StyleConstants.setForeground(set, color);
		return temp;
	}
	

	

}
