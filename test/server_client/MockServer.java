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
	
//������hashMap�û���arraylist��
	private ArrayList<Socket> onlineSocketList;//ͨ��Socket�Ϳ���ָ����ѯ��Ӧ����Ϣ��
	private ArrayList<String>  usernameList;
	
	private HashMap<String, Socket> name_socketMap;
	
	
    private SocketStream ss;
    private ServerSocket serverSocket;
	public MockServer(JFrame host) throws IOException {
		
		super(host);
		super.setTitle("������");
		
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
		
//����ӵ�createConnection��������ȥ
		
		//�½�һ��serverSocket,���ܻ���ֶ˿��Ѿ���ռ�õ�����
		serverSocket = new ServerSocket(MockPort.PORT);
System.out.println("���ڵȴ�����");
		
//����ӵ�waitConnect(ArrayList<Socket>socket)������

		//��������֮��,�˿����½���ȴ�״̬
		while(true)
		{
			Socket tempSocket=serverSocket.accept();
			
			onlineSocketList.add(tempSocket);			
			
			ss=new SocketStream(tempSocket);
			ss.pw.println("���ӷ������ɹ�\n");
			ss.pw.flush();//�ǵ�����Ҫflush,Ҫ��Ȼ�ͻ��˽��ղ�����Ϣ
			ss.pw.println("����:�ڶ��η���\n");
			ss.pw.flush();
			
//			//���������û��б���ͻ���
//			this.jTextArea.append("usernameLis="+listString(usernameList));//��һ��������
			
			ss.pw.println("����:�����η���\n");//����ʧ��
			ss.pw.flush();
			
//			ss.pw.println("usernamelist="+listString(usernameList));
//			ss.pw.flush();
			
			String line;
			line=ss.br.readLine();
			this.jTextArea.append(line+"\n");
			
//			//��װhashmap��usernamelist  ��һ�λᵼ�²��ܶ�ͻ���
			String userName=searchUserName(line);//�ɹ���ȡ
			this.jTextArea.append("expect:С����---value:"+userName+'\n');//��ʾ�ɹ�
			name_socketMap.put(userName, tempSocket);
			usernameList.add(userName); 

			//���������û��б���ͻ���  ��һ�λᵼ�����Ķ�ȡ������
			this.jTextArea.append("usernameLis="+listString(usernameList));
			ss.pw.println("usernamelist="+listString(usernameList));
			ss.pw.flush();
			
			ss.closeStream();
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
	
	
	//��ȡ�û��� mockר��
	public String searchUserName(String string)
	{
		int i=string.indexOf("UserName=");
		int length="UserName=".length();
		if(i!=-1)
			return string.substring(length,string.indexOf("&",length));
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
}

