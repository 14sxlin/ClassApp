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
		throw new Exception("不要调用我");

	}

	@Override
	synchronized public void process(String groupinfo) throws Exception {
		// TODO System Output Test Block
		System.out.println(" 服务器接收到的groupinfo =  "+groupinfo);
		if(groupinfo.contains(HeadType.GROUP))//组建的情况
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
					{	ClientsManager.sendMessage(username, sender+" 邀请你加入组聊 (本消息只有你能看见)",true);
					   ClientsManager.sendMessage(username, headinfo,false);
					}
				}
			}
			ClientsManager.sendMessage(sender, "组建成功(本消息只有你能看见)",true);
			
		}else if(groupinfo.contains(HeadType.GSEND))//处理发送的情况
		{
			String headinfo = processSend(groupinfo);
			String[] namelist = null ;
			if(usernamelist != null)
				namelist = usernamelist.split("&");
			if(namelist != null)
			{
				for(String username:namelist)
				{
					if(!username.trim().equals(this.sender))//sender不用发送
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
	 * groupmark:message 的情况
	 * 前者是group的标记, message时候要发送的信息
	 * @param content
	 */
	private String processSend(String content) 
	{
		// TODO System Output Test Block
//		System.out.println(" 处理发送 ");
		String[] temp = FormatInfo.filte3(filteContent(content));
		this.usernamelist = temp[0];
		this.groupMark = temp[1];
		this.message = temp[2];

		return HeadType.GSEND+"!"+this.groupMark+":"+TimeAddtion.getTime()+this.message+"#";
		
	}
	
	/**
	 * 这个是组建group的处理
	 * 格式是 sender!username&username2:mark
	 * 有两种情况,一种格式是组建的情况,其中包含了sender!,用感叹号来判断
	 * 另一种情况是对组内发信息
	 */
	private String processMakeup(String content)
	{
		// TODO System Output Test Block
//		System.out.println(" 处理makeup ");
		String[] temp = FormatInfo.filte3(filteContent(content));
		this.sender = temp[0];
		this.usernamelist =temp[1];
		this.message = temp[2];//这个message其实是mark
		return HeadType.GROUP+this.sender+"!"+usernamelist+":"+this.message+"#";
	}
	
	/**
	 * 处理群聊组加人的情况<br>
	 * 首先是发送给被邀请的人信息,让他先建组<br>
	 * 其次是通知邀请者的组加人<br>
	 * 发过来的格式:<br>
	 * #group:in?content=origin_usernames!mark:namelist#<br>
	 * 发过去的格式:<br>
	 * #group:in? ! mark:namelist#<br>
	 * @return
	 */
	private void processGroupIn(String content)
	{
		// TODO System Output Test Block
		// TODO System Output Test Block
		System.out.println("  ");
		System.out.println(" 处理组内登录 ");
		String[] temp = FormatInfo.filte3(filteContent(content));
		String[] originnames = temp[0].split("&");
		String[] newnames = temp[2].split("&");
		
		
		//让新成员建立组
		String newinfo = 
				HeadType.GROUP+ "!"+temp[0]+"&"+temp[2]+":"+temp[1]+"#";
		// TODO System Output Test Block
		// TODO System Output Test Block
		System.out.println("  ");
		System.out.println(" 让新成员建立的信息 =  "+ newinfo);
		for(int i=0; i<newnames.length; i++)
		{
			ClientsManager.sendMessage(newnames[i], "你被邀请加入讨论组",true);
			ClientsManager.sendMessage(newnames[i], newinfo,false);
		}
		
		//让旧成员加人的信息
		String oldinfo = HeadType.GIN+"!"+temp[1]+":"+temp[2]+"#";
		// TODO System Output Test Block
		// TODO System Output Test Block
		System.out.println("  ");
		System.out.println(" 让旧成员加人的信息 =  "+oldinfo);
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
