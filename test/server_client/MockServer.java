package server_client;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;

import gui.PublicChatRoom;


public class MockServer extends PublicChatRoom {

	private static final long serialVersionUID = 1L;
	
//测试用hashMap好还是arraylist好
	private ArrayList<Socket> onlineSocketList;//通过Socket就可以指定查询相应的信息了
	private ArrayList<String>  usernameList;
	
	private HashMap<String, Socket> name_socketMap;
	
	
    private SocketStream ss;
    private ServerSocket serverSocket;
	public MockServer(JFrame host) throws IOException {
		
		super(host);
		super.setTitle("服务器");
		
//		this.setResizable(false);//这一句会导致不能连接上 不知道为什么 什么狗屁
		//具体表现为左上角不是个咖啡图标而是一个小盒子
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
//				Iterator<Socket> it=OnlineSocketList.iterator();
//				while(it.hasNext())
//					try {
//						Socket temp=it.next();
//						temp.getInputStream().close();
//						temp.getOutputStream().close();
//						temp.close();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		onlineSocketList=new ArrayList<>();
		name_socketMap=new HashMap<>();
		usernameList=new ArrayList<>();
		
//下面加到createConnection方法里面去
		
		//新建一个serverSocket,可能会出现端口已经被占用的问题
		serverSocket = new ServerSocket(MockPort.PORT);
System.out.println("正在等待连接");
		
//这里加到waitConnect(ArrayList<Socket>socket)方法中

		//建立连接之后,端口重新进入等待状态
		while(true)
		{
			Socket tempSocket=serverSocket.accept();
			
			onlineSocketList.add(tempSocket);			
			
			ss=new SocketStream(tempSocket);
			ss.pw.println("连接服务器成功\n");
			ss.pw.flush();//记得这里要flush,要不然客户端接收不到消息
			ss.pw.println("测试:第二次发送\n");
			ss.pw.flush();
			
//			//发送在线用户列表给客户端
//			this.jTextArea.append("usernameLis="+listString(usernameList));//这一句有问题
			
			ss.pw.println("测试:第三次发送\n");//发送失败
			ss.pw.flush();
			
//			ss.pw.println("usernamelist="+listString(usernameList));
//			ss.pw.flush();
			
			String line;
			line=ss.br.readLine();
			this.jTextArea.append(line+"\n");
			
//			//组装hashmap和usernamelist  这一段会导致不能多客户端
			String userName=searchUserName(line);//成功截取
			this.jTextArea.append("expect:小林子---value:"+userName+'\n');//显示成功
			name_socketMap.put(userName, tempSocket);
			usernameList.add(userName); 

			//发送在线用户列表给客户端  这一段会导致流的读取出问题
			this.jTextArea.append("usernameLis="+listString(usernameList));
			ss.pw.println("usernamelist="+listString(usernameList));
			ss.pw.flush();
			
			ss.closeStream();
		}
	}

	
	//内部类SocketStream
	private class SocketStream{
		private BufferedReader br;
		private PrintWriter pw;
		public SocketStream(Socket socket) throws IOException {
			this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.pw=new PrintWriter(socket.getOutputStream());
		}
		private void closeStream() throws IOException
		{
			this.br.close();
			this.pw.close();
		}
	}

	public static void main(String[] args) {
		try {
			new MockServer(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//获取用户名 mock专用
	public String searchUserName(String string)
	{
		int i=string.indexOf("UserName=");
		int length="UserName=".length();
		if(i!=-1)
			return string.substring(length,string.indexOf("&",length));
		else
			return "";
	}

	//获取用户名列表中的用户名
	public String listString(ArrayList<String> nameList)
	{
		String returnString="";
		Iterator<String> it=nameList.iterator();
		while(it.hasNext())
		{
			returnString=returnString+it.next()+"&";
		}
		return returnString;
	}
}

