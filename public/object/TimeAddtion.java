package object;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAddtion {

	/**
	 * 用来发送时间的
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd : HH:mm:ss");
	public static String getTime()
	{
		return sdf.format(new Date())+"   :   ";
	}
}
