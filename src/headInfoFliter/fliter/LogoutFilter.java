package headInfoFliter.fliter;

import java.io.IOException;
import java.util.ArrayList;

import api.notifier.LogoutNotifier;
import api.notifier.Notifier;

public class LogoutFilter extends HeadInfoFilter {
	
	private Notifier logoutNotifier;
	private ArrayList<String > memberList;
	
	public LogoutFilter(ArrayList<String> memberList) {
		this.logoutNotifier=new LogoutNotifier();
	}
	
	@Override
	void process() {
		try {
			this.logoutNotifier.notify(this.memberList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
