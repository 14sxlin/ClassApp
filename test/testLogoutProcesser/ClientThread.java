package testLogoutProcesser;

import classapp.login.ClassAppLoginFrame;

public class ClientThread extends Thread {

	@Override
	public void run() {
		new ClassAppLoginFrame();
	}
}
