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
	synchronized public void process(String groupContent) throws Exception {
		// TODO System Output Test Block
		System.out.println(" 服务器接收到的groupinfo =  "+groupContent);
		if(groupContent.contains(HeadType.GROUP))//组建的情况
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
					{	ClientsManager.sendMessage(username, sender+" 邀请你加入组聊 (本消息只有你能看见)");
					   ClientsManager.sendMessage(username, headinfo);
					}
				}
			}
			ClientsManager.sendMessage(sender, "组建成功(本消息只有你能看见)");
			
		}else//已经组建好的情况
		{
			String headinfo = processSend(groupContent);
			String[] namelist = null ;
			if(usernamelist != null)
				namelist = usernamelist.split("&");
			if(namelist != null)
			{
				for(String username:namelist)
				{
					if(!username.trim().equals(this.sender))//sender不用发送
					{	
					   ClientsManager.sendMessage(username, headinfo);
					}
				}
			}
			ClientsManager.sendAllClient(headinfo,false);
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
		System.out.println(" 处理发送 ");
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
		System.out.println(" 处理makeup ");
		String[] temp = FormatInfo.filte3(filteContent(content));
		this.sender = temp[0];
		this.usernamelist =temp[1];
		this.message = temp[2];
		return HeadType.GROUP+this.sender+"!"+usernamelist+":"+this.message+"#";
	}
	
	private  String filteContent(String content)
	{
		int i = content.indexOf("content=");
		return content.substring(i+"content=".length(), content.lastIndexOf("#"));
	}
}
