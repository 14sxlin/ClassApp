package wordDecerator;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

public class TextStyleProcessor  {

	public static void decorate(Document doc,String message,SimpleAttributeSet set) {
		try {
			doc.insertString(doc.getLength(), message, (javax.swing.text.AttributeSet) set);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

}
