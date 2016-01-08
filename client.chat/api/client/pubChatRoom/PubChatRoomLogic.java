package api.client.pubChatRoom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JList;
import javax.swing.JTextArea;

import gui.pubChatRoom.GuiForPublicChatRoom;
import headinfoFilter.HeadType;
import object.AsClient;
import object.Server;
import tools.listprocesser.ListInfoProcesser;

/**
 * 客户端的socket管理
 * @author 林思鑫
 *
 */
public class PubChatRoomLogic implements AsClient {
	/**
	 * 客户端用户的名称,用来标志客户端
	 */
	private String userName;
	
	/**
	 * 服务器对象
	 */
	private Server server;
	
	public void setServer(Server server) {
		this.server = server;
	}

	/**
	 * 用来与外界交互的变量
	 */
	public JTextArea jTextArea;
	
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
		this.jTextArea=gui.jTextArea;
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
		//目前只有两个信息
		server.getSocketStream().getPrintWriter().println(HeadType.LOGIN+this.userName+"#");
		server.getSocketStream().getPrintWriter().flush();
	}
	
	@Override
	public void sendLogoutInfo(Server server) {
		server.getSocketStream().getPrintWriter().println(HeadType.LOGOUT+this.userName+"#");
		server.getSocketStream().getPrintWriter().flush();
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
					if( line.contains(HeadType.LIST))
					{
						// TODO Auto-generated catch block
						try {
							//通知gui中的list变化
							gui.classmateList.setModel(ListInfoProcesser.createListModel(userName,line));
						} catch (NullPointerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						storeString.append(line + "\n");
						this.jTextArea.append("接收到list信息"+"\n");
						this.jTextArea.append(line+"\n");
					}
					else
					{
						storeString.append(line + "\n");
						this.jTextArea.append(line+"\n");
					}
				} 
			}
		} catch (SocketException e) {
			this.jTextArea.setText("与服务断开连接\n");
		}
	}

	public Server getServer() {
		return this.server;
	}
	
}
