/**
 * 
 */
package server_client;

import java.io.IOException;

import socket.MySocketClient;
import socket.MySocketServer;

/**
 * ���������������Լ����Ϊ��������Ҫ�ķ���
 * @author ��˼��
 *  
 */
public interface AsServer extends MySocketServer{
//	/**
//	 * ���ӵ��������Ŀͻ��˵��б�,���汣�����ѧ�ŵ��ַ���<br>
//	 * ������ʶ��Щ������
//	 */
//	protected ArrayList<String>onlineList;
//	/**
//	 * ����ѧ�ż����Ӧ��Client����
//	 */
//	protected HashMap<String, MySocketClient> ipMap;
//	/**
//	 * �������ͻ���֮��ʵ����Ϣ�ķ���
//	 * @param c1 �ͻ���1
//	 * @param c2 �ͻ���2
//	 */
	abstract void  sendMessage(MySocketClient c1,MySocketClient c2);
	/**
	 * �������˳���ʱ��Ҫ����һ�������б�
	 */
	abstract void  updateList();
	/**
	 * ��ָ���Ŀͻ�����ӵ������б�
	 * @throws IOException 
	 */
	abstract void  addNewClient() throws IOException;
}
