package bean;


/**
 * ������װ�˰칫�Զ�������Ϣ
 * @author ��˼��
 *
 */
public class OfficeInfo {

	/**
	 * ��վ���ݵı���
	 */
	public String title;


	/**
	 * ��Ӧ���ݵ�����
	 */
	public String link;
	
	/**
	 * ��������
	 */
	public String date;
	
	/**
	 * ������λ
	 */
	public String department;
	
	public OfficeInfo(String title,String department,String date,String link) {
		this.title = title;
		this.department = department;
		this.date = date;
		this.link = link;
	}
	
	@Override
	public String toString() {
		return this.title;
	}
	
	
}
