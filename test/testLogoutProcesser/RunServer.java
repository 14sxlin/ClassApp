package testLogoutProcesser;

import main_frame.server.ServerMainFraim;

public class RunServer {

	public static void main(String[] args) {
		Thread serverThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				new ServerMainFraim().startService(true);
			}
		});
		
		serverThread.start();
	}

}
