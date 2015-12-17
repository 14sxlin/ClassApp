package api.client;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import head_Info_Sender_Receiver.ListInfoProcesser;

/**
 * 这个类是用来传递的内层的列表的信息的
 * 当客户端接收到列表的信息的时候
 * 通过这个类来通知gui类中的列表进行更新
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
		model = ListInfoProcesser.processe(listString);
		list.setModel(model);
	}
	
	public JList<String> getList() {
		return list;
	}
}
