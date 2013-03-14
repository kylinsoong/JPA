package com.kylin.jca.jboss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionReadWriteTest {
	
	final static String driver = "oracle.jdbc.OracleDriver";  
    final static String url = "jdbc:oracle:thin:@//10.66.129.154:1521/JBOSS";  
    final static String user = "GSSTEST";  
    final static String passwd = "redhat"; 
	
	private static Connection getConnection() {
		
		try {
			Class c = Class.forName(driver);  
			Driver d = (Driver) c.newInstance();  
			DriverManager.registerDriver(d);  
			System.out.println("Create a Db Connection use JDBC");
			return DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

	public static void main(String[] args) throws Exception {

		DBConnectionReadWriteTest test = new DBConnectionReadWriteTest();
		
//		test.readTest(getConnection());
		
		test.writeTest(getConnection());
	}

	private void writeTest(Connection conn) throws Exception {
		
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("input:");
			input = br.readLine();
			if(input.trim().equals("y")){
				PreparedStatement stmt = conn.prepareStatement("insert into hktdc values (?)");
				stmt.setInt(1, 100);
				stmt.execute();
				break;
			} else {
				System.out.println("sleep 1000");
				Thread.sleep(1000);
			}
		}
	}

	private void readTest(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM hktdc";
		
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("input:");
			input = br.readLine();
			if(input.trim().equals("y")){
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					System.out.println(rs.getInt(1));
				}
				break;
			} else {
				System.out.println("sleep 1000");
				Thread.sleep(1000);
			}
		}
	}

}
