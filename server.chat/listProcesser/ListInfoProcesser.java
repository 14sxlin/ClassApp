package listProcesser;

import javax.swing.DefaultListModel;

/**
 * ������Ƿ�������ͻ��˷��������û����б���Ϣ
 * Ȼ��������ڿͻ���������Ӧ�Ĵ���,
 * �����б��Ա�����Ӻ�ɾ��
 * �б������
 * @author ��˼��
 *
 */
public class ListInfoProcesser  {

	/**
	 * �ͻ��˽��շ��������͹�������Ϣ,��������Ӧ���б�
	 */
	public static DefaultListModel<String> createListModel(String listString)
	{
		//listString���Ѿ�������֤����ʽ��
		//#head:list#username1&username2
		int begin = listString.lastIndexOf("#");
		String name = listString.substring(begin+1);
		DefaultListModel<String> listModel = new DefaultListModel<>();
		String []names = name.split("&");
		
		for(int i=0 ; i<names.length; i++)
		{
			listModel.addElement(names[i]);
		}
		
		return listModel;
	}
}
