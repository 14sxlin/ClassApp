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
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import gui.pubChatRoom.client.GuiForPublicChatRoom;

@SuppressWarnings("serial")
public class MockClient extends GuiForPublicChatRoom{

	private Socket socketToServer;
	@SuppressWarnings("unused")
	private Socket socketToOther;
	private int serverPort;
	private SocketStream ss;
	private String userName="小林子";
	public MockClient(JFrame host) throws IOException {
		super(host);
		this.setTitle("客户端"+":userName="+userName);
//		this.setResizable(false);//这一句不知道为什么会导致神经错乱
		
		
		//创建自己的ServerSocket
//		this.socketToOther=this.createConnection(serverPort);
		super.addWindowListener(new WindowListener() {
			
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
//				try {
				//	ss.closeStream();//null poiter
//					socketToServer.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
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
		try {
			socketToServer=new Socket(MockPort.LOCAL_IP, MockPort.PORT);
			ss=new SocketStream(socketToServer);
			
			//发送头信息到服务器
			ss.pw.println("#head#"+"UserName="+this.userName
					+"&Ip="+this.socketToServer.getInetAddress().getHostAddress()
					+"&ServerPort="+serverPort+";");
			ss.pw.flush();
			
			//实现给服务器发送消息,监听回车键
			jTextField.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						String sendTxt=jTextField.getText();
						ss.pw.println(sendTxt);
						ss.pw.flush();
						jTextArea.append(sendTxt+"\n");
						jTextField.setText("");
					}
				}
			});
			super.sendButton.addActionListener(new ActionListener() {
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
		
			
			//读取服务器返回的消息
			String line=null;
			while((line=ss.br.readLine())!=null)
			{
				super.jTextArea.append(line+"\n");
			}
			
		} catch (SocketException e1) {
			this.jTextArea.append("服务器关闭");
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
			if(br!=null)
				this.br.close();
			if(pw!=null)
				this.pw.close();
		}
	}
	
	@SuppressWarnings("unused")
	private void setUserName()
	{
		//创建用户名用来表示用户,mock专用
		JTextField tempTextFiled;
		this.add(tempTextFiled=new JTextField());
		JButton tempButton=new JButton("设置用户名");
		tempButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("设置用户名"))
					userName=tempTextFiled.getText();
			}
		});
		this.add(tempButton);
	}
	
	public static void main(String[] args) {
		try {
			new MockClient(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
