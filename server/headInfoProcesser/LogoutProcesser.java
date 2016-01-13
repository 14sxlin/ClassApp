package headInfoProcesser;

import java.util.ArrayList;
import java.util.Iterator;

import mainframe.ServerMainFraim;
import manager.Client;
import tools.ClientsManager;

/**
 * @author LinSixin sparrowxin@sina.cn
 *这个是处理退出类,如果有用户退出的时候
 *这个类能够更新ClientsManager类中的clients
 *信息
 */
public class LogoutProcesser extends HeadInfoProcesser {
	
	private ArrayList<Client> memberList;
	
	public LogoutProcesser(ArrayList<Client> memberList) {
		this.type = STRING;	
		this.memberList=memberList;
	}
	
	@Override
	public void process(Client logoutClient) throws Exception {
		throw new Exception("不用这个方法");
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
		ClientsManager.sendAllClient(logoutClientName+ "  退出登录",true);
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
