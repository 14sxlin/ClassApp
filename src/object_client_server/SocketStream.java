package object_client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ��������һ������socket��������
 * @author ��˼��
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
		 * �ر���
		 * @throws IOException
		 */
		public  void closeStream() throws IOException
		{
			this.br.close();
			this.pw.close();
		}
}
