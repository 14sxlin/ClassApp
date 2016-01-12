package api.client.pubChatRoom;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JList;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import classapp.mainframe.ClassAppMainFrame;
import gui.pubChatRoom.GuiForPublicChatRoom;
import object.AsClient;
import object.HeadType;
import object.Server;
import tools.GroupChatListProcesser;
import tools.GroupChatProcesser;
import tools.ListInfoProcesser;

/**
 * 客户端的socket管理
 * @author 林思鑫
 *
 */
public class PubChatRoomLogic implements AsClient {
	/**
	 * 客户端用户的名称,用来标志客户端
	 */
	protected String userName;
	
	/**
	 * 服务器对象
	 */
	protected Server server;
	
	public void setServer(Server server) {
		this.server = server;
	}
	
	/**
	 * 用来显示在线或不在线的用户
	 */
	public JList<String> clientList;
	
	/**
	 * 与gui板块的通信,一个引用
	 */
	public GuiForPublicChatRoom gui;
	/**
	 * 构造方法
	 * @param userName 传入客户端的用户名
	 */
	public PubChatRoomLogic(String userName,GuiForPublicChatRoom gui) {
		this.userName=userName;
		this.gui = gui;
	}
	
	public PubChatRoomLogic() {
	}
	
	/**
	 * 开始连接服务器,发送头信息到输出流给服务器
	 * @param serverIp 服务器的ip地址
	 * @param serverPort 服务器的服务端口
	 * @throws IOException 抛出异常让调用它的类处理
	 */
	@Override
	public void startConnectServer(String serverIp,int serverPort) throws IOException
	{
		try {
			//尝试连接服务器
			server=new Server( new Socket(serverIp, serverPort));
			
			//发送头信息
			sendLoginInfo(server);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void sendLoginInfo(Server server)
	{
		try {
			server.getSocketStream().getPrintWriter().println(HeadType.LOGIN+this.userName+"#");
			server.getSocketStream().getPrintWriter().flush();
		} catch (NullPointerException e) {
		}
	}
	
	@Override
	public void sendLogoutInfo(Server server) {
		try {
			server.getSocketStream().getPrintWriter().println(HeadType.LOGOUT+this.userName+"#");
			server.getSocketStream().getPrintWriter().flush();
		} catch (NullPointerException e) {
		}
	}

	/**
	 * 给服务器发送消息
	 * @param message 要发送的消息
	 */
	@Override
	public void sendMessageToServer(String message)
	{
		if(server.getSocketStream() != null)
		{
			PrintWriter pw=server.getSocketStream().getPrintWriter();
			pw.println(message);
			pw.flush();
		}
	}
	
	/**
	 * 加上自己的名字
	 * @param message
	 */
	public void sendMessageWithName(String message)
	{
		sendMessageToServer(ClassAppMainFrame.username+" 说:  "+message);
	}


	/**
	 * 接收服务器发送过来的消息
	 * 检查是不是头信息,如果是头信息要做出相应的处理
	 * @param storeString 用来储存接收到的消息
	 * @exception IOException 当服务器断开连接的时候会触发这个
	 */
	@Override
	public void receiveMessageFromServer(StringBuilder storeString) throws IOException {
		try {
			if (server != null && server.getSocketStream()!=null) {
				String line="";
				while ((line = server.getSocketStream().getBufferReader().readLine()) != null) {
					try {
						
						// TODO System Output Test Block
						addStringWithColor(line, Color.cyan);
						
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if( line.contains(HeadType.LIST))
					{
						try {
							
							//通知gui中的list变化
							gui.classmateList.setModel(ListInfoProcesser.createListModel(userName,line));
							
						} catch (NullPointerException e) {
							e.printStackTrace();
						}
						storeString.append(line + "\n");
					}
					else if( line.contains(HeadType.GROUP) ||  line.contains(HeadType.GSEND) )//处理组聊信息
					{
						GroupChatProcesser.process(line, this);
					}
					else if(line.contains(HeadType.GIN) ||  line.contains(HeadType.GOUT))//处理加入退出的信息
					{
						GroupChatListProcesser.updateList(line);
					}
					else
					{
						storeString.append(line + "\n");
						if(isMyself(line))
						{
							try {
								addStringWithColor(line , Color.blue);
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
								
						}else
						{
							
							try {
								addStringWithColor(line, Color.red);
								
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
						}
						
					}
					try {
						gui.my_jtextPane.setCaretPosition(gui.my_jtextPane.getDocument().getLength());
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						//什么情况?
						e.printStackTrace();
					}
				} 
			}
		} catch (SocketException e) {
			gui.my_jtextPane.setText("与服务断开连接\n");
		}
	}

	private void addStringWithColor(String line,Color color) throws BadLocationException {
		int i = gui.my_jtextPane.getDocument().getLength();
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setForeground(set, color);
		gui.my_jtextPane.getDocument().insertString(i, line+"\n", set);
	}

	public Server getServer() {
		return this.server;
	}

	@Override
	public boolean isMyself(String message) {
		
		if(ClassAppMainFrame.username == null)
			return false;
		else
		{
			int i = message.indexOf(" 说");
			if(i != -1)
			{
				 try {
					if (message.substring(i-ClassAppMainFrame.username.length() , i).equals(ClassAppMainFrame.username ))
					{	
						return true;
					}
						else return false;
				} catch (Exception e) {
					
					return false;
				}
				
			}else return false;
			
		}
	}
	
}
