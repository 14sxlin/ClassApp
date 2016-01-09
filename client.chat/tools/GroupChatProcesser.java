package tools;

import api.client.pubChatRoom.PubChatRoomLogic;
import classapp.mainframe.ClassAppMainFrame;
import main.client.groupchat.GroupChatMainFrame;

/**
 * 处理建立组聊窗口的事件
 * 1.#head:group?content=sender!username&username2:mark#
 * 2.#head:gsend?content=mark:message#
 * 第一种是接收到要组建组聊信息的情况
 * 第二种是已经建立然后发送信息的情况
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
			if(content.contains("!"))//第一种情况,要新建一个
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
		if(content.contains("!"))//第一种情况
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
	 * 清除内存中的数据 防止脏读
	 */
	public static  void clean()
	{
		mark = -1L;
		usernameList = null;
		message = null;
		sender = null;
	}
}
