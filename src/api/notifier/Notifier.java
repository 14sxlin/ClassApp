package api.notifier;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Notifier {
	
	/**
	 * �����б��е������û�
	 * @throws IOException �ύ�쳣,���������ߴ���
	 */
	public abstract void  notify(ArrayList<String> userlist) throws IOException;	

}
