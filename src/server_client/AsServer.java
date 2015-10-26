/**
 * 
 */
package server_client;

import java.util.ArrayList;
import java.util.HashMap;

import socket.MySocketClient;
import socket.MySocketServer;

/**
 * ���������������Լ����Ϊ��������Ҫ�ķ���
 * @author ��˼��
 *  
 */
public abstract class AsServer implements MySocketServer{
	/**
	 * ���ӵ��������Ŀͻ��˵��б�,���汣�����ѧ�ŵ��ַ���<br>
	 * ������ʶ��Щ������
	 */
	protected ArrayList<String>onlineList;
	/**
	 * ����ѧ�ż����Ӧ��Client����
	 */
	protected HashMap<String, MySocketClient> ipMap;
	/**
	 * �������ͻ���֮��ʵ����Ϣ�ķ���
	 * @param c1 �ͻ���1
	 * @param c2 �ͻ���2
	 */
	abstract protected void  sendMessage(MySocketClient c1,MySocketClient c2);
	/**
	 * �������˳���ʱ��Ҫ����һ�������б�
	 */
	abstract protected void  updateList();
	/**
	 * ��ָ���Ŀͻ�����ӵ������б�
	 * @param client ָ���Ŀͻ���
	 */
	abstract protected void  addNewClient(MySocketClient client);
}
