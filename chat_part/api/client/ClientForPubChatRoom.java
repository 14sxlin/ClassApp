package api.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JList;
import javax.swing.JTextArea;

import gui.pubChatRoom.client.GuiForPublicChatRoom;
import object_client_server.Server;

/**
 * �ͻ��˵�socket����
 * @author ��˼��
 *
 */
public class ClientForPubChatRoom implements AsClient {
	/**
	 * �ͻ����û�������,������־�ͻ���
	 */
	private String userName;
	
	/**
	 * ����������
	 */
	private Server server;
	
	/**
	 * ��������罻���ı���
	 */
	private JTextArea jTextArea;
	
	/**
	 * ������ʾ���߻����ߵ��û�
	 */
	public JList<String> clientList;
	
	/**
	 * ���췽��
	 * @param userName ����ͻ��˵��û���
	 */
	public ClientForPubChatRoom(String userName) {
		this.userName=userName;
	}
	
	/**
	 * ���췽��
	 * @param userName �û���
	 * @param jTextArea �ⲿ�ı���
	 */
	public ClientForPubChatRoom(String userName,JTextArea jTextArea) {
		this(userName);
		this.jTextArea=jTextArea;
	}
	
	/**
	 * ��ʼ���ӷ�����,����ͷ��Ϣ���������������
	 * @param serverIp ��������ip��ַ
	 * @param serverPort �������ķ���˿�
	 * @throws IOException �׳��쳣�õ��������ദ��
	 */
	@Override
	public void startConnectServer(String serverIp,int serverPort) throws IOException
	{
		try {
			//�������ӷ�����
			server=new Server( new Socket(serverIp, serverPort));
			
			//����ͷ��Ϣ
			sendHeaderInfo(server.getSocketStream().getPrintWriter());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * ����ͷ��Ϣ��������
	 * @param pw ָ���������������
	 */
	@Override
	public void sendHeaderInfo(PrintWriter pw)
	{
		//Ŀǰֻ��������Ϣ
		pw.println("#head#"+"UserName="+this.userName
				+"&Ip="+this.server.getSocket().getInetAddress().getHostAddress());
		pw.flush();
	}

	/**
	 * ��������������Ϣ
	 * @param message Ҫ���͵���Ϣ
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
	 * ���շ��������͹�������Ϣ
	 * ����ǲ���ͷ��Ϣ,�����ͷ��ϢҪ������Ӧ�Ĵ���
	 * @param storeString ����������յ�����Ϣ
	 * @exception IOException ���������Ͽ����ӵ�ʱ��ᴥ�����
	 */
	@Override
	public void receiveMessageFromServer(StringBuilder storeString) throws IOException,SocketException {
		//TODO Auto-generated catch block
		//��������Ƿ��������ȶϿ������ӵĻ�,�����SocketException�Ĵ���
		//����Ӧ�ô���һ��
		if (server != null && server.getSocketStream()!=null) {
			String line="";
			while ((line = server.getSocketStream().getBufferReader().readLine()) != null) {
				if( line.contains("#head:"))
				{
					// TODO Auto-generated catch block
					//������ͷ��Ϣ,����Ҫȷ����ʲô���͵�ͷ��Ϣ
					//�б�����
					if(line.contains("list"))
					{
						//֪ͨgui�е�list�仯
						GuiForPublicChatRoom.clientGuiNotifier.updateList(line);
					}
					storeString.append(line + "\n");
					this.jTextArea.append("���յ�ͷ��Ϣ"+"\n");
					this.jTextArea.append(line+"\n");
				}
				else
				{
					storeString.append(line + "\n");
					this.jTextArea.append(line+"\n");
				}
			} 
		}
	}

	
	@Override
	public void sendLogoutMessage() {
		this.sendMessageToServer("#head:logout?username="+this.userName);
	}
}
