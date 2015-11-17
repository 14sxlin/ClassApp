package server_client;

import java.io.IOException;
import java.io.PrintWriter;

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
	void startConnectServer(String serverIp,int serverPort) throws IOException;

	/**
	 *���ͱ�������Ϣ��������
	 * @param out ָ��Ҫ���͵������
	 */
	 void sendHeaderInfo(PrintWriter out);
	
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
}
