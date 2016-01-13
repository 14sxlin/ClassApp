package object;

import java.util.ArrayList;
import java.util.Iterator;

import main.client.groupchat.GroupChatMainFrame;


public class GroupsChatManager {

	public  ArrayList<GroupChatMainFrame> groups = new ArrayList<>();
	
	public  void add(GroupChatMainFrame group)
	{
		groups.add(group);
	}
	
	public  void remove(long mark)
	{
		try {
			groups.remove(find(mark));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO System Output Test Block
		System.out.println(" �Ƴ��ɹ� ");
		System.out.println();
	}
	
	public  GroupChatMainFrame find(long mark) throws Exception
	{
		int originsize = groups.size();
		Iterator<GroupChatMainFrame> it = groups.iterator();
		while(it.hasNext())
		{
			GroupChatMainFrame temp = it.next();
			if(temp.getMark()==mark)
			{
				return temp;
			}
		}
			throw new Exception("�Ҳ���ʱ���־ = "+mark
					+"     ������ȱ仯  = "+(groups.size()-originsize));
	}
}
