package tools;

import classapp.mainframe.ClassAppMainFrame;
import headinfoFilter.HeadInfoFilter;

/**
 * @author LinSixin sparrowxin@sina.cn
 * ������������������Ĵ��ڳ�Ա���б�Ĺ�������
 * ������Ա�ļ���,��Ա���˳�
 */
public class GroupChatListProcesser {

	private static long mark;
	private static String namelist;
	private static boolean isIn;
	
	synchronized public static void	 updateList(String headinfo)
	{
		// TODO System Output Test Block
		System.out.println(" grouplist process���յ�����Ϣ =  "+headinfo);
		setState(headinfo);
		try {
			ClassAppMainFrame.groupChatManager.find(mark).updateList(namelist, isIn);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���������ַ����е���Ϣ
	 * ��ʽ�����ε�
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
		System.out.println(" ���Ǹ���list��,��ȡ����������:");
		System.out.println(" isIn =  "+isIn);
		System.out.println("  mark = "+mark);
		System.out.println("  namelist = "+namelist);
	}
}
