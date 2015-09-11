package socket;

import java.io.IOException;
import java.net.Socket;

/*
 * 林思鑫
 * 接口,作为Socket的客户端
 */
public interface MySocketServer extends MySocketStream {
	Socket createConnection(int port);		
	void close() throws IOException;
}
