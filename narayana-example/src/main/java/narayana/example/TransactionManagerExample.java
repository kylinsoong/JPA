package narayana.example;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.arjuna.ats.arjuna.common.CoreEnvironmentBeanException;
import com.arjuna.ats.arjuna.common.arjPropertyManager;

public class TransactionManagerExample {
	
	private static final String VOLATILE_STORE = com.arjuna.ats.internal.arjuna.objectstore.VolatileStore.class.getName();

	public static void main(String[] args) throws CoreEnvironmentBeanException, NotSupportedException, SystemException, IllegalStateException, RollbackException, SecurityException, HeuristicMixedException, HeuristicRollbackException {
		
		// Set Properties for Arjuna
		arjPropertyManager.getCoreEnvironmentBean().setNodeIdentifier("1");
		arjPropertyManager.getCoreEnvironmentBean().setSocketProcessIdPort(0);
		arjPropertyManager.getCoreEnvironmentBean().setSocketProcessIdMaxPorts(10);
		
		arjPropertyManager.getCoordinatorEnvironmentBean().setEnableStatistics(false);
		arjPropertyManager.getCoordinatorEnvironmentBean().setDefaultTimeout(300);
		arjPropertyManager.getCoordinatorEnvironmentBean().setTransactionStatusManagerEnable(false);
		arjPropertyManager.getCoordinatorEnvironmentBean().setTxReaperTimeout(120000);
		
		arjPropertyManager.getObjectStoreEnvironmentBean().setObjectStoreType(VOLATILE_STORE);
		
		TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
		
		System.out.println(tm.getClass());
		tm.begin();
		System.out.println(tm.getTransaction().getClass());
		System.out.println(tm.getTransaction());
		tm.getTransaction().enlistResource(new XAResource(){

			public void commit(Xid xid, boolean onePhase) throws XAException {	
				System.out.println(xid);
			}

			public void end(Xid xid, int flags) throws XAException {				
			}

			public void forget(Xid xid) throws XAException {				
			}

			public int getTransactionTimeout() throws XAException {
				return 0;
			}

			public boolean isSameRM(XAResource xares) throws XAException {
				return false;
			}

			public int prepare(Xid xid) throws XAException {
				return 0;
			}

			public Xid[] recover(int flag) throws XAException {
				return null;
			}

			public void rollback(Xid xid) throws XAException {
				System.out.println(xid);
			}

			public boolean setTransactionTimeout(int seconds) throws XAException {
				return false;
			}

			public void start(Xid xid, int flags) throws XAException {
				
			}});
		tm.commit();
		System.out.println(tm.getTransaction());
	}

}
