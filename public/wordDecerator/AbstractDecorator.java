package wordDecerator;

import javax.swing.text.SimpleAttributeSet;

public abstract class AbstractDecorator {

	/**
	 * 构造装饰器
	 */
	public abstract SimpleAttributeSet buildDecorator(SimpleAttributeSet set);
	
	/**
	 * 检查set是否为空,如果空的话返回一个新的SimpleAttribute
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
