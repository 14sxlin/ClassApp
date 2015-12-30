package bean;


/**
 * 这个类封装了办公自动化的信息
 * @author 林思鑫
 *
 */
public class OfficeInfo {

	/**
	 * 网站内容的标题
	 */
	public String title;


	/**
	 * 相应内容的连接
	 */
	public String link;
	
	/**
	 * 发布日期
	 */
	public String date;
	
	/**
	 * 发布单位
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
