package hybrid;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hybrid.HybridConstants.Applicaton;
import hybrid.HybridConstants.ExecutionPlatform;
import hybrid.HybridConstants.ExecutionPlatformHost;
import hybrid.HybridConstants.ExecutionTool;
import hybrid.HybridConstants.FLAGS;
import hybrid.HybridConstants.MobileExecutionPlatform;
import hybrid.HybridConstants.Report;
import hybrid.HybridConstants.TestDataOption;
import hybrid.HybridConstants.Tools;
import hybrid.HybridConstants.WebExecutionPlatform;
import hybrid.datahandler.HybridPropertyFileHandler;

public class HybridConfigurations {

	public static HybridConfigurations hybridconfig;
	private static HybridPropertyFileHandler hybridPropertyReader;
	private static List<Map<String, String>> capability;

	private HybridConfigurations() throws HybridException {
		hybridPropertyReader = new HybridPropertyFileHandler(HybridConstants.HYBRID_CONFIG_PROPERTY_FILE);
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
				setAppiumConfiguration();
				break;
			case SEETEST:
				setAppiumConfiguration();
				break;
			case SELENIUM:
				setAppiumConfiguration();
				break;
			case APPIUM:
				setAppiumConfiguration();
				break;
			case PERFECTO:
				setAppiumConfiguration();
				break;
			case HEADSPIN:
				setAppiumConfiguration();
				break;
			case SAUCELABS:
				setAppiumConfiguration();
				break;
			case PCLOUDY:
				setAppiumConfiguration();
				break;
			default:
				throw new HybridException("Add new tool configuration");
		}
	}

	private void setAppiumConfiguration() {

	}

}
