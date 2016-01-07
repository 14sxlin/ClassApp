package tools.listprocesser;

import javax.swing.DefaultListModel;

import headinfoFilter.HeadInfoFilter;

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
