package object;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAddtion {

	/**
	 * ��������ʱ���
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd : HH:mm:ss");
	public static String getTime()
	{
		return sdf.format(new Date())+"   :   ";
	}
}
