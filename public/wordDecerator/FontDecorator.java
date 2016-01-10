package wordDecerator;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class FontDecorator extends AbstractDecorator {

	private String font;
	
	public FontDecorator(String font) {
		this.font = font;
	}



	@Override
	public SimpleAttributeSet buildDecorator(SimpleAttributeSet set) {
		SimpleAttributeSet temp =checkSet(set);
		StyleConstants.setFontFamily(set, font);
		return temp;
	}
	
}
