/**
 * 
 */
package api.server;

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
	 * ������ע���ʱ��Ҫ�����ܵ�ע���û����б�
	 */
	void registerUpdate();

	
}
