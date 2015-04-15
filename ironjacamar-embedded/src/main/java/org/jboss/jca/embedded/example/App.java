package org.jboss.jca.embedded.example;


import java.net.URL;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.jca.embedded.Embedded;
import org.jboss.jca.embedded.EmbeddedFactory;


public class App {

	public static void main(String[] args) throws Throwable {

		Embedded embedded = EmbeddedFactory.create(false);
		
		embedded.startup();
		
		URL archieve = App.class.getClassLoader().getResource("h2-ds.xml");
		
		embedded.deploy(archieve);
		
		final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.LocalOnlyContextFactor");
        env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		Context ctx = new InitialContext(env);
		Object obj = ctx.lookup("java:/H2DS");
		
		System.out.println(obj);
	}

}
