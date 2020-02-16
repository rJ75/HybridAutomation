package hybrid;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hybrid.HybridConstants.AppiumServerConfig;
import hybrid.HybridConstants.AppiumStudioConfig;
import hybrid.HybridConstants.Applicaton;
import hybrid.HybridConstants.ExecutionPlatform;
import hybrid.HybridConstants.ExecutionPlatformHost;
import hybrid.HybridConstants.ExecutionTool;
import hybrid.HybridConstants.FLAGS;
import hybrid.HybridConstants.HeadSpinConfig;
import hybrid.HybridConstants.MobileExecutionPlatform;
import hybrid.HybridConstants.PerfectoConfig;
import hybrid.HybridConstants.Report;
import hybrid.HybridConstants.SauceLabsConfig;
import hybrid.HybridConstants.SeetestConfig;
import hybrid.HybridConstants.SeleniumConfig;
import hybrid.HybridConstants.TestDataOption;
import hybrid.HybridConstants.Tools;
import hybrid.HybridConstants.WebExecutionPlatform;
import hybrid.HybridConstants.pCloudyConfig;
import hybrid.datahandler.HybridPropertyFileHandler;

public class HybridConfigurations {

	public static HybridConfigurations hybridconfig;
	private static HybridPropertyFileHandler hybridPropertyReader, toolsPropertyReader;
	private static List<Map<String, String>> capability;

	private HybridConfigurations() throws HybridException {
		hybridPropertyReader = new HybridPropertyFileHandler(HybridConstants.HYBRID_CONFIG_PROPERTY_FILE);
		toolsPropertyReader = new HybridPropertyFileHandler(HybridConstants.TOOLS_PROPERTY_FILE);
		setConfigSettings();
	}

	public static synchronized HybridConfigurations getInstance() throws HybridException {

		if (hybridconfig == null) {
			hybridconfig = new HybridConfigurations();
		}
		return hybridconfig;
	}

	private void setConfigSettings() throws HybridException {

		setExecutionPlatforms();
		setTestDataConfiguration();
		setApplicationConfiguration();
		setReportingConfiguration();
		setMobileAppConfiguration();
		setMobileWebConfiguration();
		setWebConfiguration();
		setPlatformToolsConfiguration();
		setCapability();
	}

	public List<Map<String, String>> getCapability() throws HybridException {
		if (capability.isEmpty() || capability.size() == 0) {
			throw new HybridException("No execution is set");
		}
		return capability;
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

	private void setTestDataConfiguration() throws HybridException {
		TestDataOption.TEST_DATA_SOURCE.setTestDataOptionValue(
				hybridPropertyReader.getProperty(TestDataOption.TEST_DATA_SOURCE.getTestDataOption()));
	}

	private void setApplicationConfiguration() {
		Applicaton.AUT_NAME
				.setApplicationOptionValue(hybridPropertyReader.getProperty(Applicaton.AUT_NAME.getApplictionOption()));
		setApplicationConfigPath();
		HybridPropertyFileHandler reader = new HybridPropertyFileHandler(HybridConstants.applicationConfigFile);
		Applicaton.ANDROID_APP_NAME
				.setApplicationOptionValue(reader.getProperty(Applicaton.ANDROID_APP_NAME.getApplictionOption()));
		Applicaton.IOS_APP_NAME
				.setApplicationOptionValue(reader.getProperty(Applicaton.IOS_APP_NAME.getApplictionOption()));
		Applicaton.WEB_APP_ADDRESS
				.setApplicationOptionValue(reader.getProperty(Applicaton.WEB_APP_ADDRESS.getApplictionOption()));
		Applicaton.ANDROID_APP_ACTIVITY
				.setApplicationOptionValue(reader.getProperty(Applicaton.ANDROID_APP_ACTIVITY.getApplictionOption()));
	}

	private void setApplicationConfigPath() {
		HybridConstants.applicationConfigFile = String.format("%s%s%s%s%s%s",
				HybridConstants.APPLICATION_RESOURCES_PARENT_FOLDER, File.separator,
				Applicaton.AUT_NAME.getApplicationOptionValue(), File.separator,
				Applicaton.AUT_NAME.getApplicationOptionValue(), HybridConstants.PROPERTIES_EXTENSION);
	}

	private void setReportingConfiguration() {
		Report.REPORT_TYPE.setReportOptionValue(hybridPropertyReader.getProperty(Report.REPORT_TYPE.getReportOption()));
		Report.APPEND_REPORT
				.setReportOptionValue(hybridPropertyReader.getProperty(Report.APPEND_REPORT.getReportOption()));
		Report.REPORT_DIRECTORY
				.setReportOptionValue(hybridPropertyReader.getProperty(Report.REPORT_DIRECTORY.getReportOption()));
		Report.PROJECT_BASE_DIRECTORY.setReportOptionValue(
				hybridPropertyReader.getProperty(Report.PROJECT_BASE_DIRECTORY.getReportOption()));
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
			ExecutionTool.WEB_TOOL
					.setExecutionToolValue(hybridPropertyReader.getProperty(ExecutionTool.WEB_TOOL.getExecutionTool()));
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

	private void setCapability() {

		capability = new ArrayList<Map<String, String>>();
		setMobileCapability();
		setMobileWebCapability();
		Map<String, String> platformCapability;
		if (ExecutionPlatform.WEB.getExecutionPlatformValue()) {
			for (WebExecutionPlatform webplatform : WebExecutionPlatform.values()) {
				if (webplatform.getExecutionPlatformValue()) {
					platformCapability = new HashMap<String, String>();
					platformCapability.put(ExecutionPlatform.class.getSimpleName(), webplatform.getExecutionPlatform());
					platformCapability.put(ExecutionPlatformHost.class.getSimpleName(),
							ExecutionPlatformHost.WEB.getExecutionPlatformHostValue());
					capability.add(platformCapability);
				}
			}
		}
		if (ExecutionPlatform.API.getExecutionPlatformValue()) {
			platformCapability = new HashMap<String, String>();
			platformCapability.put(ExecutionPlatform.class.getSimpleName(),
					ExecutionPlatform.API.getExecutionPlatform());
			capability.add(platformCapability);
		}
	}
	
	private void setMobileWebCapability() {
		
		Map<String, String> platformCapability = new HashMap<String, String>();
		if (ExecutionPlatform.MOBILE_WEB.getExecutionPlatformValue()) {
			if (MobileExecutionPlatform.ANDROID_WEB.getExecutionPlatformValue()) {
				platformCapability = new HashMap<String, String>();
				platformCapability.put(ExecutionPlatform.class.getSimpleName(),
						MobileExecutionPlatform.ANDROID_WEB.getExecutionPlatform());
				platformCapability.put(ExecutionPlatformHost.class.getSimpleName(),
						ExecutionPlatformHost.MOBILE_WEB.getExecutionPlatformHostValue());
				capability.add(platformCapability);
			}
			if (MobileExecutionPlatform.IPHONE_WEB.getExecutionPlatformValue()) {
				platformCapability = new HashMap<String, String>();
				platformCapability.put(ExecutionPlatform.class.getSimpleName(),
						MobileExecutionPlatform.IPHONE_WEB.getExecutionPlatform());
				platformCapability.put(ExecutionPlatformHost.class.getSimpleName(),
						ExecutionPlatformHost.MOBILE_WEB.getExecutionPlatformHostValue());
				capability.add(platformCapability);
			}
			if (MobileExecutionPlatform.IPAD_WEB.getExecutionPlatformValue()) {
				platformCapability = new HashMap<String, String>();
				platformCapability.put(ExecutionPlatform.class.getSimpleName(),
						MobileExecutionPlatform.IPAD_WEB.getExecutionPlatform());
				platformCapability.put(ExecutionPlatformHost.class.getSimpleName(),
						ExecutionPlatformHost.MOBILE_WEB.getExecutionPlatformHostValue());
				capability.add(platformCapability);
			}
		}
	}
	
	private void setMobileCapability() {
		
		Map<String, String> platformCapability = new HashMap<String, String>();
		if (ExecutionPlatform.MOBILE.getExecutionPlatformValue()) {
			if (MobileExecutionPlatform.ANDROID.getExecutionPlatformValue()) {
				platformCapability = new HashMap<String, String>();
				platformCapability.put(ExecutionPlatform.class.getSimpleName(),
						MobileExecutionPlatform.ANDROID.getExecutionPlatform());
				platformCapability.put(ExecutionPlatformHost.class.getSimpleName(),
						ExecutionPlatformHost.MOBILE.getExecutionPlatformHostValue());
				capability.add(platformCapability);
			}
			if (MobileExecutionPlatform.IPHONE.getExecutionPlatformValue()) {
				platformCapability = new HashMap<String, String>();
				platformCapability.put(ExecutionPlatform.class.getSimpleName(),
						MobileExecutionPlatform.IPHONE.getExecutionPlatform());
				platformCapability.put(ExecutionPlatformHost.class.getSimpleName(),
						ExecutionPlatformHost.MOBILE.getExecutionPlatformHostValue());
				capability.add(platformCapability);
			}
			if (MobileExecutionPlatform.IPAD.getExecutionPlatformValue()) {
				platformCapability = new HashMap<String, String>();
				platformCapability.put(ExecutionPlatform.class.getSimpleName(),
						MobileExecutionPlatform.IPAD.getExecutionPlatform());
				platformCapability.put(ExecutionPlatformHost.class.getSimpleName(),
						ExecutionPlatformHost.MOBILE.getExecutionPlatformHostValue());
				capability.add(platformCapability);
			}
		}
	}

	private void setPlatformToolsConfiguration() throws HybridException {

		if (ExecutionPlatform.MOBILE.getExecutionPlatformValue()) {
			setToolConfiguration(Tools.valueOf(ExecutionTool.MOBILE_TOOL.getExecutionToolValue()));
		}
		if (ExecutionPlatform.MOBILE_WEB.getExecutionPlatformValue()) {
			setToolConfiguration(Tools.valueOf(ExecutionTool.MOBILE_WEB_TOOL.getExecutionToolValue()));
		}
		if (ExecutionPlatform.WEB.getExecutionPlatformValue()) {
			setToolConfiguration(Tools.valueOf(ExecutionTool.WEB_TOOL.getExecutionToolValue()));
		}
	}

	private void setToolConfiguration(Tools tool) throws HybridException {

		switch (tool) {

		case APPIUMSTUDIO:
			setAppiumStudioConfiguration();
			break;
		case SEETEST:
			setSeetestConfiguration();
			break;
		case SELENIUM:
			setSeleniumConfiguration();
			break;
		case APPIUM:
			setAppiumServerConfiguration();
			break;
		case PERFECTO:
			setPerfectoConfiguration();
			break;
		case HEADSPIN:
			setHeadspinConfiguration();
			break;
		case SAUCELABS:
			setSaucelabsConfiguration();
			break;
		case PCLOUDY:
			setpCloudyConfiguration();
			break;
		default:
			throw new HybridException("Add new tool configuration");
		}
	}

	private void setpCloudyConfiguration() {
		pCloudyConfig.CLOUD_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(pCloudyConfig.CLOUD_URL.getConfigOption()));
		pCloudyConfig.USERNAME
				.setConfigOptionValue(toolsPropertyReader.getProperty(pCloudyConfig.USERNAME.getConfigOption()));
		pCloudyConfig.PASSWORD
				.setConfigOptionValue(toolsPropertyReader.getProperty(pCloudyConfig.PASSWORD.getConfigOption()));
		pCloudyConfig.ACCESSKEY
				.setConfigOptionValue(toolsPropertyReader.getProperty(pCloudyConfig.ACCESSKEY.getConfigOption()));
		pCloudyConfig.LOCAL_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(pCloudyConfig.LOCAL_URL.getConfigOption()));
	}

	private void setSaucelabsConfiguration() {
		SauceLabsConfig.CLOUD_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(SauceLabsConfig.CLOUD_URL.getConfigOption()));
		SauceLabsConfig.USERNAME
				.setConfigOptionValue(toolsPropertyReader.getProperty(SauceLabsConfig.USERNAME.getConfigOption()));
		SauceLabsConfig.PASSWORD
				.setConfigOptionValue(toolsPropertyReader.getProperty(SauceLabsConfig.PASSWORD.getConfigOption()));
		SauceLabsConfig.ACCESSKEY
				.setConfigOptionValue(toolsPropertyReader.getProperty(SauceLabsConfig.ACCESSKEY.getConfigOption()));
		SauceLabsConfig.LOCAL_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(SauceLabsConfig.LOCAL_URL.getConfigOption()));
	}

	private void setHeadspinConfiguration() {
		HeadSpinConfig.CLOUD_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(HeadSpinConfig.CLOUD_URL.getConfigOption()));
		HeadSpinConfig.USERNAME
				.setConfigOptionValue(toolsPropertyReader.getProperty(HeadSpinConfig.USERNAME.getConfigOption()));
		HeadSpinConfig.PASSWORD
				.setConfigOptionValue(toolsPropertyReader.getProperty(HeadSpinConfig.PASSWORD.getConfigOption()));
		HeadSpinConfig.ACCESSKEY
				.setConfigOptionValue(toolsPropertyReader.getProperty(HeadSpinConfig.ACCESSKEY.getConfigOption()));
		HeadSpinConfig.LOCAL_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(HeadSpinConfig.LOCAL_URL.getConfigOption()));
	}

	private void setPerfectoConfiguration() {
		PerfectoConfig.CLOUD_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(PerfectoConfig.CLOUD_URL.getConfigOption()));
		PerfectoConfig.USERNAME
				.setConfigOptionValue(toolsPropertyReader.getProperty(PerfectoConfig.USERNAME.getConfigOption()));
		PerfectoConfig.PASSWORD
				.setConfigOptionValue(toolsPropertyReader.getProperty(PerfectoConfig.PASSWORD.getConfigOption()));
		PerfectoConfig.ACCESSKEY
				.setConfigOptionValue(toolsPropertyReader.getProperty(PerfectoConfig.ACCESSKEY.getConfigOption()));
		PerfectoConfig.LOCAL_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(PerfectoConfig.LOCAL_URL.getConfigOption()));
	}

	private void setAppiumStudioConfiguration() {
		AppiumStudioConfig.CLOUD_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumStudioConfig.CLOUD_URL.getConfigOption()));
		AppiumStudioConfig.USERNAME
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumStudioConfig.USERNAME.getConfigOption()));
		AppiumStudioConfig.PASSWORD
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumStudioConfig.PASSWORD.getConfigOption()));
		AppiumStudioConfig.ACCESSKEY
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumStudioConfig.ACCESSKEY.getConfigOption()));
		AppiumStudioConfig.LOCAL_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumStudioConfig.LOCAL_URL.getConfigOption()));
	}

	private void setAppiumServerConfiguration() {
		AppiumServerConfig.CLOUD_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumServerConfig.CLOUD_URL.getConfigOption()));
		AppiumServerConfig.USERNAME
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumServerConfig.USERNAME.getConfigOption()));
		AppiumServerConfig.PASSWORD
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumServerConfig.PASSWORD.getConfigOption()));
		AppiumServerConfig.ACCESSKEY
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumServerConfig.ACCESSKEY.getConfigOption()));
		AppiumServerConfig.LOCAL_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(AppiumServerConfig.LOCAL_URL.getConfigOption()));
	}

	private void setSeetestConfiguration() {
		SeetestConfig.CLOUD_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeetestConfig.CLOUD_URL.getConfigOption()));
		SeetestConfig.USERNAME
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeetestConfig.USERNAME.getConfigOption()));
		SeetestConfig.PASSWORD
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeetestConfig.PASSWORD.getConfigOption()));
		SeetestConfig.ACCESSKEY
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeetestConfig.ACCESSKEY.getConfigOption()));
		SeetestConfig.LOCAL_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeetestConfig.LOCAL_URL.getConfigOption()));
	}

	private void setSeleniumConfiguration() {
		SeleniumConfig.CLOUD_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeleniumConfig.CLOUD_URL.getConfigOption()));
		SeleniumConfig.USERNAME
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeleniumConfig.USERNAME.getConfigOption()));
		SeleniumConfig.PASSWORD
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeleniumConfig.PASSWORD.getConfigOption()));
		SeleniumConfig.ACCESSKEY
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeleniumConfig.ACCESSKEY.getConfigOption()));
		SeleniumConfig.LOCAL_URL
				.setConfigOptionValue(toolsPropertyReader.getProperty(SeleniumConfig.LOCAL_URL.getConfigOption()));
	}
}
