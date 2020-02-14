package hybrid.nativexe;

import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import hybrid.base.HybridWebBase;

public class HybridTestRunner implements Runnable {
	
	Map<String, String> capability;
	DesiredCapabilities dc = new DesiredCapabilities();
	HybridWebBase driver;
	
	public HybridTestRunner(Map<String, String> capability) {
		this.capability = capability;
	}

	@Override
	public void run() {

		for (Map.Entry<String, String> entry : capability.entrySet()) {
			dc.setCapability(entry.getKey(), entry.getValue());
		}
		
		
	}
	
	public void setDriver() {
		
	}
	
	

}
