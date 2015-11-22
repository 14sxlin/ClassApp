package headInfoFliter.fliter;

import java.io.IOException;
import java.util.ArrayList;

import api.notifier.LoginNotifier;
import api.notifier.Notifier;

public class LoginFilter extends HeadInfoFilter{

	private ArrayList<String> memberList;
	private Notifier loginNotifier;
	
	public LoginFilter(ArrayList<String> memberList) {
		loginNotifier=new LoginNotifier();
		this.memberList=memberList;
	}
	@Override
	void process() {
		try {
			this.loginNotifier.notify(memberList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
