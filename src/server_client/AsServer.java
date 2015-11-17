/**
 * 
 */
package server_client;

import java.io.IOException;

/**
 * ���������������Լ����Ϊ��������Ҫ�ķ���
 * @author ��˼��
 */
public interface AsServer{
	
	/**
	 * ��������
	 * @param port ����Ķ˿�
	 */
	void startService(int port);
	
	/**
	 * �����˵�¼��ʱ��Ҫ���������б�
	 * @throws IOException �ύ�쳣,���������ߴ���
	 */
	void  notifyLogin() throws IOException;
	
	/**
	 * �������˳���ʱ��Ҫ����һ�������б�
	 */
	void  notifyLogout();
	
	/**
	 * ������ע���ʱ��Ҫ�����ܵ�ע���û����б�
	 */
	void registerUpdate();

	
}
