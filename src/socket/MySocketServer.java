package socket;

import java.io.IOException;
import java.net.Socket;

/**
 * ��������Socket�ķ����,�ȴ�����socket�Ľ���
 * @author ��˼��
 */
public interface MySocketServer extends MySocketStream {
	/**
	 * ��ָ���Ķ˿ڴ�������
	 * @param port ָ���������Ķ˿�
	 * @return ����һ�����ͻ������ӵĶ���
	 * @throws IOException 
	 */
	Socket createConnection(int port) throws IOException;		
	/**
	 * �ر�socket����
	 * @throws IOException
	 */
	void close() throws IOException;
}
