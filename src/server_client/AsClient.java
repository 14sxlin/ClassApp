package server_client;

import java.io.OutputStream;

/**
 * <p>
 * �涨����Ϊ�ͻ��˵ķ���
 * ��һ��һ���ӵ�ʱ��,�ܹ����ܵ����˵�����
 * ������һ��һ���ӵ�ʱ��,�Ͳ���ͨ��������ת����Ϣ��
 * </p>
 * @author ��˼��
 *
 */
public interface AsClient  {
	
	/**
	 * ��ʼ���ӷ�����
	 */
	void startConnectServer();

	/**
	 *���ͱ�������Ϣ��������
	 * @param out ָ��Ҫ���͵������
	 */
	 void sendHeaderInfo(OutputStream out);

	 /**
	 * ������������˳�����Ϣ
	 */
	void sendLogoutInfo(OutputStream out);
}
