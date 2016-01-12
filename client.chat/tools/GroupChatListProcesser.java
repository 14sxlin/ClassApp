package tools;

import classapp.mainframe.ClassAppMainFrame;
import headinfoFilter.HeadInfoFilter;

/**
 * @author LinSixin sparrowxin@sina.cn
 * 这个类是用来处理组聊窗口成员的列表的管理问题
 * 包括成员的加入,成员的退出
 */
public class GroupChatListProcesser {

	private static long mark;
	private static String namelist;
	private static boolean isIn;
	
	synchronized public static void	 updateList(String headinfo)
	{
		setState(headinfo);
		try {
			ClassAppMainFrame.groupChatManager.find(mark).updateList(namelist, isIn);
		} catch (NullPointerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	private static void setState(String headinfo)
	{
		int index = headinfo.indexOf("#group:");
		if(index == -1 )
			throw new NullPointerException();
		isIn =headinfo.substring(index+"#group:".length(),
												index+"#group:".length()+2)
				.equals("in")?true:false;
		HeadInfoFilter filter = new HeadInfoFilter(headinfo);
		String content = filter.filteContent().trim();
		int i = content.indexOf(":");
		mark = Long.parseLong(content.substring(0,  i));
		namelist = content.substring(i+1);
		
		// TODO System Output Test Block
		System.out.println(" isIn =  "+isIn);
		System.out.println("  mark = "+mark);
		System.out.println("  namelist = "+namelist);
	}
}
