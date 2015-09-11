package socket;

import java.io.IOException;

/*
 * 林思鑫
 * 接口,作为Socket的客户端
 */
public interface MySocketClient {
	void connect(String ip,int port) throws IOException;	
	void close() throws IOException;
}
