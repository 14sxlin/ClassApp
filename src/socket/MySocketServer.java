package socket;

import java.io.IOException;

public interface MySocketServer {
	void createConnection(int port);		
	void close() throws IOException;
}
