package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface MySocketStream {
	InputStream getInStream();
	OutputStream getOutStream();
	void closeStream() throws IOException;
	void setStream(Socket socket) throws IOException;
}
