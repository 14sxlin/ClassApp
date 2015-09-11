package socket;

import java.io.IOException;
/*
 * 林思鑫
 * 接口,作为Socket的客户端
 */
public interface MySocketServer {
	void createConnection(int port);		
	void close() throws IOException;
}
