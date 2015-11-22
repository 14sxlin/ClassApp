package object_client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 用来构造一个保存socket的流的类
 * @author 林思鑫
 *
 */
public class SocketStream {

		private BufferedReader br;
		private  PrintWriter pw;
		
		public  SocketStream(Socket socket) throws IOException {
			this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw=new PrintWriter(socket.getOutputStream());
		}
		
		
		public BufferedReader getBufferReader() {
			return br;
		}


		public PrintWriter getPrintWriter() {
			return pw;
		}


		/**
		 * 关闭流
		 * @throws IOException
		 */
		public  void closeStream() throws IOException
		{
			this.br.close();
			this.pw.close();
		}
}
