package socket;

import java.io.IOException;

/**
 * �й�socket����Ϊ�ͻ��˵Ľӿ�
 * @author ��˼��
 *
 */
public interface MySocketClient extends MySocketStream{
	/**
	 * ����ָ����socketͨ��
	 * @param ip ָ���Է���IP
	 * @param port ָ���Է��Ķ˿�
	 * @throws IOException
	 */
	void connect(String ip,int port) throws IOException;	
	/**
	 * �ر�socketͨ��
	 * @throws IOException
	 */
	void close() throws IOException;
}
