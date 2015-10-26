package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 *socket的流对象的管理<br>
 *实现该类的对象必须具有inputstream和outputstream的成员变量
 *@author 林思鑫
 */
public interface MySocketStream {
	/**
	 * 获得成员变量中的输入流
	 * @return 返回成员变量Inputstream
	 */
	InputStream getInStream();
	/**
	 * 获得成员变量中的输入流
	 * @return 返回成员变量Inputstream
	 */
	OutputStream getOutStream();
	/**
	 * 关闭输入流和输出流
	 * @throws IOException
	 */
	void closeStream() throws IOException;
	/**
	 * 将socket中输入流和输出流保存在成员变量里面
	 * @param socket 指定socket
	 * @throws IOException
	 */
	void setStream(Socket socket) throws IOException;
}
