package bitronix.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;

public class HelloWorld {

	public static void main(String[] args) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
		
		PoolingDataSource pds = new PoolingDataSource();
        pds.setUniqueName("jdbc/jta-ds");
        pds.setClassName("bitronix.tm.resource.jdbc.lrc.LrcXADataSource");
        pds.setMaxPoolSize(5);
        pds.setAllowLocalTransactions(true);
        pds.getDriverProperties().put("user", "sa");
        pds.getDriverProperties().put("password", "");
        pds.getDriverProperties().put("url", "jdbc:h2:tcp://localhost/~/jta-db");
        pds.getDriverProperties().put("driverClassName", "org.h2.Driver");
        pds.init();
        
        BitronixTransactionManager btm = TransactionManagerServices.getTransactionManager();
        
        btm.begin();
        try {
            Connection c = pds.getConnection();
 
            PreparedStatement stmt = c.prepareStatement("insert into messages(content) values (?)");
            stmt.setString(1, "hello, world!");
            stmt.executeUpdate();
            stmt.close();
 
            c.close();
            btm.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            btm.rollback();
        }
        
        btm.begin();
        try {
            Connection c = pds.getConnection();
 
            PreparedStatement stmt = c.prepareStatement("select content from messages");
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                System.out.println(rs.getString(1));
            rs.close();
            stmt.close();
 
            c.close();
            btm.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            btm.rollback();
        }
 
        pds.close();
         
        btm.shutdown();
	}

}
