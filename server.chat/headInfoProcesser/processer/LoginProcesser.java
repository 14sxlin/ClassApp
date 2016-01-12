package headInfoProcesser.processer;

import java.util.ArrayList;

import main_frame.server.ServerMainFraim;
import object.Client;
import tools.ClientsManager;

public class LoginProcesser extends HeadInfoProcesser{

	private ArrayList<Client> memberList;

	
	public LoginProcesser(ArrayList<Client> memberList) {
		super.type = CLIENT;
		this.memberList=memberList;
	}
	@Override
	public void process(Client loginClient) {
		synchronized (memberList) {
			this.memberList.add(loginClient);
		}
		ClientsManager.updateNameList();
		ServerMainFraim.tdt.updateState(ClientsManager.counter, ClientsManager.userNameList);
		ClientsManager.sendAllListMessage();
		ClientsManager.sendAllClient(loginClient.getUserName()+ "  登录",true);
	}
	
	@Override
	public void process(String userName) throws Exception {//这个方法不用实现
		throw new Exception("不要用这个方法");
	}

}
