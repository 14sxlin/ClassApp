/**
 * 
 */
package serverlogic;

import java.io.IOException;
import java.net.SocketException;

/**
 * ���������������Լ����Ϊ��������Ҫ�ķ���
 * @author ��˼��
 */
public interface AsServer{
	
	/**
	 * ��������
	 * @param port ����Ķ˿�
	 * @throws SocketException 
	 * @throws IOException 
	 */
	void startService(int port) throws SocketException, IOException;
	
	
	/**
	 * ������ע���ʱ��Ҫ�����ܵ�ע���û����б�
	 */
	void registerUpdate();

	
}
