package com.kylin.jca.jboss.mysql;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NoManagedConnectionsTest {

	public static void main(String[] args) throws NamingException, SQLException {

		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("MySqlDS");
		
		for (int i = 0 ; i < 11 ; i ++) {
			ds.getConnection();
		}
	}

}
