package org.jboss.jca.embedded.example;

import java.sql.SQLException;

import javax.resource.ResourceException;


public class JDBCLocalResourceAdapterExample {
	
	private static String driverClass = "org.h2.Driver";
	private static String connURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static String user = "sa";
	private static String password = "sa";

	public static void main(String[] args) throws ResourceException, SQLException {

	
		
	}

}
