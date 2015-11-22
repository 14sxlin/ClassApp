package headInfoFliter.factory;

import java.util.ArrayList;

import headInfoFliter.fliter.HeadInfoFilter;
import headInfoFliter.fliter.LogoutFilter;

public class LoginFilterFactory extends FilterFactory {

	public LoginFilterFactory(ArrayList<String> memberList) {
		super(memberList);
	}

	@Override
	HeadInfoFilter createFilter(String filterType) {
		
		if(filterType == "login")
			return new LogoutFilter(super.memberList);
		else return null;
		
	}

}
