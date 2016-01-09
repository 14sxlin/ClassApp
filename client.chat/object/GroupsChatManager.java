package object;

import java.util.ArrayList;
import java.util.Iterator;

import main.client.groupchat.GroupChatMainDialog;


public class GroupsChatManager {

	public  ArrayList<GroupChatMainDialog> groups = new ArrayList<>();
	
	public  void add(GroupChatMainDialog group)
	{
		groups.add(group);
	}
	
	public  void remove(int id)
	{
		groups.remove(id);
	}
	
	public  GroupChatMainDialog find(long mark) 
	{
		Iterator<GroupChatMainDialog> it = groups.iterator();
		while(it.hasNext())
		{
			GroupChatMainDialog temp = it.next();
			if(temp.getMark()==mark)
			{
				// TODO System Output Test Block
				System.out.println(" i find it ! ");
				return temp;
			}
		}
		throw new NullPointerException();
	}
}
