package officeInfo;

import javax.swing.JFrame;

import gui.OfficeInfoListPanel;

public class OfficeInfoGUITest {

	public OfficeInfoGUITest() {
		JFrame frame = new JFrame("");
		frame.add(new OfficeInfoListPanel());
		frame.setLocation(200, 300);
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new OfficeInfoGUITest();

	}

}
