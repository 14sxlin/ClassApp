package api.client;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import listProcesser.ListInfoProcesser;

/**
 * ��������������ݵ��ڲ���б�����Ϣ��
 * ���ͻ��˽��յ��б����ַ�����Ϣ��ʱ��
 * ͨ���������֪ͨgui���е��б����и���
 * @author think
 *
 */
public class ClientGuiNotifier {

	private JList<String> list;

	private DefaultListModel<String> model;
	
	public ClientGuiNotifier(JList<String> list) {
		model = new DefaultListModel<>();
		this.list = list ;
	}
	
	public void updateList(String listString)
	{
		model = ListInfoProcesser.createListModel(listString);
		list.setModel(model);
	}
}