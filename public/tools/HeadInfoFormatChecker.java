package tools;

/**
 * ��������������ͷ��Ϣ�ĸ�ʽ�ǲ�����ȷ��<br>
 * ��ȷ�Ļ��Ͳ��ᱨ��,����Ļ����׳�����
 * @author LinSixin sparrowxin@sina.cn
 *
 */
public class HeadInfoFormatChecker {

	/**
	 * ����ǲ��ǰ����� : ��
	 * @param headinfo
	 * @throws Exception
	 */
	public static void check2(String headinfo) throws Exception
	{
		if(headinfo.contains(":")) {}
		else 
			throw new Exception("2�θ�ʽ�����ʽ����");
	}
	
	/**
	 * ����ǲ��ǰ����� : �ź� ! ��
	 * @param headinfo
	 * @throws Exception
	 */
	public static void check3(String headinfo) throws Exception 
	{
		if( headinfo.contains("!") && headinfo.contains(":")) {}
		else 
			throw new Exception("3�θ�ʽ�����ʽ����");
	}
}
