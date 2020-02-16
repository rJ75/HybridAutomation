package hybrid.toolsAPI;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import hybrid.HybridConstants.AppiumStudioConfig;
import hybrid.HybridConstants.Applicaton;
import hybrid.HybridConstants.ExecutionPlatform;
import hybrid.HybridConstants.MobileExecutionPlatform;
import hybrid.HybridConstants.Report;
import hybrid.HybridException;
import hybrid.HybridReporter;
import hybrid.base.HybridMobileNativeBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class HybridAppiumStudioMobileAPI extends HybridReporter implements HybridMobileNativeBase {

	DesiredCapabilities dc = new DesiredCapabilities();
	protected AppiumDriver<WebElement> driver = null;

	public HybridAppiumStudioMobileAPI(DesiredCapabilities dc) throws HybridException  {
		this.dc = dc;
		setCapabilities();
		setDriver();
		setTimeOut(10000);
		setContext();
	}
	
	public enum MobileAppContext {
		NATIVE_APP_INSTRUMENTED, NATIVE_APP, WEBVIEW_1;
	}
	
	public void setContext() {
		setMobileAppContext(MobileAppContext.NATIVE_APP_INSTRUMENTED);
	}

	public void setMobileAppContext(MobileAppContext nativeAppInstrumented) {
		driver.context(nativeAppInstrumented.name());
	}

	private void setDriver() throws HybridException {
		
		try {
			if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
					.equals(MobileExecutionPlatform.ANDROID.getExecutionPlatform())) {
				driver = new AndroidDriver<>(new URL(AppiumStudioConfig.LOCAL_URL.getConfigOptionValue()),dc);
			} else {
				driver = new IOSDriver<>(new URL(AppiumStudioConfig.LOCAL_URL.getConfigOptionValue()),dc);
			}
			driver.setLogLevel(Level.INFO);
		} catch(Exception e) {
			throw new HybridException(e, "Exception in setting HybridAppiumStudioMobileAPI driver");
		}
	}
	
	public void closeDriver() {
		driver.quit();
	}

	private void setCapabilities() {
		dc.setCapability("reportDirectory", Report.REPORT_DIRECTORY.getReportOptionValue());
		dc.setCapability("testName", dc.getCapability(ExecutionPlatform.class.getSimpleName()));
		
		if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(MobileExecutionPlatform.ANDROID.getExecutionPlatform())) {
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobileExecutionPlatform.ANDROID.getExecutionPlatform());
			dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Applicaton.ANDROID_APP_NAME.getApplicationOptionValue());
			dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
					Applicaton.ANDROID_APP_ACTIVITY.getApplicationOptionValue());
		} else if(dc.getCapability(ExecutionPlatform.class.getSimpleName()).equals(MobileExecutionPlatform.IPHONE.getExecutionPlatform())) {
			dc.setCapability("attach.crash.log.to.report", true);
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, MobileExecutionPlatform.IPHONE.getExecutionPlatform());
			
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName()).equals(MobileExecutionPlatform.IPAD.getExecutionPlatform())) {
			dc.setCapability("attach.crash.log.to.report", true);
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, MobileExecutionPlatform.IPAD.getExecutionPlatform());
		}
	}
	
	
	
	public boolean click(String object) {
		
		boolean status = false;
		try {
			driver.findElementByXPath(object).click();
			status = true;
			stepPass("Object clicked. Object : " + object);
		} catch(Exception e) {
			stepException("Exception while clicking object :" + object);
		}
		return status;
	}
	
	public boolean isDisplayed(String object) {
		
		boolean status = false;
		try {
			status = driver.findElementByXPath(object).isDisplayed();
			stepPass("Object is displayed. Object : " + object);
		} catch(Exception e) {
			stepException("Exception while checking element displayed or not. Object : " + object);
		}
		return status;
	}
	
	public void clearText(String object) {
		
		try {
			driver.findElementByXPath(object).clear();
			stepPass("Object text is cleared. Object : " + object);
		} catch(Exception e) {
			stepException("Exception while clearing text. Object : " + object);
		}
	}
	
	public boolean isEnabled(String object) {
		
		boolean status = false;
		try {
			status = driver.findElementByXPath(object).isEnabled();
			stepPass("Object is enabled. Object : " + object);
		} catch(Exception e) {
			stepException("Exception while checking object is enabled. Object : " + object);
		}
		return status;
	}
	
	public boolean isSelected(String object) {
		
		boolean status = false;
		try {
			status = driver.findElementByXPath(object).isSelected();
			stepPass("Object is selected. Object : " + object);
		} catch(Exception e) {
			stepException("Exception while checking object is selected. Object : " + object);
		}
		return status;
	}
	
	
	public String getProperty(String object, String property) {
		
		String propertyValue = null;
		try {
			propertyValue = driver.findElementByXPath(object).getAttribute(property);
			stepPass("Object propery extracted. Object : " + object+ ", Property : " + property);
		} catch(Exception e) {
			stepException("Exception while getting attribute of a property. Object : " + object + ", Property : " + property);
		}
		return propertyValue;
	}
	
	public void sendText(String object, String textToSend) {
		
		try {
			driver.findElementByXPath(object).sendKeys(textToSend);
			stepPass("Text : "+ textToSend + " is send to Object : " + object);
		} catch(Exception e) {
			stepException("Exception while sending text to an object. Object : " + object + ", TextToSend : " + textToSend);
		}
	}
	
	public String getText(String object) {
		
		String text = null;
		try {
			text = driver.findElementByXPath(object).getText();
			stepPass("Object text is extracted. Object : " + object + ", Text Extracted : " + text);
		} catch(Exception e) {
			stepException("Exception while getting text from the object. Object : " + object + ", Text Extracted : " + text);
		}
		return text;
	}

	public void closeApp() {
		
		try {
			driver.closeApp();
			stepPass("App closed");
		} catch(Exception e) {
			stepException("Exception while closing tte app.");
		}
	}
	
	public String getDeviceTime() {
		
		String time = null;
		try {
			time = driver.getDeviceTime();
			stepPass("Getting time on device. Time : " + time);
		} catch(Exception e) {
			stepException("Exception while getting time on device.");
		}
		return time;
	}
	
	
	@SuppressWarnings("deprecation")
	public void typeThroughKeyboard(String keysToSend) {
		
		try {
			driver.getKeyboard().sendKeys(keysToSend);
			stepPass("Sending keys through keyboard. KeysToSend : " + keysToSend);
		} catch(Exception e) {
			stepException("Exception while sending keys through keyboard. KeysToSend : " + keysToSend);
		}
	}
	
	public boolean uninstallApp(String appBundleID) {
		
		boolean status = false;
		try {
			status = driver.removeApp(appBundleID);
			stepPass("App uninstalled: " + appBundleID);
		} catch(Exception e) {
			stepException("Exception while uninstalling app : " + appBundleID);
		}
		return status;
	}
	
	public void resetApp() {
		
		try {
			driver.resetApp();
			stepPass("App reset successful.");
		} catch(Exception e) {
			stepException("Exception while resetting app.");
		}
	}
	
	public void launchApp() {
		
		try {
			driver.launchApp();
			stepPass("App launch successful.");
		} catch(Exception e) {
			stepException("Exception while launching app.");
		}
	}
	
	public void closeKeyboard() {
		
		try {
			driver.hideKeyboard();
			stepPass("Keyboard closed successfully.");
		} catch(Exception e) {
			stepException("Exception while closing the keyboard.");
		}
	}
	
	public void installApp(String appPath) {
		
		try {
			driver.installApp(appPath);
			stepPass("App installed successfully.");
		} catch(Exception e) {
			stepException("Exception while installing the app.");
		}
	}
	
	public void killApp(String appBundleId) {
		
		try {
			driver.terminateApp(appBundleId);
			stepPass("App killed successfully.");
		} catch(Exception e) {
			stepException("Exception while killing the app.");
		}
	}
	
	public void setTimeOut(long timeInMilliSecond) {
		
		try {
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		} catch(Exception e) {
			stepException("Exception while setting the app.");
		}
	}
	
	

}
