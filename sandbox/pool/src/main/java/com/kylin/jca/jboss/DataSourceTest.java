package com.kylin.jca.jboss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceTest {

	public static void main(String[] args) throws NamingException, SQLException, IOException, InterruptedException {

		 Context ctx = new InitialContext();
		 DataSource ds = (DataSource) ctx.lookup("OracleDefaultDS");
		 Connection conn = ds.getConnection();
		 System.out.println("Get a Connection From JBoss DB connection pool");
		
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("input:");
			input = br.readLine();
			if(input.trim().equals("y")){
				PreparedStatement stmt = conn.prepareStatement("insert into hktdc values (?)");
				stmt.setInt(1, 10);
				stmt.execute();
				break;
			} else {
				System.out.println("sleep 1000");
				Thread.sleep(1000);
			}
		}

	}
}
