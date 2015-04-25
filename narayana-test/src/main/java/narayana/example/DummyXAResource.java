package narayana.example;

import java.io.Serializable;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class DummyXAResource implements XAResource, Serializable {

	private static final long serialVersionUID = -161878582435603292L;
	
	public enum faultType {HALT, EX, NONE}
	
	private transient faultType fault = faultType.NONE;
	
	private static int commitRequests = 0;
	
	private Xid[] recoveryXids;
	
	public boolean startCalled;
	
	public boolean endCalled;
	
	public boolean prepareCalled;
	
	public boolean commitCalled;
	
	public boolean rollbackCalled;
	
	public boolean forgetCalled;
	
	public boolean recoverCalled;
	
	public DummyXAResource() {
		this(faultType.NONE);
	}
	
	public DummyXAResource(faultType fault) {
		this.fault = fault;
	}

	public void commit(Xid xid, boolean onePhase) throws XAException {
		System.out.println("DummyXAResource commit() called, fault: " + fault + " xid: " + xid);
		commitCalled = true;
		commitRequests += 1;
		
		if (fault != null) {
			if (fault.equals(faultType.EX)) {
				throw new XAException(XAException.XA_RBTRANSIENT);
			} else if (fault.equals(faultType.HALT)){
				recoveryXids = new Xid[1];
				recoveryXids[0] = xid;
				Runtime.getRuntime().halt(1);
			}
		}
	}

	public void end(Xid xid, int flags) throws XAException {
		endCalled = true;
	}

	public void forget(Xid xid) throws XAException {
		forgetCalled = true;
	}

	public int getTransactionTimeout() throws XAException {
		return 0;
	}

	public boolean isSameRM(XAResource xares) throws XAException {
		return this.equals(xares);
	}

	public int prepare(Xid xid) throws XAException {
		prepareCalled = true;
		return XAResource.XA_OK;
	}

	public Xid[] recover(int flag) throws XAException {
		recoverCalled = true;
		return recoveryXids;
	}

	public void rollback(Xid xid) throws XAException {
		rollbackCalled = true;
	}

	public boolean setTransactionTimeout(int seconds) throws XAException {
		return false;
	}

	public void start(Xid xid, int flags) throws XAException {
		startCalled = true;
	}
	
	public static int getCommitRequests() {
		return commitRequests;
	}

}
