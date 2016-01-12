package tools;

import classapp.mainframe.ClassAppMainFrame;
import headinfoFilter.HeadInfoFilter;

/**
 * @author LinSixin sparrowxin@sina.cn
 * ������������������Ĵ��ڳ�Ա���б�Ĺ�������
 * ������Ա�ļ���,��Ա���˳�
 */
public class GroupChatListProcesser {

	private static boolean isIn;
	
	synchronized public static void	 updateList(String headinfo)
	{
		// TODO System Output Test Block
		System.out.println(" grouplist process���յ�����Ϣ =  "+headinfo);
		process(headinfo);
	}
	
	/**
	 * ��ȡ���������ַ����е���Ϣ
	 * ��ʽ: #group:in?content=!mark:namelist#<br>
	 * 			#group:out?content=mark:outer#
	 * @param headinfo
	 */
	private static void process(String headinfo)
	{
		int index = headinfo.indexOf("#group:");
		if(index == -1 )
			throw new NullPointerException();
		isIn =headinfo.substring(index+"#group:".length(),
												index+"#group:".length()+2)
				.equals("in")?true:false;
		if (isIn) {
			try {
				HeadInfoFilter filter = new HeadInfoFilter(headinfo);
				String content = filter.filteContent().trim();
				String temp[] = FormatInfo.filte3(content);
				try {
					ClassAppMainFrame.groupChatManager.find(Long.parseLong(temp[1])).updateList(temp[2], isIn);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO System Output Test Block
				System.out.println();
				System.out.println(" ���Ǹ���list��,��ȡ����������:");
				System.out.println(" isIn =  "+isIn);
				System.out.println("  mark = "+temp[1]);
				System.out.println("  namelist = "+temp[2]);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}else
		{
			HeadInfoFilter filter = new HeadInfoFilter(headinfo);
			String content = filter.filteContent().trim();
			String temp[] = FormatInfo.filte2(content);
			try {
				ClassAppMainFrame.groupChatManager.find(Long.parseLong(temp[0])).updateList(temp[1], isIn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO System Output Test Block
			System.out.println();
			System.out.println(" ���Ǹ���list��,��ȡ����������:");
			System.out.println(" isIn =  "+isIn);
			System.out.println("  mark = "+temp[0]);
			System.out.println("  namelist = "+temp[1]);
		}
		
	}
}
