package headInfoFliter.factory;

import java.util.ArrayList;

import headInfoFliter.fliter.HeadInfoFilter;
import headInfoFliter.fliter.LogoutFilter;

public class LogoutFilterFactory extends FilterFactory{

	public LogoutFilterFactory(ArrayList<String> memberList) {
		super(memberList);
	}

	@Override
	HeadInfoFilter createFilter(String filterType) {
		
		if(filterType == "logout")
			return new LogoutFilter(super.memberList);
		else return null;
		
	}
}
