package object;

import java.io.IOException;

import object.Server;

/**
 * �涨����Ϊ�ͻ��˵ķ���
 * @author ��˼��
 *
 */
public interface AsClient  {
	
	/**
	 * ��ʼ���ӷ�����
	 */
	void startConnectServer(String serverIp,int serverPort) throws IOException;

	/**
	 *���ͱ�����¼����Ϣ��������
	 * @param out ָ��Ҫ���͵������
	 */
	 void sendLoginInfo(Server server);
	
	 /**
	  * ������������˳�����Ϣ
	  * @param out ָ��Ҫ���͵������
	  */
	 void sendLogoutInfo(Server server);
	 
	/**
	 * �������������͹�������Ϣ
	 * @param storeString ���������Ѿ�����������Ϣ
	 * @throws IOException 
	 */
	void receiveMessageFromServer(StringBuilder storeString) throws IOException;
	
	/**
	 * �������������Ϣ
	 * @param message Ҫ���͵���Ϣ
	 */
	void sendMessageToServer(String message);

	/**
	 * @return ���ر��ͻ��˰󶨵ķ�����
	 */
	Server getServer();
}
