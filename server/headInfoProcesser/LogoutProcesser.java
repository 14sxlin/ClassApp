package headInfoProcesser;

import java.util.ArrayList;
import java.util.Iterator;

import mainframe.ServerMainFraim;
import manager.Client;
import tools.ClientsManager;

/**
 * @author LinSixin sparrowxin@sina.cn
 *����Ǵ����˳���,������û��˳���ʱ��
 *������ܹ�����ClientsManager���е�clients
 *��Ϣ
 */
public class LogoutProcesser extends HeadInfoProcesser {
	
	private ArrayList<Client> memberList;
	
	public LogoutProcesser(ArrayList<Client> memberList) {
		this.type = STRING;	
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
		ClientsManager.sendAllClient(logoutClientName+ "  �˳���¼",true);
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
