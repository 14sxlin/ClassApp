package headInfoFliter.fliter;

import api.notifier.Notifier;

/**
 * ͷ��Ϣ�������ĳ�����
 * @author ��˼��
 *
 */
public abstract class HeadInfoFilter {
	
	protected Notifier notifier;
	
	/**
	 * ִ����Ӧ�Ĵ�����
	 */
	abstract void process();
}
