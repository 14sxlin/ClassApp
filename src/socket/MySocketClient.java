package socket;

import java.io.IOException;
import java.net.Socket;

/**
 * ��������ָ���ļ����
 * @author ��˼��
 *
 */
public interface MySocketClient extends MySocketStream{
	/**
	 * ����ָ����socketͨ��
	 * @param ip ָ���Է���IP
	 * @param port ָ���Է��Ķ˿�
	 * @return 
	 * @throws IOException
	 */
	Socket connect(String ip,int port) throws IOException;	
	/**
	 * �ر�socketͨ��
	 * @throws IOException
	 */
	void close() throws IOException;
	
}
