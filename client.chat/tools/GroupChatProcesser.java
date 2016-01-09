package tools;

import api.client.pubChatRoom.PubChatRoomLogic;
import classapp.mainframe.ClassAppMainFrame;
import main.client.groupchat.GroupChatMainFrame;

/**
 * ���������Ĵ��ڵ��¼�
 * 1.#head:group?content=sender!username&username2:mark#
 * 2.#head:gsend?content=mark:message#
 * ��һ���ǽ��յ�Ҫ�齨������Ϣ�����
 * �ڶ������Ѿ�����Ȼ������Ϣ�����
 * 
 * @author LinSixin sparrowxin@sina.cn
 *
 */
public class GroupChatProcesser {

	public  static long mark;
	public  static String usernameList;
	public  static String message;
	public  static String sender;
	
	public static   void process(String content,PubChatRoomLogic logic)
	{
		try {
			setData(filteContent(content));
			if(content.contains("!"))//��һ�����,Ҫ�½�һ��
			{
				GroupChatMainFrame temp = new GroupChatMainFrame(mark, sender+"&"+usernameList, logic);
				ClassAppMainFrame.groupChatManager.add(temp);
			}
			else
			{
				ClassAppMainFrame.groupChatManager.find(mark).updateTextPane(message);
			}
		} catch (NullPointerException e) {
		}
	}
	
	
	private  static void setData(String content)
	{
		if(content.contains("!"))//��һ�����
		{
			int  i1,i2 = -1;
			i1 = content.indexOf("!");
			i2 = content.indexOf(":");
			if(i1==-1 || i2==-1)
				throw new NullPointerException();
			sender = content.substring(0, i1);
			usernameList =  content.substring(i1+1, i2);
			mark = Long.parseLong(content.substring(i2+1));
			
					
		}else
		{
			int  i1= content.indexOf(":");
			if(i1==-1)
				throw new NullPointerException();
			mark =  Long.parseLong(content.substring(0,i1));
			message = content.substring(i1+1);
		}
	}
	
	private  static String filteContent(String content)
	{
		int i = content.indexOf("content=");
		return content.substring(i+"content=".length(), content.lastIndexOf("#"));
	}
	
	/**
	 * ����ڴ��е����� ��ֹ���
	 */
	public static  void clean()
	{
		mark = -1L;
		usernameList = null;
		message = null;
		sender = null;
	}
}
