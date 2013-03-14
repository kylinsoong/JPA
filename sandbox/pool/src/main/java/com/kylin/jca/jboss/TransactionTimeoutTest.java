package com.kylin.jca.jboss;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TransactionTimeoutTest {
	
	private static final String TABLE_CREATE = "create table tool_dbconn_kylin_test(id int)";
	private static final String TABLE_INSERT = "insert into tool_dbconn_kylin_test values(100)";
	private static final String TABLE_SELECT = "select * from tool_dbconn_kylin_test";
	private static final String TABLE_DROP = "drop table tool_dbconn_kylin_test";

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("OracleDS");
		Connection conn = ds.getConnection();
		
		try {
			droptable(conn);
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(TABLE_CREATE);
			
			
			conn.setAutoCommit(false);
			System.out.println("Start transaction");
			
			stmt.execute(TABLE_INSERT);
			stmt.execute(TABLE_SELECT);
			
			Thread.currentThread().sleep(Long.MAX_VALUE);
			conn.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	private static void droptable(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(TABLE_DROP);
		} catch (SQLException e) {
			
		}
	}
	


}
