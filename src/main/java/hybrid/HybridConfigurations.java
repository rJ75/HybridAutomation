package hybrid;

import hybrid.HybridConstants.ExecutionPlatform;
import hybrid.HybridConstants.ExecutionPlatformHost;
import hybrid.HybridConstants.ExecutionTool;
import hybrid.HybridConstants.FLAGS;
import hybrid.HybridConstants.MobileExecutionPlatform;
import hybrid.HybridConstants.WebExecutionPlatform;
import hybrid.datahandler.PropertyFileHandler;

public class HybridConfigurations  {

	public static HybridConfigurations hybridconfig;
	
	private PropertyFileHandler hybridPropertyReader;
	
	private HybridConfigurations() throws HybridException {
		hybridPropertyReader = new PropertyFileHandler(HybridConstants.HYBRID_CONFIG_PROPERTY_FILE);
		setConfigSettings();
	}
	
	public static synchronized HybridConfigurations getInstance() throws HybridException {
		
		if(hybridconfig == null) {
			hybridconfig = new HybridConfigurations();
		}
		return hybridconfig;
	}

	private void setConfigSettings() throws HybridException {
		
		setExecutionPlatforms();
		setMobileAppConfiguration();
		setMobileWebConfiguration();
		setWebConfiguration();
	}

	private void setExecutionPlatforms() {

		ExecutionPlatform.MOBILE.setExecutionPlatformValue(
				hybridPropertyReader.getProperty(ExecutionPlatform.MOBILE.getExecutionPlatform())
						.equals(FLAGS.ON.getFlag()) ? true : false);
		ExecutionPlatform.MOBILE_WEB.setExecutionPlatformValue(
				hybridPropertyReader.getProperty(ExecutionPlatform.MOBILE_WEB.getExecutionPlatform())
						.equals(FLAGS.ON.getFlag()) ? true : false);
		ExecutionPlatform.WEB.setExecutionPlatformValue(hybridPropertyReader
				.getProperty(ExecutionPlatform.WEB.getExecutionPlatform()).equals(FLAGS.ON.getFlag()) ? true : false);
		ExecutionPlatform.API.setExecutionPlatformValue(hybridPropertyReader
				.getProperty(ExecutionPlatform.API.getExecutionPlatform()).equals(FLAGS.ON.getFlag()) ? true : false);
	}
	
	private void setMobileAppConfiguration() throws HybridException {
		
		if (ExecutionPlatform.MOBILE.getExecutionPlatformValue()) {
			ExecutionTool.MOBILE_TOOL.setExecutionToolValue(
					hybridPropertyReader.getProperty(ExecutionTool.MOBILE_TOOL.getExecutionTool()));
			ExecutionPlatformHost.MOBILE.setExecutionPlatformHostValue(
					hybridPropertyReader.getProperty(ExecutionPlatformHost.MOBILE.getExecutionPlatformHost()));
			MobileExecutionPlatform.ANDROID.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(MobileExecutionPlatform.ANDROID.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
			MobileExecutionPlatform.IPHONE.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(MobileExecutionPlatform.IPHONE.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
			MobileExecutionPlatform.IPAD.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(MobileExecutionPlatform.IPAD.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
		}
	}
	
	private void setMobileWebConfiguration() throws HybridException {
		
		if (ExecutionPlatform.MOBILE_WEB.getExecutionPlatformValue()) {
			ExecutionTool.MOBILE_WEB_TOOL.setExecutionToolValue(
					hybridPropertyReader.getProperty(ExecutionTool.MOBILE_WEB_TOOL.getExecutionTool()));
			ExecutionPlatformHost.MOBILE_WEB.setExecutionPlatformHostValue(
					hybridPropertyReader.getProperty(ExecutionPlatformHost.MOBILE_WEB.getExecutionPlatformHost()));
			MobileExecutionPlatform.ANDROID_WEB.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(MobileExecutionPlatform.ANDROID_WEB.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
			MobileExecutionPlatform.IPHONE_WEB.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(MobileExecutionPlatform.IPHONE_WEB.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
			MobileExecutionPlatform.IPAD_WEB.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(MobileExecutionPlatform.IPAD_WEB.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
		}
	}
	
	private void setWebConfiguration() throws HybridException {
		
		if (ExecutionPlatform.WEB.getExecutionPlatformValue()) {
			ExecutionTool.WEB_TOOL.setExecutionToolValue(
					hybridPropertyReader.getProperty(ExecutionTool.WEB_TOOL.getExecutionTool()));
			ExecutionPlatformHost.WEB.setExecutionPlatformHostValue(
					hybridPropertyReader.getProperty(ExecutionPlatformHost.WEB.getExecutionPlatformHost()));
			WebExecutionPlatform.CHROME.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(WebExecutionPlatform.CHROME.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
			WebExecutionPlatform.FIREFOX.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(WebExecutionPlatform.FIREFOX.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
			WebExecutionPlatform.IE.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(WebExecutionPlatform.IE.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
			WebExecutionPlatform.EDGE.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(WebExecutionPlatform.EDGE.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
			WebExecutionPlatform.SAFARI.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(WebExecutionPlatform.SAFARI.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
			WebExecutionPlatform.OPERA.setExecutionPlatformValue(
					hybridPropertyReader.getProperty(WebExecutionPlatform.OPERA.getExecutionPlatform())
							.equals(FLAGS.ON.getFlag()) ? true : false);
		}
	}
	
	public int getTotalThreadCount() {
		
		int count = 0;
		if (ExecutionPlatform.MOBILE.getExecutionPlatformValue()) {
			if(MobileExecutionPlatform.ANDROID.getExecutionPlatformValue()) {
				count++;
			}
			if(MobileExecutionPlatform.IPHONE.getExecutionPlatformValue()) {
				count++;
			}
			if(MobileExecutionPlatform.IPAD.getExecutionPlatformValue()) {
				count++;
			}
		}
		if (ExecutionPlatform.MOBILE_WEB.getExecutionPlatformValue()) {
			if(MobileExecutionPlatform.ANDROID_WEB.getExecutionPlatformValue()) {
				count++;
			}
			if(MobileExecutionPlatform.IPHONE_WEB.getExecutionPlatformValue()) {
				count++;
			}
			if(MobileExecutionPlatform.IPAD_WEB.getExecutionPlatformValue()) {
				count++;
			}
		}
		if (ExecutionPlatform.WEB.getExecutionPlatformValue()) {
			for(WebExecutionPlatform webplatform : WebExecutionPlatform.values()) {
				if(webplatform.getExecutionPlatformValue()) {
					count++;
				}
			}
		}

		return count;
	}
}
