package server_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 服务器的Socket服务
 * @author 林思鑫
 *
 */
public class TcpSocketServer {
	
	/**
	 * 服务器的ServerSocket
	 */
	private ServerSocket serverSocket;
	
	/**
	 * 用来保存在线用户的socketstream
	 */
	private HashMap<String, SocketStream> socketStreamMap;
	
	/**
	 * 用来保存在线的用户socket
	 */
	public HashMap<String, Socket> socketMap;
	
	/**
	 * 用来保存线程的信息
	 */
	public HashMap<String, Thread> threadMap;
	
	/**
	 * 用来保存在线用户名列表
	 */
	public ArrayList<String> userNameList;
	
	/**
	 * 内部类,保存了输入流和输出流信息
	 */
	private SocketStream ss;
	
	/**
	 * 保存当前的用户名
	 */
	private String currentUserName;
	
	public TcpSocketServer() {
		socketMap=new HashMap<>();
		userNameList=new ArrayList<>();
		threadMap=new HashMap<>();
	}
	
	/**
	 * 启动服务,接收客户端的连接,多线程的创建
	 */
	public  void startService(int port)
	{
		try {
			//新建一个serverSocket,可能会出现端口已经被占用的问题
			serverSocket=new ServerSocket(port);
			
			//开始监听并创建新的线程
			while(true)
			{
				Socket currentSocket=serverSocket.accept();
				Thread currentThread=null;
				currentThread=new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							ss=new SocketStream(currentSocket);
							String line=ss.br.readLine();//第一次读取的是头信息
							handleHeaderInfo(line, currentSocket,
									socketMap, userNameList);
							currentUserName=searchUserName(line);
						} catch (IOException e) {
							e.printStackTrace();
						}
					
					}
				});
				threadMap.put(currentUserName , currentThread);
				socketStreamMap.put(currentUserName, ss);				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 内部的Socket类
	 * @author 林思鑫
	 *
	 */
	private class SocketStream{
		private BufferedReader br;
		private PrintWriter pw;
		public SocketStream(Socket socket) throws IOException {
			this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw=new PrintWriter(socket.getOutputStream());
		}
		@SuppressWarnings("unused")
		private void closeStream() throws IOException
		{
			this.br.close();
			this.pw.close();
		}
	}
	
	/**
	 * 从头信息中获取用户名信息
	 * @param 从字符串中截取用户名
	 * @return 返回客户端的用户名
	 */
	private String searchUserName(String line)
	{
		int i=line.indexOf("UserName=");
		int length="UserName=".length();
		if(i!=-1)
			return line.substring(i+length,line.indexOf("&",length));
		else
			return "";
	}

	/**
	 * 获取用户名信息列表中的所有用户名,转换成特定格式的字符串(userName1&userName2&...&)
	 * @param nameList 保存了在线用户名信息的列表
	 * @return 特定格式的在线用户名字符串
	 */
	@SuppressWarnings("unused")
	private String listToString(ArrayList<String> nameList)
	{
		String returnString="";
		Iterator<String> it=nameList.iterator();
		while(it.hasNext())
		{
			returnString=returnString+it.next()+"&";
		}
		return returnString;
	}
	
	/**
	 * 用来处理头信息,获取客户端的信息,然后将用户信息添加到列表当中
	 * @param line 从输入流中读取的字符串
	 * @param currentSocket 发送信息过来的Socket
	 * @param socketMap 添加目标
	 * @param userNameList 添加目标
	 */
	private void handleHeaderInfo(String line,Socket currentSocket,
			Map<String, Socket> socketMap,ArrayList<String> userNameList)
	{
		//处理头信息
		if(line.substring(0, 6).equals("#head#"))
		{
			//组装socketMap和userList和threadMap
			String userName=searchUserName(line);
			socketMap.put(userName, currentSocket);
			userNameList.add(userName);
		}
		
	}
	
	/**
	 * 停止所有的服务
	 */
	@SuppressWarnings("deprecation")
	public void stopService()
	{
		Iterator<String> it=userNameList.iterator();
		String username;
		while(it.hasNext())
		{
			username=it.next();
			try {
				socketMap.get(username).close();
				threadMap.get(username).stop();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}

