package headInfoProcesser.processer;

import headinfoFilter.HeadType;
import object.Client;
import tools.ClientsManager;

public class GroupProcesser extends HeadInfoProcesser {

	private String message;
	private String usernamelist;
	private String sender;
	private String groupMark;
	
	public GroupProcesser() {
		super.type = STRING;
	}
	
	@Override
	public void process(Client client) throws Exception {
		throw new Exception("��Ҫ������");

	}

	@Override
	synchronized public void process(String groupContent) throws Exception {
		if(groupContent.contains("!"))//�齨�����
		{
			String headinfo = processMakeup(groupContent);
			String[] namelist = null ;
			if(usernamelist != null)
				namelist = usernamelist.split("&");
			
			if(namelist != null)
			{
				for(String username:namelist)
				{
					if(!username.trim().equals(this.sender))
					{	ClientsManager.sendMessage(username, sender+" ������������� (����Ϣֻ�����ܿ���)");
					   ClientsManager.sendMessage(username, headinfo);
					}
				}
			}
			ClientsManager.sendMessage(sender, "�齨�ɹ�(����Ϣֻ�����ܿ���)");
			
		}else//�Ѿ��齨�õ����
		{
			String headinfo = processSend(groupContent);
			String[] namelist = null ;
			if(usernamelist != null)
				namelist = usernamelist.split("&");
			if(namelist != null)
			{
				for(String username:namelist)
				{
					if(!username.trim().equals(this.sender))//sender���÷���
					{	
					   ClientsManager.sendMessage(username, headinfo);
					}
				}
			}
			ClientsManager.sendAllClient(headinfo);
		}
	}
	
	/**
	 * groupmark:message �����
	 * ǰ����group�ı��, messageʱ��Ҫ���͵���Ϣ
	 * @param content
	 */
	private String processSend(String content) 
	{
//		int index1  =content.indexOf("!");
//		this.usernamelist = content.substring(0, index1);
		int index2 = content.indexOf(":");
		this.groupMark = content.substring(0, index2);
		this.message = content.substring(index2+1);
		// TODO System Output Test Block
System.out.println(" ������Ϣ  ���Ѿ����group�� = : "
		+HeadType.GSEND+this.groupMark+":"+this.message+"#");
		return HeadType.GSEND+this.groupMark+":"+this.message+"#";
		
	}
	
	/**
	 * ������齨group�Ĵ���
	 * ��ʽ�� sender!username&username2:mark
	 * ���������,һ�ָ�ʽ���齨�����,���а�����sender!,�ø�̾�����ж�
	 * ��һ������Ƕ����ڷ���Ϣ
	 */
	private String processMakeup(String content)
	{
		// TODO System Output Test Block
		System.out.println(" processmakeup  -----------");
		int index1  =content.indexOf("!");
		int index2 = content.indexOf(":");
		if( index2 != -1&& index1 != -1)
		{
			this.sender = content.substring(0, index1);
			this.usernamelist = content.substring(index1+1, index2);
			// TODO System Output Test Block
			System.out.println(" usname =  "+ this.usernamelist);
			this.message = content.substring(index2+1);//�����ʵ����groupmark
			// TODO System Output Test Block
			System.out.println(" message =  "+this.message);
			// TODO System Output Test Block
			System.out.println(" sender =  "+this.sender);
		}else
			throw new NullPointerException();		
		return HeadType.GROUP+this.sender+"!"+usernamelist+":"+this.message+"#";
	}
}
