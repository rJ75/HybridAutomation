package hybrid;

import java.util.Arrays;

public class HybridConstants {

	public static final String UTF8_FORMAT = "UTF8";
	public static final String HYBRID_CONFIG_PROPERTY_FILE = "HybridConfig.properties";

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
			if(Arrays.toString(PlatformHosts.values()).contains(platformHostValue)) {
				this.platformHostValue = PlatformHosts.valueOf(platformHostValue).toString();
			} else {
				throw new HybridException("Host value from config parameter" + platformHost + " is not matching with predefined values");
			}
		}

		public String getExecutionPlatformHostValue() {
			return platformHostValue;
		}
	}
	
	private enum PlatformHosts{
		
		LOCAL,GRID;
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

		protected String getExecutionToolValue() {
			return executionToolValue;
		}
		
		protected void setExecutionToolValue(String executionToolValue) throws HybridException {
			executionToolValue = executionToolValue.trim();
			if(Arrays.toString(Tools.values()).contains(executionToolValue)) {
				this.executionToolValue = Tools.valueOf(executionToolValue).toString();
			} else {
				throw new HybridException("Host value from config parameter" + executionTool + " is not matching with predefined values");
			}
		}
	}
	
	private enum Tools{
		
		APPIUMSTUDIO, SEETEST, PERFECTO, HEADSPIN, SELENIUM;
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

		protected boolean getExecutionPlatformValue() {
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

		protected boolean getExecutionPlatformValue() {
			return executionPlatformValue;
		}

		protected void setExecutionPlatformValue(boolean executionPlatformValue) {
			this.executionPlatformValue = executionPlatformValue;
		}
	}

	enum TestData {
		
		TEST_DATA_SOURCE("TestDataSource"), TEST_REPOSITORY_NAME("TestRepositoryName"),
		OBJECT_REPOSITORY_NAME("ObjectRepositoryName");

		private String testDataOption;
		private String testDataOptionValue;

		TestData(String testDataOption) {
			this.testDataOption = testDataOption.trim();
		}

		protected String getTestDataOption() {
			return testDataOption;
		}

		protected String getTestDataOptionValue() {
			return testDataOptionValue;
		}

		protected void setTestDataOptionValue(String testDataOptionValue) {
			this.testDataOptionValue = testDataOptionValue.trim();
		}
	}
	
	enum TestDataSources {
		
		DB, EXCEL, CSV, PROPERTIES, JSON, XML, YAML;
	}

	enum Report {
		
		REPORT("Report"), APPEND("Append"), REPORT_DIRECTORY("ReportDirectory"),
		PROJECT_BASE_DIRECTORY("ProjectBaseDirectory");

		private String reportOption;
		private String reportOptionValue;

		Report(String reportOption) {
			this.reportOption = reportOption.trim();
		}

		protected String getReportOption() {
			return reportOption;
		}

		protected String getReportOptionValue() {
			return reportOptionValue;
		}

		protected void setReportOptionValue(String reportOptionValue) {
			this.reportOptionValue = reportOptionValue.trim();
		}
	}

	enum Applicaton {
		
		AUT_NAME("AUTName");

		private String applicationOption;
		private String applicationOptionValue;

		Applicaton(String applicationOption) {
			this.applicationOption = applicationOption.trim();
		}

		protected String getApplictionOption() {
			return applicationOption;
		}

		protected String getApplicationOptionValue() {
			return applicationOptionValue;
		}

		protected void setApplicationOptionValue(String applicationOptionValue) {
			this.applicationOptionValue = applicationOptionValue.trim();
		}
	}

	public enum FLAGS {
		
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
	

}
