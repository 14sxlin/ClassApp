package tools;

import api.client.pubChatRoom.PubChatRoomLogic;
import classapp.mainframe.ClassAppMainFrame;
import main.client.groupchat.GroupChatMainFrame;
import object.HeadType;

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
		// TODO System Output Test Block
		System.out.println(" GroupProcesser接收到服务器的消息是 =  "+content);
		try {
			if(setData(content))//第一种情况,要新建一个
			{
				GroupChatMainFrame temp = new GroupChatMainFrame(mark, sender+"&"+usernameList, logic);
//				GroupChatMainFrame temp = new GroupChatMainFrame(mark, usernameList, logic);
				ClassAppMainFrame.groupChatManager.add(temp);
			}
			else
			{
				ClassAppMainFrame.groupChatManager.find(mark).updateTextPane(message);
			}
			clean();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	
	private  static boolean setData(String content)
	{
		if(content.contains(HeadType.GROUP))//第一种情况
		{
			String[] temp = FormatInfo.filte3(filteContent(content));
			sender = temp[0];
			usernameList =  temp[1];
			mark = Long.parseLong(temp[2]);
			
			// TODO System Output Test Block
			System.out.println(" makeup group "
					+" 触发 -----\n sender =  "+temp[0]);
			System.out.println("  usernamelist  =  "+temp[1]);
			System.out.println("  mark  =  "+temp[2]);
				
			return true;
		}else if(content.contains(HeadType.GSEND))
		{
			String[] temp = FormatInfo.filte3(filteContent(content));
			usernameList  = temp[0];
			mark =  Long.parseLong(temp[1]);
			message = temp[2];
			
			// TODO System Output Test Block
			System.out.println(" send "
					+ " -----\n usernamelist =  "+temp[0]);
			System.out.println("  mark  =  "+temp[1]);
			System.out.println("  message  =  "+temp[2]);			
			
			return false;
		}else throw new NullPointerException();
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
