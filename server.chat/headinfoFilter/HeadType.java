package headinfoFilter;

public interface HeadType {
	/**
	 * �����б����ѵ�ͷ��Ϣ
	 */
	String LIST = "#head:list?content=";
	/**
	 * �����˳���¼��ͷ��Ϣ
	 */
	String LOGOUT = "#head:logout?content=";
	/**
	 * ���͵�¼��ͷ��Ϣ
	 */
	String LOGIN = "#head:login?content=";
	/**
	 * ����˽�Ļ������ĵ�ͷ��Ϣ,�û������û���֮����&�ָ�
	 * ����� ":message"����ʶҪ���͵���Ϣ 
	*/
	String GROUP = "#head:group?content=";
}
