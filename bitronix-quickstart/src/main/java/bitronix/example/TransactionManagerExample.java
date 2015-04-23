package bitronix.example;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import bitronix.tm.Configuration;
import bitronix.tm.TransactionManagerServices;

public class TransactionManagerExample {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, InterruptedException {
		
		Configuration conf = TransactionManagerServices.getConfiguration();
		conf.setDefaultTransactionTimeout(1000 * 5);
		
		TransactionManager transactionManager = TransactionManagerServices.getTransactionManager();
		
		transactionManager.begin();
		
		Thread.currentThread().sleep(1000 * 10);
		
		transactionManager.commit();

	}

}
