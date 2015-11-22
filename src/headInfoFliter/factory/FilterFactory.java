package headInfoFliter.factory;

import java.util.ArrayList;

import headInfoFliter.fliter.HeadInfoFilter;

public abstract class FilterFactory {
	
	/**
	 * ���������������ѵķ���
	 */
	protected ArrayList<String> memberList;
	
	public FilterFactory(ArrayList<String> memberList) {
		this.memberList=memberList;
	}
	/**
	 * ��������������Ӧ�Ĺ�����
	 * @param filterType ������ж�����
	 * @return ������Ӧ�Ĺ�����
	 */
	abstract HeadInfoFilter createFilter(String filterType);
}
