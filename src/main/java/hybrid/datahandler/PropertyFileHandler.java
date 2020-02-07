package hybrid.datahandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import hybrid.HybridConstants;

public class PropertyFileHandler {
	
	private String propFileName = null;
	
	public PropertyFileHandler() {
	}
	
	public PropertyFileHandler(String filename) {
		propFileName = filename;
	}
	
	public void serPropertyFileName(String filename) {
		this.propFileName = filename;
	}

	public String getProperty(String Key) {

		String result = null;

		try {

			Properties property = new Properties();
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(new FileInputStream(propFileName), HybridConstants.UTF8Format));
			property.load(inputStream);
			result = property.getProperty(Key);
			if (result == null) {
				throw new Exception(
						"The Key - " + Key + " is not found in the File -  " + propFileName + "\n\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured :" + e + e.getMessage());
		}

		return result;
	}
}
