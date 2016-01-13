package testLoginProcesser;

import mainframe.ServerMainFraim;
import pubchatmain.PubChatRoomMainFrame;

public class Run{


	public static void main(String[] args) throws InterruptedException {
		Thread serverThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				new ServerMainFraim().startService(true);
			}
		});
		
		serverThread.start();
		Thread.sleep(1000);
		
		for(int i=0; i< 4 ; i++)
		{
			Thread clientThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					{	
						PubChatRoomMainFrame temp = new PubChatRoomMainFrame(""+(int)(Math.random()*200));
						temp.gui.setVisible(true);
					}
				}
			});
			clientThread.start();
		}
	}

}
