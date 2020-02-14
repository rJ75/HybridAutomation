package hybrid.nativexe;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hybrid.HybridConfigurations;
import hybrid.HybridException;
import hybrid.datahandler.DBHandler;

public class TestDriver {
	
	public static void main(String[] args) {
		
		try {
			HybridConfigurations hybridConfigurations = HybridConfigurations.getInstance();
			int threadCount = hybridConfigurations.getTotalThreadCount();
			DBHandler.connect();
			ExecutorService executor = Executors.newFixedThreadPool(threadCount);
			for(int i=0; i< threadCount; i++) {
				TestRunner newTest = new TestRunner();
				executor.execute(newTest);
			}
			executor.shutdown();
			while(!executor.isTerminated()) {}
		} catch (HybridException e) {
			e.handleException();
		}

	}
	

}
