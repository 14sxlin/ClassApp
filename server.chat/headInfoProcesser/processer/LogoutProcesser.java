package headInfoProcesser.processer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import api.notifier.LogoutNotifier;
import api.notifier.Notifier;
import object.Client;

public class LogoutProcesser extends HeadInfoProcesser {
	
	private Notifier logoutNotifier;
	private ArrayList<Client> memberList;
	
	public LogoutProcesser(ArrayList<Client> memberList) {
		this.logoutNotifier=new LogoutNotifier();
		this.memberList=memberList;
	}
	
	@Override
	public ArrayList<Client> process(Client client) {
		return null;
	}

	@Override
	public ArrayList<Client> process(String userName) {
		try {
			
			this.logoutNotifier.notify(this.memberList);
			
			Client tempClient;
			if (( tempClient = findClient(userName)) != null)
				memberList.remove(tempClient);
			
			return this.memberList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private Client findClient(String userName)
	{
		Iterator<Client> it=this.memberList.iterator();
		while( it.hasNext())
		{
			Client tempClient=it.next();
			if(tempClient.getUserName().equals(userName))
				return tempClient;
		}
		return null;
	}

}
