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
	
//������hashMap�û���arraylist��
	private ArrayList<Socket> onlineSocketList;//ͨ��Socket�Ϳ���ָ����ѯ��Ӧ����Ϣ��
	private ArrayList<String>  usernameList;
	
	private HashMap<String , Socket> name_socketMap;
	@SuppressWarnings("unused")
	private HashMap<String , Thread>  threadMap;
	
    private SocketStream ss;
    private ServerSocket serverSocket;
	public MockServer(JFrame host) throws IOException {
		
		super(host);
		super.setTitle("������");
		super.setLocation(0, 0);
		
//		this.setResizable(false);//��һ��ᵼ�²��������� ��֪��Ϊʲô ʲô��ƨ
		//�������Ϊ���Ͻǲ��Ǹ�����ͼ�����һ��С����
		
		
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
		
		//�½�һ��serverSocket,���ܻ���ֶ˿��Ѿ���ռ�õ�����
		serverSocket = new ServerSocket(MockPort.PORT);
System.out.println("���ڵȴ�����");
		

		//��������֮��,�˿����½���ȴ�״̬
		while(true)
		{
			Socket tempSocket=serverSocket.accept();
			//�������߳�
			Thread tempThread=new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						onlineSocketList.add(tempSocket);			
						
						//ʵ�ָ��ͻ��˷�����Ϣ
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
						ss.pw.println("���ӷ������ɹ�\n");
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

	
	//�ڲ���SocketStream
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
	
	
	//��ȡ�û��� mockר��
	public String searchUserName(String string)
	{
		int i=string.indexOf("UserName=");
		int length="UserName=".length();
		if(i!=-1)
			return string.substring(i+length,string.indexOf("&",length));
		else
			return "";
	}

	//��ȡ�û����б��е��û���
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
	
	//����ͷ��Ϣ
	public void handleHeaderInfo(String line,Socket currentSocket)
	{
		//����ͷ��Ϣ
		if(line.substring(0, 6).equals("#head#"))
		{
			//��װhashmap��usernamelist
			String userName=searchUserName(line);//�ɹ���ȡ
			jTextArea.append("expect:С����---value:"+userName+'\n');//��ʾ�ɹ�
			name_socketMap.put(userName, currentSocket);
			usernameList.add(userName); 

			//���������û��б���ͻ���  
			jTextArea.append("usernameLis="+listString(usernameList)+'\n');
			ss.pw.println("usernamelist="+listString(usernameList));
			ss.pw.flush();
		}
		
	}
}

