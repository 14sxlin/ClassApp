package tool;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import bean.OfficeInfo;
import internet.HttpTools;

/**
 * 这个是获取办公自动化信息的工具类
 * @author 林思鑫
 *
 */
public class AutoOfficeTools {
	
	private static final String WEBSITE = "http://notes.stu.edu.cn/csweb/list.jsp";
	public final static int TOTALINDEX  = 393 ;
	
	public static ArrayList<OfficeInfo> list ;
	private static StringBuilder sb = new StringBuilder();
	
	private static void openPage(int pageIndex) throws TimeoutException
	{
		String param = "pageindex=";
		//设置请求参数
		if(pageIndex<=TOTALINDEX&& pageIndex>=1)
			param+=pageIndex;
		else 
			param+=1;
		param+="&pagesize=10&totalcount=4061&totalindex=407"
				+ "&keyword=&fwdw=-1";
			if (sb != null && !sb.equals(""))
				sb.delete(0, sb.length());
			sb = HttpTools.post(WEBSITE, param, "gbk");
			
//		getTitle();
//		getDepartment();
//		getDate();
//		getLinks();
	}

	private static ArrayList<String> getTitle()
	{
		return subInfo("title='", "'>");
	}
	
	private static ArrayList<String> getLinks()
	{
		return subInfo("<a target ='_blank'  href='", "' title=");
	}
	
	private static ArrayList<String> getDepartment()
	{
		ArrayList<String> time_deparment = subInfo("align=\"center\">", "</TD>");
		String temp = time_deparment.toString().substring(1,
				time_deparment.toString().length()-1);
		String [] time_depart = temp.split(",");
		ArrayList<String> tempArr = new ArrayList<>(10);
		for(int i = 0 ; i<time_deparment.size(); i++)
		{
			if(i%2 == 0)
				tempArr.add(time_depart[i]);
		}
		return tempArr;
	}
	
	private static ArrayList<String> getDate()
	{
		ArrayList<String> time_deparment = subInfo("align=\"center\">", "</TD>");
		String temp = time_deparment.toString().substring(1,
				time_deparment.toString().length()-1);
		String [] time_depart = temp.split(",");
		ArrayList<String> tempArr = new ArrayList<>(10);
		for(int i = 0 ; i<time_deparment.size(); i++)
		{
			if(i%2 != 0)
				tempArr.add(time_depart[i]);
		}

		return tempArr;
	}
	
	private static ArrayList<String> subInfo(String startstr,String endstr) {
		ArrayList<String> list = new ArrayList<>(10);
		int beginIndex= 0;
		int endIndex = 0;
		while(beginIndex != -1 && endIndex != -1)
		{
			beginIndex = sb.indexOf(startstr,beginIndex);
			endIndex  =sb.indexOf(endstr,beginIndex);
			if(beginIndex != -1 && endIndex != -1)
				list.add(sb.substring(beginIndex+startstr.length(),endIndex));
			else break;
			beginIndex = endIndex+1;
		}
//		System.out.println(list.toString());	
//		System.out.println("length =  "+list.size());
		
		return list;
	}
	
	public static ArrayList<OfficeInfo> getInfoList(int pageIndex) throws TimeoutException
	{
		openPage(pageIndex);
		ArrayList<String>titles = getTitle();
		ArrayList<String>departments =getDepartment();
		ArrayList<String>dates =getDate();
		ArrayList<String>links =getLinks();
		ArrayList<OfficeInfo> temp = new ArrayList<>(10);
		for( int i=0; i<titles.size(); i++)
		{
			temp.add(new OfficeInfo(titles.get(i),
					departments.get(i),dates.get(i),links.get(i)));
		}
		list = temp;
		return temp;
	}
	
	/**
	 * @return 保存的办公自动化的信息的字符串
	 */
	public static String toStr() {
		if (list != null) {
			String temp = "";
			for (OfficeInfo info : list) {
				temp += (info.toString() + "\n\n");
			}
			return temp;
		}else 
			return "没有网页信息";
	}
	
	/**
	 * 获取详细网址的信息,之后会调用系统的浏览器进行访问
	 * @return 获取详细网址的信息
	 */
	public static String getDetailWebsite(OfficeInfo info)
	{
		String web = "http://notes.stu.edu.cn"+info.link;
		return web;
	}
}
