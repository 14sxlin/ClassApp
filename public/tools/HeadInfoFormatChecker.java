package tools;

/**
 * 这个类是用来检查头信息的格式是不是正确的<br>
 * 正确的话就不会报错,错误的话会抛出错误
 * @author LinSixin sparrowxin@sina.cn
 *
 */
public class HeadInfoFormatChecker {

	/**
	 * 检查是不是包含了 : 号
	 * @param headinfo
	 * @throws Exception
	 */
	public static void check2(String headinfo) throws Exception
	{
		if(headinfo.contains(":")) {}
		else 
			throw new Exception("2段格式编码格式错误");
	}
	
	/**
	 * 检查是不是包含了 : 号和 ! 号
	 * @param headinfo
	 * @throws Exception
	 */
	public static void check3(String headinfo) throws Exception 
	{
		if( headinfo.contains("!") && headinfo.contains(":")) {}
		else 
			throw new Exception("3段格式编码格式错误");
	}
}
