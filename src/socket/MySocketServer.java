package socket;

import java.io.IOException;
import java.net.Socket;

/*
 * ��˼��
 * �ӿ�,��ΪSocket�Ŀͻ���
 */
public interface MySocketServer extends MySocketStream {
	Socket createConnection(int port);		
	void close() throws IOException;
}
