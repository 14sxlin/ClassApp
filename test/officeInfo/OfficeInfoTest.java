package officeInfo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import tool.AutoOfficeTools;

public class OfficeInfoTest {

	public static void main(String[] args) {
		AutoOfficeTools.getInfoList(1);
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
