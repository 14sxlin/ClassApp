package tools;

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
	 * 
	 * @param username ��ϣ�����б�����ʾ�Լ�����Ŀ,�������ָ���Լ�������
	 * @param listString һ����ʽ���û����б��ַ���
	 * @return һ���µ��б�model
	 */
	synchronized public static DefaultListModel<String> createListModel(String username,String listString)
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
			listModel.removeElement(username);
			return listModel;
		}
		else return null;
		
	} 
}
