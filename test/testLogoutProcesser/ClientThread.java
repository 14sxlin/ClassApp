package testLogoutProcesser;

import classapp.login.LoginDialog;

public class ClientThread extends Thread {

	@Override
	public void run() {
		new LoginDialog();
	}
}
