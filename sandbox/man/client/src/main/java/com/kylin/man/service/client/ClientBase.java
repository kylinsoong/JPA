package com.kylin.man.service.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.kylin.man.service.FacadeService;
import com.kylin.man.service.TestService;
import com.kylin.man.service.stateful.StatefulService;

public abstract class ClientBase {
	
	protected static final String STR_JNDI_FACADE = "ejb:man-ear/man-service-1.0/FacadeSession!" + FacadeService.class.getName() ;
	protected static final String STR_JNDI_TEST = "ejb:man-ear/man-service-1.0/TestSession!" + TestService.class.getName() ;
	protected static final String STR_JNDI_STATEFUL = "ejb:man-ear/man-service-1.0/StatefulSession!" + StatefulService.class.getName() + "?stateful";
	
	protected Context getContext() throws NamingException {
		
		Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
		Context context = new InitialContext(jndiProperties);
		
		return context;
	}

	protected void prompt(Object msg) {
		System.out.println("\n  " + msg);
	}

	protected void stop(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public abstract void test() throws Exception;

}