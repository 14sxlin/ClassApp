package headInfoProcesser.processer;

import object.Client;
import object.HeadType;
import object.TimeAddtion;
import tools.ClientsManager;
import tools.FormatInfo;

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
	synchronized public void process(String groupinfo) throws Exception {
		// TODO System Output Test Block
		System.out.println(" ���������յ���groupinfo =  "+groupinfo);
		if(groupinfo.contains(HeadType.GROUP))//�齨�����
		{
			String headinfo = processMakeup(groupinfo);
			String[] namelist = null ;
			if(usernamelist != null)
				namelist = usernamelist.split("&");
			
			if(namelist != null)
			{
				for(String username:namelist)
				{
					if(!username.trim().equals(this.sender))
					{	ClientsManager.sendMessage(username, sender+" ������������� (����Ϣֻ�����ܿ���)",true);
					   ClientsManager.sendMessage(username, headinfo,false);
					}
				}
			}
			ClientsManager.sendMessage(sender, "�齨�ɹ�(����Ϣֻ�����ܿ���)",true);
			
		}else if(groupinfo.contains(HeadType.GSEND))//�����͵����
		{
			String headinfo = processSend(groupinfo);
			String[] namelist = null ;
			if(usernamelist != null)
				namelist = usernamelist.split("&");
			if(namelist != null)
			{
				for(String username:namelist)
				{
					if(!username.trim().equals(this.sender))//sender���÷���
					{	
					   ClientsManager.sendMessage(username, headinfo,false);
					}
				}
			}
			ClientsManager.sendAllClient(headinfo,false);
		}else if(groupinfo.contains(HeadType.GIN))
		{
			processGroupIn(groupinfo);
		}
		
	}
	
	/**
	 * groupmark:message �����
	 * ǰ����group�ı��, messageʱ��Ҫ���͵���Ϣ
	 * @param content
	 */
	private String processSend(String content) 
	{
		// TODO System Output Test Block
//		System.out.println(" ������ ");
		String[] temp = FormatInfo.filte3(filteContent(content));
		this.usernamelist = temp[0];
		this.groupMark = temp[1];
		this.message = temp[2];

		return HeadType.GSEND+"!"+this.groupMark+":"+TimeAddtion.getTime()+this.message+"#";
		
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
//		System.out.println(" ����makeup ");
		String[] temp = FormatInfo.filte3(filteContent(content));
		this.sender = temp[0];
		this.usernamelist =temp[1];
		this.message = temp[2];//���message��ʵ��mark
		return HeadType.GROUP+this.sender+"!"+usernamelist+":"+this.message+"#";
	}
	
	/**
	 * ����Ⱥ������˵����<br>
	 * �����Ƿ��͸������������Ϣ,�����Ƚ���<br>
	 * �����֪ͨ�����ߵ������<br>
	 * �������ĸ�ʽ:<br>
	 * #group:in?content=origin_usernames!mark:namelist#<br>
	 * ����ȥ�ĸ�ʽ:<br>
	 * #group:in? ! mark:namelist#<br>
	 * @return
	 */
	private void processGroupIn(String content)
	{
		// TODO System Output Test Block
		// TODO System Output Test Block
		System.out.println("  ");
		System.out.println(" �������ڵ�¼ ");
		String[] temp = FormatInfo.filte3(filteContent(content));
		String[] originnames = temp[0].split("&");
		String[] newnames = temp[2].split("&");
		
		
		//���³�Ա������
		String newinfo = 
				HeadType.GROUP+ "!"+temp[0]+"&"+temp[2]+":"+temp[1]+"#";
		// TODO System Output Test Block
		// TODO System Output Test Block
		System.out.println("  ");
		System.out.println(" ���³�Ա��������Ϣ =  "+ newinfo);
		for(int i=0; i<newnames.length; i++)
		{
			ClientsManager.sendMessage(newnames[i], "�㱻�������������",true);
			ClientsManager.sendMessage(newnames[i], newinfo,false);
		}
		
		//�þɳ�Ա���˵���Ϣ
		String oldinfo = HeadType.GIN+"!"+temp[1]+":"+temp[2]+"#";
		// TODO System Output Test Block
		// TODO System Output Test Block
		System.out.println("  ");
		System.out.println(" �þɳ�Ա���˵���Ϣ =  "+oldinfo);
		System.out.println("  ");
		for(int i=0; i<originnames.length; i++)
		{
			ClientsManager.sendMessage(originnames[i], oldinfo,false);
		}
		
	}
	
	private  String filteContent(String content)
	{
		int i = content.indexOf("content=");
		return content.substring(i+"content=".length(), content.lastIndexOf("#"));
	}
}
