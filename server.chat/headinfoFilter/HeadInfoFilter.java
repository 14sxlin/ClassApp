package headinfoFilter;

/**
 * ͷ��Ϣ������,���˳�ͷ��Ϣ�����ͺ�ֵ
 * @author LinSixin sparrowxin@sina.cn
 *
 */
public class HeadInfoFilter {
	private final String head = "#head:"; 
	private final String content= "content=";
	private String readline;
	
	public HeadInfoFilter(String readline) {
		this.readline = readline;
	}
	
	/**
	 * �����ж��ǲ���ͷ��Ϣ
	 * @return ���������#head:�ֶξ���Ϊ��ͷ��Ϣ����true
	 * ���򷵻�false
	 */
	public boolean isHeadInfo()
	{
		if(readline.contains("#head:"))
			return true;
		else return false;
	}
	
	/**
	 * ���ܵ�ֵ��
	 * list : �б�ͷ��Ϣ
	 * login : ��¼ͷ��Ϣ
	 * logout : �ǳ�ͷ��Ϣ
	 * @return ���˳���ʶ
	 */
	public String filteType()
	{
		int start= -1;
		int end = -1;
		start = readline.indexOf("#head:")+head.length();
		end = readline.indexOf("?",start);
		if(start != -1 && end != -1)
			return readline.substring(start,end);
		else return "�Ҳ�����ʶ";
	}
	
	/**
	 * @return ���˳���Ӧ��ʶ������
	 */
	public String filteContent()
	{
		int begin = readline.indexOf(content);
		if(begin != -1)
			begin+=content.length();
		else return "�Ҳ�������";
		int end = readline.lastIndexOf("#");
		if(end != -1)
		{	
			return readline.substring(begin, end);
		}
		else return "�Ҳ�������";
	}
}
