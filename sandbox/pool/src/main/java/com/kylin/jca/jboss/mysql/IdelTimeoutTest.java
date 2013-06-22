package com.kylin.jca.jboss.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class IdelTimeoutTest {

	public static void main(String[] args) throws NamingException, SQLException {

		for(int i = 0 ; i < 20 ; i ++) {
			new Thread(new Runnable(){

				public void run() {
					
					try {
						Context ctx = new InitialContext();
						DataSource ds = (DataSource) ctx.lookup("MySqlDS");
						
						for (int i = 0 ; i < 1000 ; i ++) {
							Connection conn = ds.getConnection();
							System.out.println(Thread.currentThread().getName() + " Create connection " + (i + 1) +" " + conn);
							conn.close();
						}
					} catch (Exception e) {
						throw new RuntimeException("", e);
					}
					
				}}).start();
		}
		
		
	}

}
