package com.kylin.man.service.client;

import javax.naming.NamingException;

import com.kylin.man.service.stateful.StatefulService;

public class StatefulServiceClient extends ClientBase {
	
	private StatefulService service;
	
	public StatefulServiceClient() throws NamingException {
		service = (StatefulService) getContext().lookup(STR_JNDI_STATEFUL);
	}

	public void test() throws Exception {
		service.ping();
	}

	public static void main(String[] args) throws Exception {
		
		new StatefulServiceClient().test();
	}

}
