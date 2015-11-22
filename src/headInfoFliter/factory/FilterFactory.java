package headInfoFliter.factory;

import java.util.ArrayList;

import headInfoFliter.fliter.HeadInfoFilter;

public abstract class FilterFactory {
	
	/**
	 * 用来调用下面提醒的方法
	 */
	protected ArrayList<String> memberList;
	
	public FilterFactory(ArrayList<String> memberList) {
		this.memberList=memberList;
	}
	/**
	 * 根据条件创建相应的过滤器
	 * @param filterType 传入的判断条件
	 * @return 返回相应的过滤器
	 */
	abstract HeadInfoFilter createFilter(String filterType);
}
