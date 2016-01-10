package wordDecerator;

import javax.swing.text.SimpleAttributeSet;

public abstract class AbstractDecorator {

	/**
	 * ����װ����
	 */
	public abstract SimpleAttributeSet buildDecorator(SimpleAttributeSet set);
	
	/**
	 * ���set�Ƿ�Ϊ��,����յĻ�����һ���µ�SimpleAttribute
	 * @param set
	 * @return
	 */
	public  SimpleAttributeSet checkSet(SimpleAttributeSet set)
	{
		if(set != null)
			return set;
		else 
			return new SimpleAttributeSet();
	}
}
