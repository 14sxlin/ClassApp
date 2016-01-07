package api.client.pubChatRoom;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import tools.listprocesser.ListInfoProcesser;

/**
 * 这个类是用来传递的内层的列表的信息的
 * 当客户端接收到列表的字符串信息的时候
 * 通过这个类来通知gui类中的列表进行更新
 * @author 林思鑫
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
System.out.println("触发model处理");
System.out.println("old size = "+((DefaultListModel<String>)list.getModel()).getSize());
//	synchronized (model) {
		model = ListInfoProcesser.createListModel(listString);
// TODO System Output Test Block
System.out.println(" newmodel size =  "+model.getSize()+" last elements = "+model.lastElement());
		
			this.list.setModel(model);
//		}
	}
}
