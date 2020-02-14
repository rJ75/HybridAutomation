package hybrid.datahandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import hybrid.HybridConstants;

public class PropertyFileHandler {

	private Properties property = new Properties();
	private String propFileName = null;

	public PropertyFileHandler(String filename) {
		setPropFileName(filename);
		serPropertyFileName(filename);
	}

	public String getPropFileName() {
		return propFileName;
	}

	public void setPropFileName(String propFileName) {
		this.propFileName = propFileName;
	}

	public void serPropertyFileName(String filename) {

		try {
			if (filename != null) {
				BufferedReader inputStream = new BufferedReader(
						new InputStreamReader(new FileInputStream(getPropFileName()), HybridConstants.UTF8_FORMAT));
				property.load(inputStream);
			} else {
				throw new Exception("The property file name value provided is null.\n\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured :" + e + e.getMessage());
		}
	}

	public String getProperty(String key) {

		String result = null;

		try {

			if (key != null) {
				result = property.getProperty(key);
				if (result == null) {
					throw new Exception(
							"The Key - " + key + " is not found in the File -  " + getPropFileName() + "\n\n");
				}
			} else {
				throw new Exception("The Key value provided is null.\n\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured :" + e + e.getMessage());
		}

		return result;
	}

	public String getProperty(String Key, String propFileName) {
		serPropertyFileName(propFileName);
		return getProperty(Key);
	}

	public String getProperty(String key, Properties props) {

		property = props;
		if (System.getProperty(key) != null) {
			return System.getProperty(key);
		} else if (System.getenv().containsKey(key)) {
			return System.getenv(key);
		} else if (props != null) {
			return props.getProperty(key);
		}
		return null;
	}
}
