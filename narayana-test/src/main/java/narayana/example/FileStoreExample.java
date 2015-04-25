package narayana.example;

import com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean;
import com.arjuna.ats.arjuna.common.arjPropertyManager;
import com.arjuna.common.internal.util.propertyservice.BeanPopulator;

public class FileStoreExample {
	
	private static final String STORE_DIR = "target/TxStoreDir";

	public static void main(String[] args) throws Exception {

		setupStore();
		
		TransactionExample.main(new String[] {});
	}

	private static void setupStore() {

		arjPropertyManager.getObjectStoreEnvironmentBean().setObjectStoreDir(STORE_DIR);
		BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "communicationStore").setObjectStoreDir(STORE_DIR);
	}

}
