package com.kylin.ejb.remote.client;

import com.kylin.jpa.po.User;
import com.kylin.jpa.service.FacadeService;

public class FacadeServiceClient extends ClientBase {

	private FacadeService service;

	public FacadeServiceClient() throws Exception {

		service = (FacadeService) getContext().lookup(STR_JNDI);
	}

	/**
	 * This will test `add`, `modify`, `search`, `delete`
	 * 
	 * @throws Exception
	 */
	public void test() throws Exception {
		
		prompt(service.ping());
		
		service.addUser(new User());
		
		prompt(service.getUsers());
		
//		service.addUser(new User());
	}

	public static void main(String[] args) throws Exception {
		FacadeServiceClient client = new FacadeServiceClient();
		for (int i = 0; i < 100; i++)
			client.test();
		// System.out.println(STR_JNDI);
	}

}
