package com.kylin.man.service.client;

import javax.naming.NamingException;

import com.kylin.man.service.TestService;

public class TestServiceClient extends ClientBase {
	
	private TestService service ;
	
	public TestServiceClient() throws NamingException {
		service = (TestService) getContext().lookup(STR_JNDI_TEST);
	}

	public void test() throws Exception {
		service.jaxbTest();
	}
	
	public static void main(String[] args) throws Exception {
		TestServiceClient client = new TestServiceClient();
		client.test();
	}

}
