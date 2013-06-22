package com.kylin.jca.jboss.mysql;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * 1. Edit $JBOSS_HOME/server/<CONFIG>/deploy/jca-jboss-beans.xmlm make sure the below property is true:
 *       <!-- Whether to track unclosed connections and close them -->
         <property name="debug">true</property>
   2. Primary purpose is to let JBoss close App level leaked connection, with 
         Closing a connection for you.  Please close them yourself: org.jboss.resource.adapter.jdbc.WrappedConnection
     threw
 * 
 * @author kylin
 *
 */
public class UnClosedConnectionTest {


	public static void main(String[] args) throws NamingException, SQLException {

		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("MySqlDS");
		
		for (int i = 0 ; i < 5 ; i ++) {
			ds.getConnection();
		}
	}

}
