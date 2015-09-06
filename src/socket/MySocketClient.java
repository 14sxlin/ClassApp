package socket;

import java.io.IOException;

public interface MySocketClient {
	void connect(String ip,int port) throws IOException;	
	void close() throws IOException;
}
