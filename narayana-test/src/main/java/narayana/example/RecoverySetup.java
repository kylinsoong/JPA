package narayana.example;

import com.arjuna.ats.arjuna.common.RecoveryEnvironmentBean;
import com.arjuna.ats.arjuna.common.arjPropertyManager;
import com.arjuna.ats.arjuna.recovery.RecoveryManager;
import com.arjuna.common.internal.util.propertyservice.BeanPopulator;

public class RecoverySetup {
	
	public static final String dataDir = "target/data";
	public static final String recoveryStoreDir = dataDir + "/recoveryTestStore";
	
	protected static RecoveryManager recoveryManager;
	
	public static void startRecovery() {
		arjPropertyManager.getObjectStoreEnvironmentBean().setObjectStoreDir(dataDir);
		RecoveryManager.delayRecoveryManagerThread() ;
		BeanPopulator.getDefaultInstance(RecoveryEnvironmentBean.class).setRecoveryBackoffPeriod(1);
		recoveryManager = RecoveryManager.manager();
	}
	
	public static void stopRecovery() {
		recoveryManager.terminate();
	}
	
	protected void runRecoveryScan() {
		recoveryManager.scan();
	}

}
