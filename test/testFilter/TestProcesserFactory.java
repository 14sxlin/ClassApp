package testFilter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import headInfoProcesser.factory.ProcesserFactory;
import headInfoProcesser.processer.HeadInfoProcesser;
import headInfoProcesser.processer.LoginProcesser;
import headInfoProcesser.processer.LogoutProcesser;
import object.Client;

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
		
		ProcesserFactory.setMemeberList(clientList);
		in = ProcesserFactory.createFilter("login") ;
		out =ProcesserFactory.createFilter("logout") ;
	}

	@Test
	public void testFactoryWorkWell() {
		
		assertEquals(true,out instanceof LogoutProcesser);
		
		assertEquals(true, in instanceof LoginProcesser);
		
	}
	
	@Test 
	public void testProcessWorkWell()
	{
		assertEquals( 3 , in.process(c3).size());
		assertEquals( 2 , out.process("小王").size());
		assertEquals( 2 , out.process("小张").size());
	}

}
