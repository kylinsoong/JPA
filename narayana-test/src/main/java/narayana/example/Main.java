package narayana.example;

/**
 *  https://github.com/jbosstm/quickstart
 * 
 * @author kylin
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {

		TransactionManagerExample.main(args);
		
		TransactionExample.main(args);
		
		VolatileStoreExample.main(args);
		
		FileStoreExample.main(args);
		
		System.out.println("\n\t Success Exit");
		
	}

}
