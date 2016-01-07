package headinfoFilter;

public interface HeadType {
	/**
	 * 发送列表提醒的头信息
	 */
	String LIST = "#head:list?content=";
	/**
	 * 发送退出登录的头信息
	 */
	String LOGOUT = "#head:logout?content=";
	/**
	 * 发送登录的头信息
	 */
	String LOGIN = "#head:login?content=";
	/**
	 * 发送私聊或者组聊的头信息,用户名与用户名之间用&分隔
	 * 最后用 ":message"来标识要发送的信息 
	*/
	String GROUP = "#head:group?content=";
}
