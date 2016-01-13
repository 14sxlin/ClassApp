package officeInfo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

import javax.swing.JOptionPane;

import tools.AutoOfficeTools;

public class OfficeInfoTest {

	public static void main(String[] args) {
		try {
			AutoOfficeTools.getInfoList(1);
		} catch (TimeoutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "连接不到办公自动化");
		}
		System.out.println(AutoOfficeTools.toStr());
		try {
			Desktop.getDesktop().browse(
					new URI(
							AutoOfficeTools.getDetailWebsite(
									AutoOfficeTools.list.get(0))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
