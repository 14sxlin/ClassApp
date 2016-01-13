package pubtools;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * ������������������:<br>
 * �����ʽ���ַ�����
 * @author LinSixin sparrowxin@sina.cn
 *
 */
public class FormatInfo {
	
	/**
	 *  #### ! ##### : ##### ����������ʽ��
	 * @param formatString
	 * @return ����һ������,��Ϣ��������
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
	 * ##### : ##### ���ֶ�����ʽ��
	 * @param formatString
	 * @return ����һ������,��Ϣ��������
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
	 *  <br>����ת����һ������
	 * @param formatString
	 * @return ����һ������,��Ϣ��������
	 */
	public static String[] fillteName(String formatString)
	{
		return formatString.split("&");
	}

	/**
	 * �������û����б��е������û���ת�����ַ���
	 * @param usernamelist
	 * @return username1 & username2 & username3
	 */
	public static String formatNames(JList<String>usernamelist)
	{
		return formatNames(usernamelist, false, null);
	}
	
	/**
	 * �������û����б��е������û���ת�����ַ���<br>
	 * @param usernamelist
	 * @param ridName ��ridNameΪtrue��ʱ��,���˵���������ridUserName
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
