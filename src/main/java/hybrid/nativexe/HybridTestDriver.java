package hybrid.nativexe;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hybrid.HybridConfigurations;
import hybrid.HybridException;

public class HybridTestDriver {
	
	public static void main(String[] args) {
		
		try {
			System.out.println("Execution Started !");
			HybridConfigurations hybridConfigurations = HybridConfigurations.getInstance();
			List<Map<String, String>> capability = hybridConfigurations.getCapability();
			ExecutorService executor = Executors.newFixedThreadPool(capability.size());
			Iterator<Map<String, String>> iterator = capability.iterator();
			while(iterator.hasNext()) {
				HybridTestRunner newTest = new HybridTestRunner(iterator.next());
				executor.execute(newTest);
			}
			executor.shutdown();
			while(!executor.isTerminated()) {}
		} catch (HybridException e) {
			e.handleException();
		}
	}
}
