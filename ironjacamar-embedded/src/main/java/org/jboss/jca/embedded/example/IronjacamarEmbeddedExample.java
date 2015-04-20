package org.jboss.jca.embedded.example;

import java.io.File;
import java.net.URL;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jboss.jca.embedded.Embedded;
import org.jboss.jca.embedded.EmbeddedFactory;

public class IronjacamarEmbeddedExample {
	
	private static Embedded embedded;
		
	public static void main(String[] args) throws Throwable {
		
		embedded = EmbeddedFactory.create();
		
		embedded.startup();
		
		URL archive = new File("/home/kylin/work/ironjacamar/ironjacamar-1.2.4.Final/system/jdbc-local.rar").toURI().toURL();
		Context context = null;
		
		try {
			embedded.deploy(archive);
			URL ds = new File("h2-ds.xml").toURI().toURL();
			embedded.deploy(ds);
			
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/H2DS");
			System.out.println(dataSource.getConnection());
		} finally {
			embedded.undeploy(archive);
			if (context != null) {
				context.close();
			}
		}
				
		embedded.shutdown();
	}

}
