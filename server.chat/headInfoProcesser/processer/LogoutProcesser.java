package headInfoProcesser.processer;

import java.util.ArrayList;
import java.util.Iterator;

import main_frame.server.ServerMainFraim;
import object.Client;
import tools.clientmanager.ClientsManager;

/**
 * @author LinSixin sparrowxin@sina.cn
 *����Ǵ����˳���,������û��˳���ʱ��
 *������ܹ�����ClientsManager���е�clients
 *��Ϣ
 */
public class LogoutProcesser extends HeadInfoProcesser {
	
	private ArrayList<Client> memberList;
	
	public LogoutProcesser(ArrayList<Client> memberList) {
		this.memberList=memberList;
	}
	
	@Override
	public void process(Client logoutClient) throws Exception {
		throw new Exception("�����������");
	}

	@Override
	public void process(String logoutClientName) {
		synchronized (memberList) {
			Client tempClient;
			if ((tempClient = findClient(logoutClientName)) != null)
				memberList.remove(tempClient);
		}
		ClientsManager.updateNameList();
		ServerMainFraim.tdt.updateState(ClientsManager.counter, ClientsManager.userNameList);
		ClientsManager.sendAllListMessage();
	}
	
	private Client findClient(String userName)
	{
		synchronized (memberList) {
			Iterator<Client> it = this.memberList.iterator();
			while (it.hasNext()) {
				Client tempClient = it.next();
				if (tempClient.getUserName().equals(userName))
					return tempClient;
			}
			return null;
		}
		
	}

}
