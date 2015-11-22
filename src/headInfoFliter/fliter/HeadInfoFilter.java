package headInfoFliter.fliter;

import api.notifier.Notifier;

/**
 * 头信息过滤器的抽象类
 * @author 林思鑫
 *
 */
public abstract class HeadInfoFilter {
	
	protected Notifier notifier;
	
	/**
	 * 执行相应的处理动作
	 */
	abstract void process();
}
