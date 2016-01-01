package object;

public class HeadInfoFilter {
	private final String head = "#head:"; 
	private final String content= "content=";
	private String readline;
	
	public HeadInfoFilter(String readline) {
		this.readline = readline;
	}
	
	public boolean isHeadInfo()
	{
		if(readline.contains("#head:"))
			return true;
		else return false;
	}
	
	/**
	 * 可能的值是
	 * list : 列表头信息
	 * login : 登录头信息
	 * logout : 登出头信息
	 * @return 过滤出标识
	 */
	public String filteType()
	{
		int start= -1;
		int end = -1;
		start = readline.indexOf("#head:")+head.length();
		end = readline.indexOf("?",start);
		if(start != -1 && end != -1)
			return readline.substring(start,end);
		else return "找不到标识";
	}
	
	/**
	 * @return 过滤出相应标识的内容
	 */
	public String filteContent()
	{
		int begin = readline.indexOf(content);
		if(begin != -1)
			begin+=content.length();
		else return "找不到内容";
		int end = readline.lastIndexOf("#");
		if(end != -1)
		{	
			return readline.substring(begin, end);
		}
		else return "找不到内容";
	}
}
