package headInfoProcesser.processer;

import object.Client;

/**
 * ͷ��Ϣ�������ĳ�����
 * @author ��˼��
 *
 */
public abstract class HeadInfoProcesser {
		
	/**
	 * ���������б�����Ӷ���
	 * @param client ����һ��Client����
	 * @throws Exception 
	 */
	public abstract void process(Client client) throws Exception;
	
	/**
	 * ������ɾ���б��еĶ���
	 * @param userName �����û���
	 * @throws Exception 
	 */
	public abstract void process(String userName) throws Exception;
}
