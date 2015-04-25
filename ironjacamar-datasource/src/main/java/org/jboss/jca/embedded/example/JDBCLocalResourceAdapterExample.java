package org.jboss.jca.embedded.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.resource.ResourceException;
import javax.sql.DataSource;

import org.jboss.jca.adapters.jdbc.JDBCResourceAdapter;
import org.jboss.jca.adapters.jdbc.local.LocalManagedConnectionFactory;
import org.jboss.jca.core.api.connectionmanager.pool.PoolConfiguration;
import org.jboss.jca.core.bootstrapcontext.BaseCloneableBootstrapContext;
import org.jboss.jca.core.connectionmanager.notx.NoTxConnectionManagerImpl;
import org.jboss.jca.core.connectionmanager.pool.strategy.OnePool;

import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionSynchronizationRegistryImple;

public class JDBCLocalResourceAdapterExample {
	
	private static String driverClass = "org.h2.Driver";
	private static String connURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static String user = "sa";
	private static String password = "sa";

	public static void main(String[] args) throws ResourceException, SQLException {

		LocalManagedConnectionFactory mcf = new LocalManagedConnectionFactory();
		
		JDBCResourceAdapter ra = new JDBCResourceAdapter();
		BaseCloneableBootstrapContext ctx = new BaseCloneableBootstrapContext();
		ctx.setTransactionSynchronizationRegistry(new TransactionSynchronizationRegistryImple());
		ra.start(ctx);
		mcf.setResourceAdapter(ra);
		
		NoTxConnectionManagerImpl cm = new NoTxConnectionManagerImpl();
		
		OnePool pool = new OnePool(mcf, new PoolConfiguration(), false, false);
		pool.setConnectionManager(cm);
		cm.setPool(pool);
		
		mcf.setDriverClass(driverClass);
		mcf.setConnectionURL(connURL);
		mcf.setUserName(user);
		mcf.setPassword(password);
		
		DataSource ds = (DataSource) mcf.createConnectionFactory(cm);
		
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT CURRENT_DATE()");
		while(rs.next()) {
			System.out.println(rs.getObject(1));
		}
		
	}

}
