package org.jboss.jca.datasource;

import java.util.UUID;

import javax.resource.ResourceException;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkCompletedException;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAException;
import javax.transaction.xa.Xid;

import org.jboss.jca.adapters.jdbc.JDBCResourceAdapter;
import org.jboss.jca.adapters.jdbc.local.LocalManagedConnectionFactory;
import org.jboss.jca.adapters.jdbc.xa.XAManagedConnectionFactory;
import org.jboss.jca.core.api.connectionmanager.pool.PoolConfiguration;
import org.jboss.jca.core.bootstrapcontext.BaseCloneableBootstrapContext;
import org.jboss.jca.core.connectionmanager.notx.NoTxConnectionManagerImpl;
import org.jboss.jca.core.connectionmanager.pool.strategy.OnePool;
import org.jboss.jca.core.connectionmanager.tx.TxConnectionManagerImpl;
import org.jboss.jca.core.spi.transaction.TransactionIntegration;
import org.jboss.jca.core.tx.jbossts.TransactionIntegrationImpl;
import org.jboss.jca.core.tx.jbossts.TransactionManagerDelegator;
import org.jboss.jca.core.tx.jbossts.XAResourceRecoveryRegistryImpl;

import com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean;
import com.arjuna.ats.arjuna.common.arjPropertyManager;
import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionSynchronizationRegistryImple;
import com.arjuna.common.internal.util.propertyservice.BeanPopulator;

public class DataSourceHelper {
    
    public static TransactionManager getTransactionManager() throws Exception {
        
        arjPropertyManager.getCoreEnvironmentBean().setNodeIdentifier(UUID.randomUUID().toString()); //$NON-NLS-1$
        arjPropertyManager.getCoreEnvironmentBean().setSocketProcessIdPort(0);
        arjPropertyManager.getCoreEnvironmentBean().setSocketProcessIdMaxPorts(10);
        
        arjPropertyManager.getCoordinatorEnvironmentBean().setEnableStatistics(false);
        arjPropertyManager.getCoordinatorEnvironmentBean().setDefaultTimeout(300);
        arjPropertyManager.getCoordinatorEnvironmentBean().setTransactionStatusManagerEnable(false);
        arjPropertyManager.getCoordinatorEnvironmentBean().setTxReaperTimeout(120000);
        
//        String storeDir = getStoreDir();
        
        arjPropertyManager.getObjectStoreEnvironmentBean().setObjectStoreDir("target");
        BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "communicationStore").setObjectStoreDir("target"); //$NON-NLS-1$
        
        return com.arjuna.ats.jta.TransactionManager.transactionManager();
    }
    
    public static DataSource newDataSource(String driverClass, String connURL, String user, String password) throws ResourceException{
        
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
        
        return (DataSource) mcf.createConnectionFactory(cm);
    }
    
    public static DataSource newXADataSource(String xaDataSourceClass, String xaDataSourceProperties, String user, String password) throws Exception{
        
        XAManagedConnectionFactory mcf = new XAManagedConnectionFactory();
        
        JDBCResourceAdapter ra = new JDBCResourceAdapter();
        BaseCloneableBootstrapContext ctx = new BaseCloneableBootstrapContext();
        ctx.setTransactionSynchronizationRegistry(new TransactionSynchronizationRegistryImple());
        ra.start(ctx);
        mcf.setResourceAdapter(ra);
        
        TransactionManager tm = new TransactionManagerDelegator(getTransactionManager());
        org.jboss.tm.usertx.UserTransactionRegistry utr = new org.jboss.tm.usertx.UserTransactionRegistry();
        org.jboss.tm.JBossXATerminator terminator = null;
        org.jboss.tm.XAResourceRecoveryRegistry rr = null;
        TransactionIntegration txIntegration = new TransactionIntegrationImpl(tm, new TransactionSynchronizationRegistryImple(), utr, terminator, rr);
        
        TxConnectionManagerImpl cm = new TxConnectionManagerImpl(txIntegration, true);
        
        OnePool pool = new OnePool(mcf, new PoolConfiguration(), false, false);
        pool.setConnectionManager(cm);
        cm.setPool(pool);
        
        mcf.setXADataSourceClass(xaDataSourceClass);
        mcf.setXADataSourceProperties(xaDataSourceProperties);
        mcf.setUserName(user);
        mcf.setPassword(password);
        
        return (DataSource) mcf.createConnectionFactory(cm);
    }

}
