package api.notifier;

import java.io.IOException;
import java.util.ArrayList;

import object_client_server.Client;

public abstract class Notifier {
	
	/**
	 * �����б��е������û�
	 * @throws IOException �ύ�쳣,���������ߴ���
	 */
	public abstract void  notify(ArrayList<Client> memberList) throws IOException;	

}
