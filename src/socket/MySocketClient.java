package socket;

import java.io.IOException;

/*
 * ��˼��
 * �ӿ�,��ΪSocket�Ŀͻ���
 */
public interface MySocketClient {
	void connect(String ip,int port) throws IOException;	
	void close() throws IOException;
}
