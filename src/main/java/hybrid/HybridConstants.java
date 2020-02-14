package hybrid;

import java.io.File;
import java.util.Arrays;

public class HybridConstants {

	public static final String UTF8_FORMAT = "UTF8";
	public static final String PROPERTIES_EXTENSION = ".properties";
	public static final String HYBRID_CONFIG_PROPERTY_FILE = "HybridConfig" + PROPERTIES_EXTENSION;
	public static final String CONFIG_PROPERTIES_FOLDER = "Resources" + File.separator + "HybridAutomation"
			+ File.separator + "ConfigProperties";
	public static final String APPLICATION_TEST_PARENT_FOLDER = "src" + File.separator + "test" + File.separator
			+ "java";
	public static final String APPLICATION_RESOURCES_PARENT_FOLDER = "Resources";
	public static String applicationConfigFile;

	enum ExecutionPlatform {

		MOBILE("Mobile"), MOBILE_WEB("MobileWeb"), WEB("Web"), API("API");

		private String platform;
		private boolean platformValue;

		ExecutionPlatform(String executionPlatform) {
			this.platform = executionPlatform;
		}

		protected String getExecutionPlatform() {
			return platform;
		}

		protected void setExecutionPlatformValue(boolean platformValue) {
			this.platformValue = platformValue;
		}

		public boolean getExecutionPlatformValue() {
			return platformValue;
		}
	}

	enum ExecutionPlatformHost {

		MOBILE("MobileExecutionHost"), MOBILE_WEB("MobileWebExecutionHost"), WEB("WebExecutionHost");

		private String platformHost;
		private String platformHostValue;

		ExecutionPlatformHost(String executionPlatformHost) {
			this.platformHost = executionPlatformHost;
		}

		protected String getExecutionPlatformHost() {
			return platformHost;
		}

		protected void setExecutionPlatformHostValue(String platformHostValue) throws HybridException {
			platformHostValue = platformHostValue.trim();
			if (Arrays.toString(PlatformHosts.values()).contains(platformHostValue)) {
				this.platformHostValue = PlatformHosts.valueOf(platformHostValue).toString();
			} else {
				throw new HybridException(
						"Host value from config parameter" + platformHost + " is not matching with predefined values");
			}
		}

		public String getExecutionPlatformHostValue() {
			return platformHostValue;
		}
	}

	enum PlatformHosts {

		LOCAL, GRID;
	}

	enum ExecutionTool {

		MOBILE_TOOL("MobileTool"), MOBILE_WEB_TOOL("MobileWebTool"), WEB_TOOL("WebTool");

		private String executionTool;
		private String executionToolValue;

		ExecutionTool(String executionTool) {
			this.executionTool = executionTool.trim();
		}

		protected String getExecutionTool() {
			return executionTool;
		}

		public String getExecutionToolValue() {
			return executionToolValue;
		}

		protected void setExecutionToolValue(String executionToolValue) throws HybridException {
			executionToolValue = executionToolValue.trim();
			if (Arrays.toString(Tools.values()).contains(executionToolValue)) {
				this.executionToolValue = Tools.valueOf(executionToolValue).toString();
			} else {
				throw new HybridException(
						"Host value from config parameter" + executionTool + " is not matching with predefined values");
			}
		}
	}

	enum Tools {

		APPIUMSTUDIO, SEETEST, PERFECTO, HEADSPIN, SELENIUM, SAUCELABS, PCLOUDY, APPIUM;
	}

	enum MobileExecutionPlatform {

		ANDROID("Android"), IPHONE("iPhone"), IPAD("iPad"), ANDROID_WEB("AndroidWeb"), IPHONE_WEB("iPhoneWeb"),
		IPAD_WEB("iPadWeb");

		private String executionPlatform;
		private boolean executionPlatformValue;

		MobileExecutionPlatform(String executionPlatform) {
			this.executionPlatform = executionPlatform.trim();
		}

		protected String getExecutionPlatform() {
			return executionPlatform;
		}

		public boolean getExecutionPlatformValue() {
			return executionPlatformValue;
		}

		protected void setExecutionPlatformValue(boolean executionPlatformValue) {
			this.executionPlatformValue = executionPlatformValue;
		}
	}

	enum WebExecutionPlatform {

		CHROME("Chrome"), FIREFOX("FireFox"), IE("IE"), EDGE("Edge"), SAFARI("Safari"), OPERA("Opera");

		private String executionPlatform;
		private boolean executionPlatformValue;

		WebExecutionPlatform(String executionPlatform) {
			this.executionPlatform = executionPlatform.trim();
		}

		protected String getExecutionPlatform() {
			return executionPlatform;
		}

		public boolean getExecutionPlatformValue() {
			return executionPlatformValue;
		}

		protected void setExecutionPlatformValue(boolean executionPlatformValue) {
			this.executionPlatformValue = executionPlatformValue;
		}
	}

	enum TestDataOption {

		TEST_DATA_SOURCE("TestDataSource");

		private String testDataOption;
		private String testDataOptionValue;

		TestDataOption(String testDataOption) {
			this.testDataOption = testDataOption.trim();
		}

		protected String getTestDataOption() {
			return testDataOption;
		}

		public String getTestDataOptionValue() {
			return testDataOptionValue;
		}

		protected void setTestDataOptionValue(String testDataOptionValue) throws HybridException {
			testDataOptionValue = testDataOptionValue.trim();
			if (Arrays.toString(TestDataSources.values()).contains(testDataOptionValue)) {
				this.testDataOptionValue = TestDataSources.valueOf(testDataOptionValue).toString();
			} else {
				throw new HybridException("Host value from config parameter" + testDataOption
						+ " is not matching with predefined values");
			}
		}
	}

	enum TestDataSources {

		DB, EXCEL, CSV, PROPERTIES, JSON, XML, YAML;
	}

	enum Report {

		REPORT_TYPE("ReportType"), APPEND_REPORT("AppendReport"), REPORT_DIRECTORY("ReportDirectory"),
		PROJECT_BASE_DIRECTORY("ProjectBaseDirectory");

		private String reportOption;
		private String reportOptionValue;

		Report(String reportOption) {
			this.reportOption = reportOption.trim();
		}

		protected String getReportOption() {
			return reportOption;
		}

		public String getReportOptionValue() {
			return reportOptionValue;
		}

		protected void setReportOptionValue(String reportOptionValue) {
			this.reportOptionValue = reportOptionValue.trim();
		}
	}

	enum Applicaton {

		AUT_NAME("AUTName"), ANDROID_APP_NAME("AndroidAppName"), IOS_APP_NAME("iOSAppName"),
		WEB_APP_ADDRESS("WebAppAddress"), ANDROID_APP_ACTIVITY("AndroidAppActivity");

		private String applicationOption;
		private String applicationOptionValue;

		Applicaton(String applicationOption) {
			this.applicationOption = applicationOption.trim();
		}

		protected String getApplictionOption() {
			return applicationOption;
		}

		public String getApplicationOptionValue() {
			return applicationOptionValue;
		}

		protected void setApplicationOptionValue(String applicationOptionValue) {
			this.applicationOptionValue = applicationOptionValue.trim();
		}
	}

	enum FLAGS {

		ON("ON", true), OFF("OFF", false);

		private String flag;
		private boolean flagBoolean;

		FLAGS(String flag, boolean flagBoolean) {
			this.setFlag(flag);
			this.setFlagBoolean(flagBoolean);
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag.trim();
		}

		public boolean getFlagBoolean() {
			return flagBoolean;
		}

		public void setFlagBoolean(boolean flagBoolean) {
			this.flagBoolean = flagBoolean;
		}
	}

	enum DBConfig {

		TEST_REPOSITORY_NAME("TestRepositoryName"), OBJECT_REPOSITORY_NAME("ObjectRepositoryName"),
		DRIVER_CLASS("DriverClass"), DB_URL("DBUrl"), DB_USERNAME("DBUsername"), DB_PASSWORD("DBPassword"),
		TEST_QUERY("TestQuery");

		private String dbConfigOption;
		private String dbConfigOptionValue;

		DBConfig(String dbConfigOption) {
			this.dbConfigOption = dbConfigOption.trim();
		}

		protected String getDBConfigOption() {
			return dbConfigOption;
		}

		public String getDBConfigOptionValue() {
			return dbConfigOptionValue;
		}

		protected void setDBConfigOptionValue(String dbConfigOptionValue) {
			this.dbConfigOptionValue = dbConfigOptionValue.trim();
		}
	}

	enum COMMON_OBJECTS {
		MOBILE_COMMON, MOBILE_WEB_COMMON, WEB_COMMON;
	}

	enum PerfectoConfig {

		CLOUD_URL("PerfectoCloudURL"), USERNAME("PerfectoUsername"), PASSWORD("PerfectoPassword"),
		ACCESSKEY("PerfectoAccessKey"), LOCAL_URL("PerfectoLocalURL");

		private String configOption;
		private String configOptionValue;

		PerfectoConfig(String configOption) {
			this.configOption = configOption.trim();
		}

		public String getConfigOption() {
			return configOption;
		}

		public String getConfigOptionValue() {
			return configOptionValue;
		}

		public void setConfigOptionValue(String configOptionValue) {
			this.configOptionValue = configOptionValue.trim();
		}
	}

	enum SeeTestConfig {

		CLOUD_URL("SeeTestCloudURL"), USERNAME("SeeTestUsername"), PASSWORD("SeeTestPassword"),
		ACCESSKEY("SeeTestAccessKey"), LOCAL_URL("SeeTestLocalURL");

		private String configOption;
		private String configOptionValue;

		SeeTestConfig(String configOption) {
			this.configOption = configOption.trim();
		}

		public String getConfigOption() {
			return configOption;
		}

		public String getConfigOptionValue() {
			return configOptionValue;
		}

		public void setConfigOptionValue(String configOptionValue) {
			this.configOptionValue = configOptionValue.trim();
		}
	}

	enum HeadSpinConfig {

		CLOUD_URL("HeadSpinCloudURL"), USERNAME("HeadSpinUsername"), PASSWORD("HeadSpinPassword"),
		ACCESSKEY("HeadSpinAccessKey"), LOCAL_URL("HeadSpinLocalURL");

		private String configOption;
		private String configOptionValue;

		HeadSpinConfig(String configOption) {
			this.configOption = configOption.trim();
		}

		public String getConfigOption() {
			return configOption;
		}

		public String getConfigOptionValue() {
			return configOptionValue;
		}

		public void setConfigOptionValue(String configOptionValue) {
			this.configOptionValue = configOptionValue.trim();
		}
	}

	enum SauceLabsConfig {

		CLOUD_URL("SauceLabsCloudURL"), USERNAME("SauceLabsUsername"), PASSWORD("SauceLabsPassword"),
		ACCESSKEY("SauceLabsAccessKey"), LOCAL_URL("SauceLabsLocalURL");

		private String configOption;
		private String configOptionValue;

		SauceLabsConfig(String configOption) {
			this.configOption = configOption.trim();
		}

		public String getConfigOption() {
			return configOption;
		}

		public String getConfigOptionValue() {
			return configOptionValue;
		}

		public void setConfigOptionValue(String configOptionValue) {
			this.configOptionValue = configOptionValue.trim();
		}
	}

	enum PCloudyConfig {

		CLOUD_URL("PCloudyCloudURL"), USERNAME("PCloudyUsername"), PASSWORD("PCloudyPassword"),
		ACCESSKEY("PCloudyAccessKey"), LOCAL_URL("PCloudyLocalURL");

		private String configOption;
		private String configOptionValue;

		PCloudyConfig(String configOption) {
			this.configOption = configOption.trim();
		}

		public String getConfigOption() {
			return configOption;
		}

		public String getConfigOptionValue() {
			return configOptionValue;
		}

		public void setConfigOptionValue(String configOptionValue) {
			this.configOptionValue = configOptionValue.trim();
		}
	}

	enum AppiumStudioConfig {

		CLOUD_URL("AppiumStudioCloudURL"), USERNAME("AppiumStudioUsername"), PASSWORD("AppiumStudioPassword"),
		ACCESSKEY("AppiumStudioAccessKey"), LOCAL_URL("AppiumStudioLocalURL");

		private String configOption;
		private String configOptionValue;

		AppiumStudioConfig(String configOption) {
			this.configOption = configOption.trim();
		}

		public String getConfigOption() {
			return configOption;
		}

		public String getConfigOptionValue() {
			return configOptionValue;
		}

		public void setConfigOptionValue(String configOptionValue) {
			this.configOptionValue = configOptionValue.trim();
		}
	}

	enum SeleniumConfig {

		CLOUD_URL("SeleniumCloudURL"), USERNAME("SeleniumUsername"), PASSWORD("SeleniumPassword"),
		ACCESSKEY("SeleniumAccessKey"), LOCAL_URL("SeleniumLocalURL");

		private String configOption;
		private String configOptionValue;

		SeleniumConfig(String configOption) {
			this.configOption = configOption.trim();
		}

		public String getConfigOption() {
			return configOption;
		}

		public String getConfigOptionValue() {
			return configOptionValue;
		}

		public void setConfigOptionValue(String configOptionValue) {
			this.configOptionValue = configOptionValue.trim();
		}
	}
	
	enum AppiumServerConfig {

		CLOUD_URL("AppiumServerCloudURL"), USERNAME("AppiumServerUsername"), PASSWORD("AppiumServerPassword"),
		ACCESSKEY("AppiumServerAccessKey"), LOCAL_URL("AppiumServerLocalURL");

		private String configOption;
		private String configOptionValue;

		AppiumServerConfig(String configOption) {
			this.configOption = configOption.trim();
		}

		public String getConfigOption() {
			return configOption;
		}

		public String getConfigOptionValue() {
			return configOptionValue;
		}

		public void setConfigOptionValue(String configOptionValue) {
			this.configOptionValue = configOptionValue.trim();
		}
	}
}
