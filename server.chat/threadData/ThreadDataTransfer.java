package threadData;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;

/**
 * 这个类用于在一直阻塞的线程中获取数据
 * 脏东西全部在这里面...
 * @author 林思鑫
 *
 */

public class ThreadDataTransfer {
	
	/**
	 * 用来引用外部的textfield对象
	 */
	public JTextField counterTextField;

	/**
	 * 用来引用外部的listmodel对象
	 */
	public DefaultListModel<String> listmodel;
	
	
	/**
	 * 提供给外部的接口,用来更新外部swing组件的状态<br>
	 * 但是这么写有一个问题,那就是不符合开闭原则,每次想要扩展的时候都需要在这个类里面增加新的成员变量
	 * 但是增加到一定程度之后就不需要增加了,可以用方法的重载,不过不是什么好的解决方案.
	 * @param counterTextField 用来显示数据的文本框
	 * @param listmodel 用来储存数据的列表模型
	 */
	synchronized public void setField(JTextField counterTextField,DefaultListModel<String> listmodel)
	{
		this.counterTextField=counterTextField;
		this.listmodel=listmodel;
	}
	
	/**
	 * 提供给外部的接口,用来更新外部swing组件的状态<br>
	 * 但是这么写有一个问题,那就是不符合开闭原则,每次想要扩展的时候都需要在这个类里面增加新的成员变量
	 * @param counter 计算在线人数
	 * @param list	在线的用户名列表
	 */
	synchronized public void updateState(int counter,ArrayList<String> list)
	{
		this.counterTextField.setText(""+counter);
		if( list != null)
		{
			this.listmodel.removeAllElements();
			Iterator<String> it = list.iterator();
			while( it.hasNext() )
			{
				this.listmodel.addElement( it.next() );
			}
		}
	}
}
