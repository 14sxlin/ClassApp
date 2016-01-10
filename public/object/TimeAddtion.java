package object;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAddtion {

	/**
	 * 用来发送时间的
	 */
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd : HH:mm:ss");
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm ");
	
	public static String getLongTime()
	{
		return sdf1.format(new Date())+"   :   ";
	}
	
	public static String getTime()
	{
		return sdf.format(new Date())+"   :   ";
	}
}
