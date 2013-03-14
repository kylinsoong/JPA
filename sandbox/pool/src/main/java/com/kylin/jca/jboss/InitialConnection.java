package com.kylin.jca.jboss;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class InitialConnection {

	static final int C_COUNT = 16;
	
	public static void main(String[] args) throws NamingException, SQLException, InterruptedException {

		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("BONUSDS");
		
		
		List<Connection> list = new ArrayList<Connection>();
		
		for(int i = 0 ; i < C_COUNT ; i ++) {
			Connection conn = ds.getConnection();
			System.out.println("Create Connection: " + (i + 1) + " [" + conn + "]");
			list.add(conn);
			Thread.sleep(50);
		}
		
		Thread.sleep(20 * 1000);
		
		System.out.println("Create a new Connection:");
		
		Connection conn = ds.getConnection();
		
//		for(Connection conn : list) {
//			conn.close();
//		}
	}

}
