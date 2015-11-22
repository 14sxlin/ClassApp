package api.notifier;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Notifier {
	
	/**
	 * 提醒列表中的所有用户
	 * @throws IOException 提交异常,交给调用者处理
	 */
	public abstract void  notify(ArrayList<String> userlist) throws IOException;	

}
