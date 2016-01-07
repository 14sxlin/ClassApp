package tools.listprocesser;

import javax.swing.DefaultListModel;

import headinfoFilter.HeadInfoFilter;

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
	synchronized public static DefaultListModel<String> createListModel( String listString)
	{
		HeadInfoFilter filter = new HeadInfoFilter(listString);
		if(filter.filteType().equals("list"))
		{
			String content = filter.filteContent();
			DefaultListModel<String> listModel = new DefaultListModel<>();
			String []names = content.split("&");
			if(listModel != null)
				listModel.removeAllElements();
			for(int i=0 ; i<names.length; i++)
			{
				listModel.addElement(names[i]);
			}
			
			return listModel;
		}
		else return null;
		
	}
}
