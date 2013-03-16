package com.kylin.man.service.client;

import com.kylin.man.po.util.POUtil;
import com.kylin.man.service.FacadeService;

public class FacadeServiceClient extends ClientBase {

	private FacadeService service;

	public FacadeServiceClient() throws Exception {

		service = (FacadeService) getContext().lookup(STR_JNDI_FACADE);
	}

	/**
	 * This will test `add`, `modify`, `search`, `delete`
	 * 
	 * @throws Exception
	 */
	public void test() throws Exception {
		
		prompt(service.ping());
		
		service.addMan(POUtil.newMan());
		
		prompt(service.getMans().size());
		
//		service.addUser(new User());
	}

	public static void main(String[] args) throws Exception {
		FacadeServiceClient client = new FacadeServiceClient();
		for (int i = 0; i < 10; i++)
			client.test();
	}

}
