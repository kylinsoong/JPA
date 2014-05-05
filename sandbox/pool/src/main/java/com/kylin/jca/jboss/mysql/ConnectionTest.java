package com.kylin.jca.jboss.mysql;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionTest {


	public static void main(String[] args) {

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("MySqlDS");
			
			for (int i = 0 ; i < 10; i ++) {	
				Connection conn = ds.getConnection();
				System.out.println(Thread.currentThread().getName() + " Create connection " + (i + 1) +" " + conn);
				conn.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

}
