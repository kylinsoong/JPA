package org.jboss.jca.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@Singleton
@Startup
public class MysqlConnectionSingleton {
	
	
	ArrayList<Connection> list = new ArrayList<Connection>();

	@PostConstruct
	protected void startup() {
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:jboss/datasources/mysqlDS1");
			
			if(null != ds){
				for (int i = 0 ; i < 10 ; i ++) {
					Connection conn = ds.getConnection();
					list.add(conn);
					System.out.println("Created a connection " + conn);
				}
			} else {
				throw new Exception("data source is null");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@PreDestroy
	protected void destroy() {
		for (Connection conn : list) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
