package com.kylin.ejb.remote.client;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.kylin.jpa.service.FacadeService;

public abstract class ClientBase {
	
	protected static final String STR_JNDI = "ejb:jpa-hibernate-ear/jpa-hibernate-service-1.0/FacadeSession!" + FacadeService.class.getName() ;

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