package object;

/**
 * 
 * @author LinSixin sparrowxin@sina.cn
 * 服务器和客户端的头信息的规定
 */
public interface HeadType {
	/**
	 * 发送列表提醒的头信息<br>
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
	 * 发送私聊或者组聊的头信息<br>
	 * Sender是发送者<br>
	 * 用户名与用户名之间用&分隔,是要组建的群聊窗口的用户名<br>
	 * 最后用 ":message"来标识要发送的信息 <br>
	 * 格式是 #head:group?content=sender!username1&username2:mark#
	*/
	String GROUP = "#group:makegroup?content=";
	
	/**
	 * 发送到组的信息<br>
	 * usernamlist是指包含在组聊里面的人<br>
	 * mark是组的标识,由客户端发过来<br>
	 * message是要发送的信息<br>
	 * 格式是#head:gsned?content=usernamelist!mark:message#
	 */
	String GSEND = "#group:gsend?content=";
	
	/**
	 * 组聊加入时候的头信息<br>
	 * origin_usernames是发出邀请的组原来的成员<br>
	 * mark是组聊的标识<br>
	 * content指定成员列表字符串<br>
	 * #group:in?content=origin_usernames!mark:namelist#
	 */
	String GIN = "#group:in?content=";
	
	/**
	 * 组聊退出时候的头信息<br>
	 * namelist是退出的人,一般只有有个人<br>
	 * mark是组的标识<br>
	 * #group:out?content=mark:outer#
	 */
	String GOUT = "#group:out?content=";
}
