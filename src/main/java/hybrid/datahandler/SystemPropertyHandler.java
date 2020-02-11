package hybrid.datahandler;

import hybrid.HybridException;

public class SystemPropertyHandler {
	
	public String getProperty(String property) {
		
		try {
			if (System.getProperty(property) != null) {
				return System.getProperty(property);
			} else if (System.getenv().containsKey(property)) {
				return System.getenv(property);
			} else {
				throw new HybridException("System property is not found.");
			}
		} catch(HybridException e) {
			e.handleException();
		}
		
		return null;
	}
	
	

}
