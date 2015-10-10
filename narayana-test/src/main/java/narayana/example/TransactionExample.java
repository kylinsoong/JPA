package narayana.example;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

public class TransactionExample {
	
	public static void main(String[] args) throws Exception {
				
		TransactionExample example = new TransactionExample();
		
//		example.commitUserTransaction();
		
//		example.commitTransactionManager();
		
//		example.rollbackUserTransaction();
		
		example.setRollbackOnly();
		
		example.transactionStatus();
		
		example.transactionTimeout();
	}
	
	public void commitUserTransaction() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		UserTransaction utx = com.arjuna.ats.jta.UserTransaction.userTransaction();
		
		utx.begin();
		
		System.out.println(utx + " " + utx.getClass());
		
		utx.commit();
	}
	
	public void commitTransactionManager() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
		
		tm.begin();
		
		System.out.println(tm + " " + tm.getClass());
		
		tm.commit();
	}
	
	public void rollbackUserTransaction() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		UserTransaction utx = com.arjuna.ats.jta.UserTransaction.userTransaction();
		
		utx.begin();
		
		System.out.println(utx + " " + utx.getClass());
		
		utx.rollback();
	}
	
	public void setRollbackOnly() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
		
		tm.begin();
		
		tm.setRollbackOnly();
		
		try {
			tm.commit();
		} catch (RollbackException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void transactionStatus() throws NotSupportedException, SystemException {
		
		UserTransaction utx = com.arjuna.ats.jta.UserTransaction.userTransaction();
		
		System.out.println(utx.getStatus() == Status.STATUS_NO_TRANSACTION);
		
		utx.begin();
		
		System.out.println(utx.getStatus() == Status.STATUS_ACTIVE);
		
		utx.setRollbackOnly();
		
		System.out.println(utx.getStatus() == Status.STATUS_MARKED_ROLLBACK);
		
		utx.rollback();
		
		System.out.println(utx.getStatus() == Status.STATUS_NO_TRANSACTION);
		
	}
	
	public void transactionTimeout() throws SystemException, NotSupportedException, InterruptedException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		UserTransaction utx = com.arjuna.ats.jta.UserTransaction.userTransaction();
		
		utx.setTransactionTimeout(1);
		
		utx.begin();
		
		Thread.sleep(1500);
		
		try {
			utx.commit();
		} catch (RollbackException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
