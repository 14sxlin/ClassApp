package headinfoFilter;

public interface HeadType {
	/**
	 * �����б����ѵ�ͷ��Ϣ
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
	 * ����˽�Ļ������ĵ�ͷ��Ϣ,�û������û���֮����&�ָ�
	 * ����� ":message"����ʶҪ���͵���Ϣ 
	 * ��ʽ�� #head:group?content=sender!username&username2:mark#
	*/
	String GROUP = "#head:group?content=";
	
	/**
	 * ���͵������Ϣ,mark����ı�ʶ,�ɿͻ��˷�����
	 * message��Ҫ���͵���Ϣ
	 * ��ʽ��#head:gsned?content=usernamelist!mark:message#
	 */
	String GSEND = "#head:gsend?content=";
	
	/**
	 * "��˵"���ֶ�
	 */
	String ISAY = "�� ˵:";
}
