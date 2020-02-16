package hybrid.toolsAPI;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import hybrid.HybridConstants.AppiumStudioConfig;
import hybrid.HybridConstants.ExecutionPlatform;
import hybrid.HybridConstants.MobileExecutionPlatform;
import hybrid.HybridConstants.Report;
import hybrid.HybridException;
import hybrid.HybridReporter;
import hybrid.base.HybridMobileWebBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;

public class HybridAppiumStudioMobileWebAPI  extends HybridReporter implements HybridMobileWebBase {
	DesiredCapabilities dc = new DesiredCapabilities();
	protected AppiumDriver<WebElement> driver = null;

	public HybridAppiumStudioMobileWebAPI(DesiredCapabilities dc) throws HybridException  {
		this.dc = dc;
		setCapabilities();
		setDriver();
		setTimeOut(10000);
		setContext();
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
			dc.setBrowserName(MobileBrowserType.CHROME);
		} else if(dc.getCapability(ExecutionPlatform.class.getSimpleName()).equals(MobileExecutionPlatform.IPHONE.getExecutionPlatform())) {
			dc.setCapability("attach.crash.log.to.report", true);
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, MobileExecutionPlatform.IPHONE.getExecutionPlatform());
			dc.setBrowserName(MobileBrowserType.SAFARI);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName()).equals(MobileExecutionPlatform.IPAD.getExecutionPlatform())) {
			dc.setCapability("attach.crash.log.to.report", true);
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, MobileExecutionPlatform.IPAD.getExecutionPlatform());
			dc.setBrowserName(MobileBrowserType.SAFARI);
		}
	}
	
	
	public void setContext() {
		if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(MobileExecutionPlatform.ANDROID.getExecutionPlatform())) {
			driver.context("CHROMIUM");
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
	
	public String getCurrentUrl() {
		
		String url = null;
		try {
			url = driver.getCurrentUrl();
			stepPass("Getting current url on device. url : " + url);
		} catch(Exception e) {
			stepException("Exception while getting url on device.");
		}
		return url;
	}
	
	public String getPageSource() {
		
		String pageSource = null;
		try {
			pageSource = driver.getPageSource();
			stepPass("Getting current page source on device. url : " + pageSource);
		} catch(Exception e) {
			stepException("Exception while getting page source on device.");
		}
		return pageSource;
	}
	
	public String getPageTitle() {
		
		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
			stepPass("Getting current page title on device. url : " + pageTitle);
		} catch(Exception e) {
			stepException("Exception while getting page title on device.");
		}
		return pageTitle;
	}
	
	public void closeKeyboard() {
		
		try {
			driver.hideKeyboard();
			stepPass("Keyboard closed successfully.");
		} catch(Exception e) {
			stepException("Exception while closing the keyboard.");
		}
	}
	
	public void setTimeOut(long timeInMilliSecond) {
		
		try {
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		} catch(Exception e) {
			stepException("Exception while setting the time out of commands.");
		}
	}
	
	public void getUrl(String url) {

		try {
			driver.get(url);
			stepPass("Getting url on device. url : " + url);
		} catch(Exception e) {
			stepException("Exception while getting url on device.");
		}
	}
}
