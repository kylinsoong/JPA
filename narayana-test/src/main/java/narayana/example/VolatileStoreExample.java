package narayana.example;

import java.io.File;

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


	public static void main(String[] args) throws Exception {

		setupStore();
		
		TransactionExample.main(new String[] {});
	}


	private static void setupStore() {
		
		arjPropertyManager.getObjectStoreEnvironmentBean().setObjectStoreType(VOLATILE_STORE);
		
		BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "default").setObjectStoreType(VOLATILE_STORE);
		BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "communicationStore").setObjectStoreType(VOLATILE_STORE);
		
		emptyObjectStore();
	}


	private static void emptyObjectStore() {
		
		String objectStoreDirName = BeanPopulator.getDefaultInstance(ObjectStoreEnvironmentBean.class).getObjectStoreDir();
		
		if(objectStoreDirName != null) {
			removeContents(new File(objectStoreDirName));
		}
	}


	private static void removeContents(File directory) {

		if ((directory != null) &&
				directory.isDirectory() &&
				(!directory.getName().equals("")) &&
				(!directory.getName().equals("/")) &&
				(!directory.getName().equals("\\")) &&
				(!directory.getName().equals(".")) &&
				(!directory.getName().equals("src/test"))) {
			
			File[] contents = directory.listFiles();
			
			for (File f : contents) {
				if (f.isDirectory()){
					removeContents(f);
					f.delete();
				}
			}
			
			if(directory != null) {
				directory.delete();
			}
		}
	}

}
