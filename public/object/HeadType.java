package object;

/**
 * 
 * @author LinSixin sparrowxin@sina.cn
 * �������Ϳͻ��˵�ͷ��Ϣ�Ĺ涨
 */
public interface HeadType {
	/**
	 * �����б����ѵ�ͷ��Ϣ<br>
	 * ��ʽ #head:list?content=usename1&username2#
	 */
	String LIST = "#head:list?content=";
	/**
	 * �����˳���¼��ͷ��Ϣ
	 * #head:logout?content=logoutusername#
	 */
	String LOGOUT = "#head:logout?content=";
	/**
	 * ���͵�¼��ͷ��Ϣ
	 * #head:logout?content=loginusername#
	 */
	String LOGIN = "#head:login?content=";
	/**
	 * ����˽�Ļ������ĵ�ͷ��Ϣ<br>
	 * Sender�Ƿ�����<br>
	 * �û������û���֮����&�ָ�,��Ҫ�齨��Ⱥ�Ĵ��ڵ��û���<br>
	 * ����� ":message"����ʶҪ���͵���Ϣ <br>
	 * ��ʽ�� #head:group?content=sender!username1&username2:mark#
	*/
	String GROUP = "#group:makegroup?content=";
	
	/**
	 * ���͵������Ϣ<br>
	 * usernamlist��ָ�����������������<br>
	 * mark����ı�ʶ,�ɿͻ��˷�����<br>
	 * message��Ҫ���͵���Ϣ<br>
	 * ��ʽ��#head:gsned?content=usernamelist!mark:message#
	 */
	String GSEND = "#group:gsend?content=";
	
	/**
	 * ���ļ���ʱ���ͷ��Ϣ<br>
	 * origin_usernames�Ƿ����������ԭ���ĳ�Ա<br>
	 * mark�����ĵı�ʶ<br>
	 * contentָ����Ա�б��ַ���<br>
	 * #group:in?content=origin_usernames!mark:namelist#
	 */
	String GIN = "#group:in?content=";
	
	/**
	 * �����˳�ʱ���ͷ��Ϣ<br>
	 * namelist���˳�����,һ��ֻ���и���<br>
	 * mark����ı�ʶ<br>
	 * #group:out?content=mark:outer#
	 */
	String GOUT = "#group:out?content=";
}
