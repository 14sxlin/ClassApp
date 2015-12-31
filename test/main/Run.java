package main;

import classapp.login.LoginDialog;
import main_frame.server.ServerMainFraim;

public class Run{

	public static void main(String[] args) throws InterruptedException {
		Thread serverThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				new ServerMainFraim().startService();
			}
		});
		Thread clientThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				new LoginDialog();
			}
		});
		serverThread.start();
		Thread.sleep(1000);
		clientThread.start();
	}

}
