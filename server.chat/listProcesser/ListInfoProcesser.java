package listProcesser;

import javax.swing.DefaultListModel;

/**
 * 这个类是服务器向客户端发送在线用户的列表信息
 * 然后这个类在客户端做出相应的处理,
 * 包括列表成员的增加和删除
 * 列表的生成
 * @author 林思鑫
 *
 */
public class ListInfoProcesser  {

	/**
	 * 客户端接收服务器发送过来的信息,并生成相应的列表
	 */
	public static DefaultListModel<String> createListModel(String listString)
	{
		//listString是已经经过验证的形式了
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
