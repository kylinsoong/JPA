package org.jboss.jca.mysql;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MysqlConnection {

	public static void main(String[] args) throws NamingException {

		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "password1!");

		Context ctx = new InitialContext(env);
		
		DataSource ds = (DataSource) ctx.lookup("java:jboss/datasources/mysqlDS1");
		
		System.out.println(ds);
	}

}
