package api.client.pubChatRoom;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import tools.listprocesser.ListInfoProcesser;

/**
 * ��������������ݵ��ڲ���б����Ϣ��
 * ���ͻ��˽��յ��б���ַ�����Ϣ��ʱ��
 * ͨ���������֪ͨgui���е��б���и���
 * @author ��˼��
 *
 */
@Deprecated
public class ClientGuiNotifier {

	private JList<String> list;

	private DefaultListModel<String> model;
	
	public ClientGuiNotifier(JList<String> list) {
		model = new DefaultListModel<>();
		this.list = list ;
	}
	
	public void updateList(String listString)
	{
System.out.println("����model����");
System.out.println("old size = "+((DefaultListModel<String>)list.getModel()).getSize());
//	synchronized (model) {
		model = ListInfoProcesser.createListModel(listString);
// TODO System Output Test Block
System.out.println(" newmodel size =  "+model.getSize()+" last elements = "+model.lastElement());
		
			this.list.setModel(model);
//		}
	}
}
