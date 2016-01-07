package headInfoProcesser.processer;

import object.Client;

/**
 * ͷ��Ϣ�������ĳ�����
 * @author ��˼��
 *
 */
public abstract class HeadInfoProcesser {
		
	/**
	 * Ӧ�õ��ò�����String�ķ���
	 * ��һ���������û��׳��쳣
	 */
	public static  final int STRING = 1;
	/**
	 * Ӧ�õ��ò�����Client�ķ���
	 * ��һ���������û��׳��쳣
	 */
	public static  final int CLIENT = 2;
	
	/**
	 * ����������process����
	 * typeָ���˵������ַ���
	 */
	protected int type;
	/**
	 * ���������б�����Ӷ���
	 * @param client ����һ��Client����
	 * @throws Exception 
	 */
	public abstract void process(Client client) throws Exception;
	
	/**
	 * ������ɾ���б��еĶ���
	 * @param userName �����û���
	 * @throws Exception 
	 */
	public abstract void process(String userName) throws Exception;
	
	
	public int getType()
	{
		return type;
	}
}
