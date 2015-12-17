package headInfoFliter.fliter;

import java.io.IOException;
import java.util.ArrayList;

import api.notifier.LoginNotifier;
import api.notifier.Notifier;
import object_client_server.Client;

public class LoginProcesser extends HeadInfoProcesser{

	private ArrayList<Client> memberList;
	private Notifier loginNotifier;
	
	public LoginProcesser(ArrayList<Client> memberList) {
		loginNotifier=new LoginNotifier();
		this.memberList=memberList;
	}
	@Override
	public ArrayList<Client> process(Client client) {
		try {
			this.loginNotifier.notify(memberList);
			this.memberList.add(client);
			return this.memberList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ArrayList<Client> process(String userName) {//这个方法不用实现
		return null;
	}

}
