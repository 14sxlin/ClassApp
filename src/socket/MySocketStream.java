package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 *socket��������Ĺ���<br>
 *ʵ�ָ���Ķ���������inputstream��outputstream�ĳ�Ա����
 *@author ��˼��
 */
public interface MySocketStream {
	/**
	 * ��ó�Ա�����е�������
	 * @return ���س�Ա����Inputstream
	 */
	InputStream getInStream();
	/**
	 * ��ó�Ա�����е�������
	 * @return ���س�Ա����Inputstream
	 */
	OutputStream getOutStream();
	/**
	 * �ر��������������
	 * @throws IOException
	 */
	void closeStream() throws IOException;
	/**
	 * ��socket��������������������ڳ�Ա��������
	 * @param socket ָ��socket
	 * @throws IOException
	 */
	void setStream(Socket socket) throws IOException;
}
