package com.kylin.jca.jboss;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class JDBCInitialConnection {

	final static String driver = "oracle.jdbc.OracleDriver";  
    final static String url = "jdbc:oracle:thin:@//10.66.192.144:1521/JBOSS";  
    final static String user = "GSSTEST";  
    final static String passwd = "redhat"; 
	
	private static Connection getConnection() {
		
		try {
			Class c = Class.forName(driver);  
			Driver d = (Driver) c.newInstance();  
			DriverManager.registerDriver(d);  
			return DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	public static void main(String[] args) throws InterruptedException {

		List<Connection> conns = new ArrayList<Connection>();
		
		for(int i = 0 ; i < 5000 ; i ++) {
			try {
				Connection conn = getConnection();
				conns.add(conn);
				System.out.println("Create Connection: " + (i + 1) + " [" + conn + "]");
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		Thread.sleep(Long.MAX_VALUE);
	}

}
