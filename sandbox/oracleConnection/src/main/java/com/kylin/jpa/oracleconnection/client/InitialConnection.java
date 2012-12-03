package com.kylin.jpa.oracleconnection.client;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class InitialConnection {

	public static void main(String[] args) throws NamingException, SQLException {

		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("OracleDefaultDS");
		Connection conn = ds.getConnection();
		
		
		Class[] cls = conn.getClass().getInterfaces();
		
		for(Class c : cls){
			System.out.println(c);
		}
		
		System.out.println(conn.getClass());
		System.out.println(conn.getClass().getSuperclass());
		System.out.println(conn.getClass().getSuperclass().getSuperclass());
	}

}


















