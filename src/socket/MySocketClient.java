package socket;

import java.io.IOException;

/*
 * ��˼��
 * �ӿ�,��ΪSocket�Ŀͻ���
 */
public interface MySocketClient extends MySocketStream{
	void connect(String ip,int port) throws IOException;	
	void close() throws IOException;
}
