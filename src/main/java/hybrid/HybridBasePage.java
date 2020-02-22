package hybrid;

import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import hybrid.HybridConstants.ExecutionPlatform;
import hybrid.HybridConstants.ExecutionPlatformHost;
import hybrid.HybridConstants.ExecutionTool;
import hybrid.HybridConstants.MobileExecutionPlatform;
import hybrid.HybridConstants.PlatformHosts;
import hybrid.HybridConstants.Tools;
import hybrid.HybridConstants.WebExecutionPlatform;
import hybrid.base.HybridCoreBase;
import hybrid.toolsAPI.HybridAppiumMobileAPI;
import hybrid.toolsAPI.HybridAppiumMobileWebAPI;
import hybrid.toolsAPI.HybridAppiumStudioMobileAPI;
import hybrid.toolsAPI.HybridAppiumStudioMobileWebAPI;
import hybrid.toolsAPI.HybridSeetestAPI;
import hybrid.toolsAPI.HybridSeleniumAPI;

public class HybridBasePage extends HybridTestData {

	public static HybridBasePage hybridBasePage = new HybridBasePage();
	private HybridCoreBase driver = null;
	public String threadName = null;
	public String currentPlatform;
	public String reportName = null;
	
	public String getCurrentPlatform() {
		return currentPlatform;
	}

	public Map<String, String> testData;
	
	public Map<String, String> getTestDataMap() {
		return testData;
	}

	public void setTestDataMap(Map<String, String> testData) {
		this.testData = testData;
	}

	private boolean isMobile, isAndroid, isiOS, isIPhone, isiPad,
			isMobileWeb, isAndroidWeb, isiPhoneWeb, isiPadWeb, isWeb,
			isChrome, isFirefox, isIe, isEdge, isSafari, isOpera,
			isLocal, isGrid, isAPI;
	
	private HybridBasePage() {
	}

	public static HybridBasePage getHybridBasePage() {

		return hybridBasePage;
	}

	public HybridCoreBase getDriver() {

		return driver;
	}
	
	private void initalize(){
		driver = null;
		isMobile = false;
		isAndroid = false; 
		isiOS = false;
		isIPhone = false;
		isiPad = false;
		isMobileWeb = false;
		isAndroidWeb = false;
		isiPhoneWeb = false;
		isiPadWeb = false;
		isWeb = false;
		isChrome = false;
		isFirefox = false;
		isIe = false;
		isEdge = false;
		isSafari = false;
		isOpera = false;
		isLocal = false;
		isGrid = false;
		isAPI = false;
	}

	public boolean isAPI() {
		return isAPI;
	}

	public boolean isMobile() {
		return isMobile;
	}

	public void setMobile(boolean isMobile) {
		this.isMobile = isMobile;
	}

	public boolean isAndroid() {
		return isAndroid;
	}

	public boolean isIsiOS() {
		return isiOS;
	}

	public boolean isIPhone() {
		return isIPhone;
	}

	public boolean isIsiPad() {
		return isiPad;
	}

	public boolean isMobileWeb() {
		return isMobileWeb;
	}

	public boolean isAndroidWeb() {
		return isAndroidWeb;
	}

	public boolean isIsiPhoneWeb() {
		return isiPhoneWeb;
	}

	public boolean isIsiPadWeb() {
		return isiPadWeb;
	}

	public boolean isWeb() {
		return isWeb;
	}

	public boolean isChrome() {
		return isChrome;
	}

	public boolean isFirefox() {
		return isFirefox;
	}

	public boolean isIe() {
		return isIe;
	}

	public boolean isEdge() {
		return isEdge;
	}

	public boolean isSafari() {
		return isSafari;
	}

	public boolean isOpera() {
		return isOpera;
	}

	public boolean isLocal() {
		return isLocal;
	}

	public boolean isGrid() {
		return isGrid;
	}


	public void setDriver(DesiredCapabilities dc) throws HybridException {
		initializeEligibility();
		intializeTestData();
		initalize();
		setMobileTool(dc);
		setMobileWebTool(dc);
		setWebTool(dc);
		setAPI(dc);
		setHost(dc);
		setThreadName(dc);
	}
	
	private void setThreadName(DesiredCapabilities dc) throws HybridException {
		this.threadName = (String) dc.getCapability(ExecutionPlatform.class.getSimpleName());
	}
	
	private void setHost(DesiredCapabilities dc) throws HybridException {
		if(dc.getCapability(ExecutionPlatformHost.class.getSimpleName()).equals(PlatformHosts.GRID)) {
			isGrid = true;
		} else {
			isLocal = true;
		}
	}

	private void setAPI(DesiredCapabilities dc) throws HybridException {
		if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(ExecutionPlatform.API.getExecutionPlatform())) {
			isAPI = true;
		}
	}

	private void setWebTool(DesiredCapabilities dc) throws HybridException {
		if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.CHROME.getExecutionPlatform())) {
			isWeb = true;
			isChrome = true;
			currentPlatform = WebExecutionPlatform.CHROME.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.WEB_TOOL.getExecutionToolValue()), dc);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.FIREFOX.getExecutionPlatform())) {
			isWeb = true;
			isFirefox = true;
			currentPlatform = WebExecutionPlatform.FIREFOX.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.WEB_TOOL.getExecutionToolValue()), dc);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.IE.getExecutionPlatform())) {
			isWeb = true;
			isIe = true;
			currentPlatform = WebExecutionPlatform.IE.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.WEB_TOOL.getExecutionToolValue()), dc);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.SAFARI.getExecutionPlatform())) {
			isWeb = true;
			isSafari = true;
			currentPlatform = WebExecutionPlatform.SAFARI.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.WEB_TOOL.getExecutionToolValue()), dc);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.EDGE.getExecutionPlatform())) {
			isWeb = true;
			isEdge = true;
			currentPlatform = WebExecutionPlatform.EDGE.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.WEB_TOOL.getExecutionToolValue()), dc);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.OPERA.getExecutionPlatform())) {
			isWeb = true;
			isOpera = true;
			currentPlatform = WebExecutionPlatform.OPERA.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.WEB_TOOL.getExecutionToolValue()), dc);
		}
	}

	private void setMobileWebTool(DesiredCapabilities dc) throws HybridException {
		if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(MobileExecutionPlatform.ANDROID_WEB.getExecutionPlatform())) {
			isMobileWeb = true;
			isAndroidWeb = true;
			currentPlatform = MobileExecutionPlatform.ANDROID_WEB.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.MOBILE_WEB_TOOL.getExecutionToolValue()), dc);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(MobileExecutionPlatform.IPHONE_WEB.getExecutionPlatform())) {
			isMobileWeb = true;
			isiOS = true;
			isiPhoneWeb = true;
			currentPlatform = MobileExecutionPlatform.IPHONE_WEB.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.MOBILE_WEB_TOOL.getExecutionToolValue()), dc);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(MobileExecutionPlatform.IPAD_WEB.getExecutionPlatform())) {
			isMobileWeb = true;
			isiOS = true;
			isiPadWeb = true;
			currentPlatform = MobileExecutionPlatform.IPAD_WEB.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.MOBILE_WEB_TOOL.getExecutionToolValue()), dc);
		}
	}

	private void setMobileTool(DesiredCapabilities dc) throws HybridException {
		if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(MobileExecutionPlatform.ANDROID.getExecutionPlatform())) {
			isMobile = true;
			isAndroid = true;
			currentPlatform = MobileExecutionPlatform.ANDROID.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.MOBILE_TOOL.getExecutionToolValue()), dc);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(MobileExecutionPlatform.IPHONE.getExecutionPlatform())) {
			isMobile = true;
			isiOS = true;
			isIPhone = true;
			currentPlatform = MobileExecutionPlatform.IPHONE.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.MOBILE_TOOL.getExecutionToolValue()), dc);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(MobileExecutionPlatform.IPAD.getExecutionPlatform())) {
			setTool(Tools.valueOf(ExecutionTool.MOBILE_TOOL.getExecutionToolValue()), dc);
			isMobile = true;
			isiOS = true;
			isiPad = true;
			currentPlatform = MobileExecutionPlatform.IPAD.getExecutionPlatform();
			setTool(Tools.valueOf(ExecutionTool.MOBILE_TOOL.getExecutionToolValue()), dc);
		}
	}

	private void setTool(Tools tool, DesiredCapabilities dc) throws HybridException {

		switch (tool) {

		case APPIUMSTUDIO:
			if (isMobile()) {
				driver = new HybridAppiumStudioMobileAPI(dc);
			} else if (isMobileWeb()) {
				driver = new HybridAppiumStudioMobileWebAPI(dc);
			}
			break;
		case SEETEST:
			driver = new HybridSeetestAPI(dc);
			break;
		case SELENIUM:
			driver = new HybridSeleniumAPI(dc);
			break;
		case APPIUM:
			if (isMobile()) {
				driver = new HybridAppiumMobileAPI(dc);
			} else if (isMobileWeb()) {
				driver = new HybridAppiumMobileWebAPI(dc);
			}
			break;
		case PERFECTO:
			break;
		case HEADSPIN:
			break;
		case SAUCELABS:
			break;
		case PCLOUDY:
			break;
		default:
			throw new HybridException("Add new tool configuration");
		}
	}
	
	
	protected String getObject(String screenName, String objectName, String platform) throws HybridException  {
		return super.getObject(screenName, objectName, platform);
	}
	
	public String getObject(String screenName, String objectName) throws HybridException  {
		return getObject(screenName, objectName, getCurrentPlatform());
	}
	
	public String getObject(String screenName, String objectName, String... varArgs) throws HybridException  {
		String object =  getObject(screenName, objectName);
		for(String arg : varArgs) {
			object = String.format(object, arg);
		}
		return object;
	}
	

}
