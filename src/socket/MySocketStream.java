package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/*
 * 林思鑫
 * 接口,作为Socket的流的返回
 */
public interface MySocketStream {
	InputStream getInStream();
	OutputStream getOutStream();
	void closeStream() throws IOException;
	void setStream(Socket socket) throws IOException;
}
