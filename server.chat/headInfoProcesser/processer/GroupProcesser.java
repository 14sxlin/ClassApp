package headInfoProcesser.processer;

import object.Client;
import tools.clientmanager.ClientsManager;

public class GroupProcesser extends HeadInfoProcesser {

	private String message;
	private String usernamelist;
	private String sender;
	
	public GroupProcesser() {
		this.type = STRING;
	}
	
	@Override
	public void process(Client client) throws Exception {
		throw new Exception("��Ҫ������");

	}

	@Override
	public void process(String groupContent) throws Exception {
		processContent(groupContent);
		String[] namelist = null ;
		if(usernamelist != null)
			namelist = usernamelist.split("&");
		
		if(namelist != null)
		{
			for(String username:namelist)
			{
				ClientsManager.sendMessage(username, message);
			}
		}
		ClientsManager.sendMessage(sender, message);
		// TODO System Output Test Block
		System.out.println(" ���������յ�groupͷ��Ϣ ");
	}
	
	/**
	 * �����û����б�ͷ��͵���Ϣ
	 * ��ʽ�� #head:group?content=sender!username&username2:message#
	 */
	private void processContent(String groupContent) 
	{
		// TODO System Output Test Block
		System.out.println(" groupcontetn =  "+ groupContent);
		int index2 = groupContent.indexOf(":");
		if( index2 != -1)
		{
			this.usernamelist = groupContent.substring(0, index2);
			// TODO System Output Test Block
			System.out.println(" usname =  "+ this.usernamelist);
			this.message = groupContent.substring(index2+1);
			// TODO System Output Test Block
			System.out.println(" message =  "+this.message);
		}else
			throw new NullPointerException();		
		
	}
}
