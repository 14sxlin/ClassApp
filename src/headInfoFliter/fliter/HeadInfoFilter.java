package headInfoFliter.fliter;

import java.util.ArrayList;

import object_client_server.Client;

/**
 * ͷ��Ϣ�������ĳ�����
 * @author ��˼��
 *
 */
public abstract class HeadInfoFilter {
		
	/**
	 * ���������б�����Ӷ���
	 * @param client ����һ��Client����
	 * @return �����µĿͻ����б�
	 */
	public abstract ArrayList<Client> process(Client client);
	
	/**
	 * ������ɾ���б��еĶ���
	 * @param userName �����û���
	 * @return �����µĿͻ����б�
	 */
	public abstract ArrayList<Client> process(String userName);
}
