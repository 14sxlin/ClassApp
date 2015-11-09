package threadData;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;

/**
 * �����������һֱ�������߳��л�ȡ����
 * �ණ��ȫ����������...
 * @author ��˼��
 *
 */

public class ThreadDataTransfer {
	
	/**
	 * ���������ⲿ��textfield����
	 */
	public JTextField counterTextField;

	/**
	 * ���������ⲿ��listmodel����
	 */
	public DefaultListModel<String> listmodel;
	
	
	public ThreadDataTransfer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * �ṩ���ⲿ�Ľӿ�,���������ⲿswing�����״̬<br>
	 * ������ôд��һ������,�Ǿ��ǲ����Ͽ���ԭ��,ÿ����Ҫ��չ��ʱ����Ҫ����������������µĳ�Ա����
	 * �������ӵ�һ���̶�֮��Ͳ���Ҫ������,�����÷���������,��������ʲô�õĽ������.
	 * @param textField
	 * @param listmodel
	 */
	synchronized public void setField(JTextField counterTextField,DefaultListModel<String> listmodel)
	{
		this.counterTextField=counterTextField;
		this.listmodel=listmodel;
	}
	
	/**
	 * �ṩ���ⲿ�Ľӿ�,���������ⲿswing�����״̬<br>
	 * ������ôд��һ������,�Ǿ��ǲ����Ͽ���ԭ��,ÿ����Ҫ��չ��ʱ����Ҫ����������������µĳ�Ա����
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
