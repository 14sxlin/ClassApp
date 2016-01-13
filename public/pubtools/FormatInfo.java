package pubtools;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * 这个类是用来处理具有:<br>
 * 特殊格式的字符串的
 * @author LinSixin sparrowxin@sina.cn
 *
 */
public class FormatInfo {
	
	/**
	 *  #### ! ##### : ##### 这种三段形式的
	 * @param formatString
	 * @return 返回一个数组,信息依次排列
	 */
	public static String[] filte3(String formatString)
	{
		try {
			HeadInfoFormatChecker.check3(formatString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int index1  =formatString.indexOf("!");
		int index2 = formatString.indexOf(":");
		if( index2 != -1&& index1 != -1)
		{
			String [] str = new String[3];
			str[0] = formatString.substring(0, index1);
			str[1] = formatString.substring(index1+1, index2);
			str[2] = formatString.substring(index2+1);
			return str;
		}else
			throw new NullPointerException();		
	}
	
	/**
	 * ##### : ##### 这种二段形式的
	 * @param formatString
	 * @return 返回一个数组,信息依次排列
	 */
	public static String[] filte2(String formatString)
	{
		try {
			HeadInfoFormatChecker.check2(formatString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int index1 = formatString.indexOf(":");
		if (index1 != -1) {
			String[] str = new String[2];
			str[0] = formatString.substring(0, index1);
			str[1] = formatString.substring(index1 + 1);
			return str;
		}else
			throw new NullPointerException();
	}
	
	/**
	 *  username&username1&username2
	 *  <br>将其转化成一个数组
	 * @param formatString
	 * @return 返回一个数组,信息依次排列
	 */
	public static String[] fillteName(String formatString)
	{
		return formatString.split("&");
	}

	/**
	 * 将含有用户名列表中的所有用户名转换成字符串
	 * @param usernamelist
	 * @return username1 & username2 & username3
	 */
	public static String formatNames(JList<String>usernamelist)
	{
		return formatNames(usernamelist, false, null);
	}
	
	/**
	 * 将含有用户名列表中的所有用户名转换成字符串<br>
	 * @param usernamelist
	 * @param ridName 当ridName为true的时候,过滤掉传进来的ridUserName
	 * @param ridUsername
	 * @return username1 & username2 & username3
	 */
	public static String formatNames(JList<String>usernamelist, boolean ridName,String ridUsername)
	{
		if(usernamelist != null)
		{
			String temp = "";
			DefaultListModel<String> model = 
					(DefaultListModel<String>) usernamelist.getModel();
			for(int i=0; i<model.getSize(); i ++)
			{
				if(!( model.getElementAt(i).equals("")
						&& model.getElementAt(i)!= null))
					if(ridName)
					{
						if(!(model.getElementAt(i).equals(ridUsername.trim())))
							temp+=(model.getElementAt(i)+"&");
					}
					else
					{
						temp+=(model.getElementAt(i)+"&");
					}
			}
			return  temp.substring(0,temp.length()-1);
		}else throw new NullPointerException();
	}
}
