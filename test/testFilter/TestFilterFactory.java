package testFilter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import headInfoFliter.factory.FilterFactory;
import headInfoFliter.fliter.HeadInfoFilter;
import headInfoFliter.fliter.LoginFilter;
import headInfoFliter.fliter.LogoutFilter;
import object_client_server.Client;

public class TestFilterFactory {

	ArrayList<Client> clientList;
	HeadInfoFilter out;
	HeadInfoFilter in;
	Client c1,c2,c3;

	@Before
	public void setUp() throws Exception {
		clientList=new ArrayList<>();	
		c1 = new Client(null);  c1.setUserName("小王");
		c2 = new Client(null);  c2.setUserName("小明");
		c3 = new Client(null);  c3.setUserName("小李");
		
		clientList.add(c1);
		clientList.add(c2);
		
		FilterFactory.setMemeberList(clientList);
		in = FilterFactory.createFilter("login") ;
		out =FilterFactory.createFilter("logout") ;
	}

	@Test
	public void testFactoryWorkWell() {
		
		assertEquals(true,out instanceof LogoutFilter);
		
		assertEquals(true, in instanceof LoginFilter);
		
	}
	
	@Test 
	public void testProcessWorkWell()
	{
		assertEquals( 3 , in.process(c3).size());
		assertEquals( 2 , out.process("小王").size());
		assertEquals( 2 , out.process("小张").size());
	}

}
