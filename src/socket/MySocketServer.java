package socket;

import java.io.IOException;
import java.net.Socket;

/**
 * 
 * �ӿ�,��ΪSocket�Ŀͻ���
 * @author ��˼��
 */
public interface MySocketServer extends MySocketStream {
	/**
	 * ��ָ���Ķ˿ڴ�������
	 * @param port ָ���������Ķ˿�
	 * @return ����һ�����ͻ������ӵĶ���
	 */
	Socket createConnection(int port);		
	/**
	 * �ر�socket����
	 * @throws IOException
	 */
	void close() throws IOException;
}
