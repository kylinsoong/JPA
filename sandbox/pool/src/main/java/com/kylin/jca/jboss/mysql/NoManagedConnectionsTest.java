package com.kylin.jca.jboss.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NoManagedConnectionsTest {

	public static void main(String[] args) throws NamingException, SQLException, InterruptedException {

		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("MySqlDS2");
		Connection conn = ds.getConnection();
		System.out.println("Get connection from pool " + conn);
		Thread.sleep(100 * 1000);
		conn.close();
	}

}
