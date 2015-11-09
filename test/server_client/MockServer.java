package server_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import gui.client.PublicChatRoomForUser;


public class MockServer extends PublicChatRoomForUser  {

	private static final long serialVersionUID = 1L;
	
//测试用hashMap好还是arraylist好
	private ArrayList<Socket> onlineSocketList;//通过Socket就可以指定查询相应的信息了
	private ArrayList<String>  usernameList;
	
	private HashMap<String , Socket> name_socketMap;
	@SuppressWarnings("unused")
	private HashMap<String , Thread>  threadMap;
	
    private SocketStream ss;
    private ServerSocket serverSocket;
	public MockServer(JFrame host) throws IOException {
		
		super(host);
		super.setTitle("服务器");
		super.setLocation(0, 0);
		
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
		threadMap=new HashMap<>();
		
		//新建一个serverSocket,可能会出现端口已经被占用的问题
		serverSocket = new ServerSocket(MockPort.PORT);
System.out.println("正在等待连接");
		

		//建立连接之后,端口重新进入等待状态
		while(true)
		{
			Socket tempSocket=serverSocket.accept();
			//建立多线程
			Thread tempThread=new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						onlineSocketList.add(tempSocket);			
						
						//实现给客户端发送消息
						jTextField.addKeyListener(new KeyListener() {
							
							@Override
							public void keyTyped(KeyEvent e) {
								if(e.getKeyCode()==KeyEvent.VK_ENTER)
								{
									System.out.println("invoke keyboard");
									String sendTxt=jTextField.getText();
									ss.pw.println(sendTxt);
									ss.pw.flush();
									jTextArea.append(sendTxt+"\n");
									jTextField.setText("");
								}
							}
							
							@Override
							public void keyReleased(KeyEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void keyPressed(KeyEvent e) {
								// TODO Auto-generated method stub
								
							}
						});
						sendButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								if(e.getSource()==sendButton)
								{
									String sendTxt=jTextField.getText();
									ss.pw.println(sendTxt);
									ss.pw.flush();
									jTextArea.append(sendTxt+"\n");
									jTextField.setText("");
								}
							}
						});
						
						
						ss=new SocketStream(tempSocket);
						ss.pw.println("连接服务器成功\n");
						String line;
						line=ss.br.readLine();
						handleHeaderInfo(line,tempSocket);
						
						while((line=ss.br.readLine())!=null)
						{	
							jTextArea.append(line+"\n");
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			tempThread.start();
			
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
		@SuppressWarnings("unused")
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
			e.printStackTrace();
		}
	}
	
	
	//获取用户名 mock专用
	public String searchUserName(String string)
	{
		int i=string.indexOf("UserName=");
		int length="UserName=".length();
		if(i!=-1)
			return string.substring(i+length,string.indexOf("&",length));
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
	
	//处理头信息
	public void handleHeaderInfo(String line,Socket currentSocket)
	{
		//处理头信息
		if(line.substring(0, 6).equals("#head#"))
		{
			//组装hashmap和usernamelist
			String userName=searchUserName(line);//成功截取
			jTextArea.append("expect:小林子---value:"+userName+'\n');//显示成功
			name_socketMap.put(userName, currentSocket);
			usernameList.add(userName); 

			//发送在线用户列表给客户端  
			jTextArea.append("usernameLis="+listString(usernameList)+'\n');
			ss.pw.println("usernamelist="+listString(usernameList));
			ss.pw.flush();
		}
		
	}
}

