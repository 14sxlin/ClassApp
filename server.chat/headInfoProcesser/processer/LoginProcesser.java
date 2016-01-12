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
		ClientsManager.sendAllClient(loginClient.getUserName()+ "  ��¼",true);
	}
	
	@Override
	public void process(String userName) throws Exception {//�����������ʵ��
		throw new Exception("��Ҫ���������");
	}

}
