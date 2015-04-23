package bitronix.example;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class H2Helper {
	
	final static String driver = "org.h2.Driver";  
    final static String url = "jdbc:h2:tcp://localhost/~/jta-db";  
    final static String user = "sa";  
    final static String passwd = "";  
    final static String PREPARE_SQL = "create table messages ( content varchar(50))";
    final static String TEST_SQL = "select * from messages";
    
    public static void testBTMDemo() {
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(TEST_SQL);
			while(rs.next()){
				System.out.println("  EXRACT FROM DB: " + rs.getString(1));
			}
			System.out.println("TEST SUCCESS");
		} catch (Throwable t) {
			throw new RuntimeException("Could not prepare BTM Demo", t);
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null;
				}
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				throw new RuntimeException("Could not close database resource", e);
			}
		}
    }
    
    public static void prepareBTMDemo() {
    	Connection conn = null;
    	Statement stmt = null;
    	try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.execute(PREPARE_SQL);	
			System.out.println("PREPARE COMPLETE");
		} catch (Throwable t) {
			throw new RuntimeException("Could not prepare BTM Demo", t);
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				throw new RuntimeException("Could not close database resource", e);
			}
		}
    }
    
    public static Connection getConnection() {
    	try {
    		Class c = Class.forName(driver);  
            Driver d = (Driver) c.newInstance();  
            DriverManager.registerDriver(d);  
            return DriverManager.getConnection(url, user, passwd); 
		} catch (Throwable t) {
			throw new RuntimeException("Could not create connection", t);
		}
    }

	public static Server startH2Server(){
		try {
			// start h2 in memory database
			Server server = Server.createTcpServer(new String[0]);
	        server.start();
	        return server;
		} catch (Throwable t) {
			throw new RuntimeException("Could not start H2 server", t);
		}
	}
	
	public static void stopH2Server(Server server) {
		try {
			// start h2 in memory database
	        server.stop();
		} catch (Throwable t) {
			throw new RuntimeException("Could not stop H2 server", t);
		}
	}
}
