package hybrid;

public class HybridConfigurations {
	
	protected enum ExecutionPlatform {
		MOBILE("Mobile"),
		MOBILE_WEB("MobileWeb"),
		WEB("Web"),
		API("API");
		
		private String platform;

		ExecutionPlatform(String executionPlatform) {
			this.platform = executionPlatform;
		}
		
		protected String getExecutionPlatform() {
			return platform;
		}
	}
	
	protected enum ExecutionPlatformHost {
		MOBILE("MobileExecutionHost"),
		MOBILE_WEB("MobileWebExecutionHost"),
		WEB("WebExecutionHost");

		
		private String platformHost;

		ExecutionPlatformHost(String executionPlatformHost) {
			this.platformHost = executionPlatformHost;
		}
		
		protected String getExecutionPlatform() {
			return platformHost;
		}
	}
	
	
	
	
	
	

}
