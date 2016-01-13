package testLogoutProcesser;

import login.ClassAppLoginFrame;

public class ClientThread extends Thread {

	@Override
	public void run() {
		new ClassAppLoginFrame();
	}
}
