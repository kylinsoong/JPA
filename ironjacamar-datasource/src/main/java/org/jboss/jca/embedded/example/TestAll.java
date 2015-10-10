package org.jboss.jca.embedded.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.resource.ResourceException;
import javax.sql.DataSource;

import org.jboss.jca.datasource.DataSourceHelper;

public class TestAll {
    
    private static String driverClass = "org.h2.Driver";
    private static String connURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static String user = "sa";
    private static String password = "sa";
    
    static String xaDataSourceClass = "org.h2.jdbcx.JdbcDataSource";
    static String xaDataSourceProperties = "URL=jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    public static void main(String[] args) throws Exception {

//        testDataSource();
        
        
        DataSource ds = DataSourceHelper.newXADataSource(xaDataSourceClass, xaDataSourceProperties, user, password);
        System.out.println(ds);
        System.out.println(ds.getConnection());
    }

    static void testDataSource() throws ResourceException, SQLException {

        DataSource ds = DataSourceHelper.newDataSource(driverClass, connURL, user, password);
        
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT CURRENT_DATE()");
        while(rs.next()) {
            System.out.println(rs.getObject(1));
        }
        
        rs.close();
        stmt.close();
        conn.close();
    }

}
