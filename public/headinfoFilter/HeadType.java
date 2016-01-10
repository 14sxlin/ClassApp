package headinfoFilter;

public interface HeadType {
	/**
	 * 发送列表提醒的头信息
	 * 格式 #head:list?content=usename1&username2#
	 */
	String LIST = "#head:list?content=";
	/**
	 * 发送退出登录的头信息
	 * #head:logout?content=logoutusername#
	 */
	String LOGOUT = "#head:logout?content=";
	/**
	 * 发送登录的头信息
	 * #head:logout?content=loginusername#
	 */
	String LOGIN = "#head:login?content=";
	/**
	 * 发送私聊或者组聊的头信息,用户名与用户名之间用&分隔
	 * 最后用 ":message"来标识要发送的信息 
	 * 格式是 #head:group?content=sender!username&username2:mark#
	*/
	String GROUP = "#head:group?content=";
	
	/**
	 * 发送到组的信息,mark是组的标识,由客户端发过来
	 * message是要发送的信息
	 * 格式是#head:gsned?content=usernamelist!mark:message#
	 */
	String GSEND = "#head:gsend?content=";
	
	/**
	 * "我说"的字段
	 */
	String ISAY = "我 说:";
}
