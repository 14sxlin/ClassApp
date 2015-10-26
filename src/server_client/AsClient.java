package server_client;

import socket.MySocketClient;
import socket.MySocketServer;
/**
 * <p>
 * �涨����Ϊ�ͻ��˵ķ���,�����Ҳʵ����Mysocket��
 * ����Ϊ����һ��һ���ӵ�ʱ��,�ܹ����ܵ����˵�����
 * ������һ��һ���ӵ�ʱ��,�Ͳ���ͨ��������ת����Ϣ��
 * </p>
 * @author ��˼��
 *
 */
public abstract class AsClient implements MySocketClient,MySocketServer {
	/**
	 * ͨ��������������ӱ�ʶ,���÷�����ȷ�����͵�Ŀ��
	 * @param client ָ��һ��������Ϊ������Ϣ��Ŀ��<br>
	 * 					�������Ļ����ͨ�����������͵��ͻ��˵������б�ָ����.
	 * @return ����һ����ʶ,�������뵽�������
	 */
	abstract protected String sendDestinationHeader(MySocketClient client);
}
