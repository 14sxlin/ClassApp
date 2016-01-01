package api.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LogoutEvent extends WindowAdapter{
	private AsClient client;
	
	public LogoutEvent(AsClient client) {
		this.client =  client;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		client.sendLogoutInfo(client.getServer());
	}


}
