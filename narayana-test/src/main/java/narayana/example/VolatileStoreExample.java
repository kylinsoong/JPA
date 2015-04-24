package narayana.example;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean;
import com.arjuna.ats.arjuna.common.arjPropertyManager;
import com.arjuna.common.internal.util.propertyservice.BeanPopulator;

/**
 * 
 * https://github.com/jbosstm/quickstart
 * 
 * @author kylin
 *
 */
public class VolatileStoreExample {
	
	private static final String VOLATILE_STORE = com.arjuna.ats.internal.arjuna.objectstore.VolatileStore.class.getName();


	public static void main(String[] args) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

		arjPropertyManager.getObjectStoreEnvironmentBean().setObjectStoreType(VOLATILE_STORE);
		
		BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "default").setObjectStoreType(VOLATILE_STORE);
		BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "communicationStore").setObjectStoreType(VOLATILE_STORE);
		

		UserTransaction utx = com.arjuna.ats.jta.UserTransaction.userTransaction();
		
		System.out.println(utx.getClass());
		
		utx.begin();
		utx.commit();
	}

}
