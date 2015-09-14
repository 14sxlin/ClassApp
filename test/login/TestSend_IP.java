package login;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class TestSend_IP {
/*
 * 发送本机的IP地址
 */
	private InetAddress ip;
	public String getLocal_IP() throws UnknownHostException
	{
		ip=InetAddress.getLocalHost();
		return ip.getHostAddress();
		
	}
	@Test
	public void testSendLocal_IP() throws UnknownHostException {
		System.out.println(getLocal_IP());
		assertNotNull(getLocal_IP());
		assertEquals(getLocal_IP(), "10.21.30.30");
	}

}
