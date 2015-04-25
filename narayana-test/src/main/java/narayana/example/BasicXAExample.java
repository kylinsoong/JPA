package narayana.example;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

public class BasicXAExample extends RecoverySetup{
		
	public static void main(String[] args) throws IllegalStateException, SecurityException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		startRecovery();
		new BasicXAExample().resourceEnlistment();
		stopRecovery();
	}
	
	public void resourceEnlistment() throws NotSupportedException, SystemException, IllegalStateException, RollbackException, SecurityException, HeuristicMixedException, HeuristicRollbackException {
		
		TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
		DummyXAResource xares1 = new DummyXAResource(DummyXAResource.faultType.NONE);
		DummyXAResource xares2 = new DummyXAResource(DummyXAResource.faultType.NONE);
		
		tm.begin();
		
		tm.getTransaction().enlistResource(xares1);
		tm.getTransaction().enlistResource(xares2);
		
		if (!xares1.startCalled)
			throw new RuntimeException("start should have called");
		
		tm.commit();
		
		if (!xares1.endCalled)
			throw new RuntimeException("end should have called");
		
		if (!xares1.prepareCalled)
			throw new RuntimeException("prepare should have called");
		
		if (!xares1.commitCalled)
			throw new RuntimeException("commit should have called");
	}

}
