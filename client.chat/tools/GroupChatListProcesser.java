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
		// TODO System Output Test Block
		System.out.println(" grouplist process接收到的信息 =  "+headinfo);
		setState(headinfo);
		try {
			ClassAppMainFrame.groupChatManager.find(mark).updateList(namelist, isIn);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 截取发过来的字符串中的信息
	 * 格式是三段的
	 * @param headinfo
	 */
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
		String temp[] = FormatInfo.filte3(content);
		mark = Long.parseLong(temp[1]);
		namelist = temp[2];
		
		// TODO System Output Test Block
		System.out.println(" 我是更新list的,截取的数据如下:");
		System.out.println(" isIn =  "+isIn);
		System.out.println("  mark = "+mark);
		System.out.println("  namelist = "+namelist);
	}
}
