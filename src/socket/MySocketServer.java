package socket;

import java.io.IOException;
/*
 * ��˼��
 * �ӿ�,��ΪSocket�Ŀͻ���
 */
public interface MySocketServer {
	void createConnection(int port);		
	void close() throws IOException;
}
