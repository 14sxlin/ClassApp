package testProcesser;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import headInfoProcesser.HeadInfoProcesser;
import headInfoProcesser.LoginProcesser;
import headInfoProcesser.LogoutProcesser;
import headInfoProcesser.ProcesserFactory;
import manager.Client;

public class TestProcesserFactory {

	ArrayList<Client> clientList;
	HeadInfoProcesser out;
	HeadInfoProcesser in;
	Client c1,c2,c3;

	@Before
	public void setUp() throws Exception {
		clientList=new ArrayList<>();	
		c1 = new Client(null);  c1.setUserName("小王");
		c2 = new Client(null);  c2.setUserName("小明");
		c3 = new Client(null);  c3.setUserName("小李");
		
		clientList.add(c1);
		clientList.add(c2);
		
		in = new ProcesserFactory(clientList).createProcesser("login") ;
		out =new ProcesserFactory(clientList).createProcesser("logout") ;
	}

	@Test
	public void testFactoryWorkWell() {
		
		assertEquals(true,out instanceof LogoutProcesser);
		
		assertEquals(true, in instanceof LoginProcesser);
		
	}
	
	@Test 
	public void testProcessWorkWell() throws Exception
	{
		in.process(c3);
		assertEquals( 3 , clientList.size());
		out.process("小王");
		assertEquals( 2 , clientList.size());
		out.process("小张");
		assertEquals( 2 , clientList.size());
	}

}
