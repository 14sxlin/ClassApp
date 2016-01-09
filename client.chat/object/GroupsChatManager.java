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
	
	public  void remove(int id)
	{
		groups.remove(id);
	}
	
	public  GroupChatMainFrame find(long mark) 
	{
		Iterator<GroupChatMainFrame> it = groups.iterator();
		while(it.hasNext())
		{
			GroupChatMainFrame temp = it.next();
			if(temp.getMark()==mark)
			{
				return temp;
			}
		}
		throw new NullPointerException();
	}
}
