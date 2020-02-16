package hybrid.toolsAPI;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import hybrid.HybridConstants.ExecutionPlatform;
import hybrid.HybridConstants.Report;
import hybrid.HybridConstants.WebExecutionPlatform;
import hybrid.HybridException;
import hybrid.HybridReporter;
import hybrid.base.HybridWebBase;

public class HybridSeleniumAPI extends HybridReporter implements HybridWebBase {

	DesiredCapabilities dc = new DesiredCapabilities();
	protected WebDriver driver = null;
	private FileInputStream fileInputStream;

	public HybridSeleniumAPI(DesiredCapabilities dc) throws HybridException {
		this.dc = dc;
		setCapabilities();
		setDriver();
		setTimeOut(10000);
	}

	private void setDriver() throws HybridException {
		
		try {
			if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
					.equals(WebExecutionPlatform.CHROME.getExecutionPlatform())) {
				
				System.setProperty("webdriver.chrome.driver", "Drivers" + File.separator + "chromedriver.exe");
				driver = new ChromeDriver();
			} else if(dc.getCapability(ExecutionPlatform.class.getSimpleName())
					.equals(WebExecutionPlatform.FIREFOX.getExecutionPlatform())) {
				System.setProperty("webdriver.gecko.driver", "Drivers" + File.separator + "geckodriver.exe");
				driver = new FirefoxDriver();
			} else if(dc.getCapability(ExecutionPlatform.class.getSimpleName())
					.equals(WebExecutionPlatform.IE.getExecutionPlatform())) {
				System.setProperty("webdriver.ie.driver", "Drivers" + File.separator + "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else if(dc.getCapability(ExecutionPlatform.class.getSimpleName())
					.equals(WebExecutionPlatform.SAFARI.getExecutionPlatform())) {
				driver = new ChromeDriver();
			} else if(dc.getCapability(ExecutionPlatform.class.getSimpleName())
					.equals(WebExecutionPlatform.EDGE.getExecutionPlatform())) {
				
			} else if(dc.getCapability(ExecutionPlatform.class.getSimpleName())
					.equals(WebExecutionPlatform.OPERA.getExecutionPlatform())) {
				
			}
			driver.manage().window().maximize();
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
				.equals(WebExecutionPlatform.CHROME.getExecutionPlatform())) {
			dc.setCapability(CapabilityType.BROWSER_NAME, WebExecutionPlatform.CHROME.getExecutionPlatform().toLowerCase());
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.FIREFOX.getExecutionPlatform())) {
			dc.setCapability(CapabilityType.BROWSER_NAME, WebExecutionPlatform.FIREFOX.getExecutionPlatform().toLowerCase());
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.IE.getExecutionPlatform())) {
			dc.setCapability(CapabilityType.BROWSER_NAME, WebExecutionPlatform.IE.getExecutionPlatform().toLowerCase());
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.SAFARI.getExecutionPlatform())) {
			dc.setCapability(CapabilityType.BROWSER_NAME, WebExecutionPlatform.SAFARI.getExecutionPlatform().toLowerCase());
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.EDGE.getExecutionPlatform())) {
			dc.setCapability(CapabilityType.BROWSER_NAME, WebExecutionPlatform.EDGE.getExecutionPlatform().toLowerCase());
		} else if (dc.getCapability(ExecutionPlatform.class.getSimpleName())
				.equals(WebExecutionPlatform.OPERA.getExecutionPlatform())) {
			dc.setCapability(CapabilityType.BROWSER_NAME, WebExecutionPlatform.OPERA.getExecutionPlatform().toLowerCase());
		}

	}



	public boolean click(String object) {

		boolean status = false;
		try {
			driver.findElement(By.xpath(object));
			status = true;
			stepPass("Object clicked. Object : " + object);
		} catch (Exception e) {
			stepException("Exception while clicking object :" + object);
		}
		return status;
	}

	public boolean isDisplayed(String object) {

		boolean status = false;
		try {
			status = driver.findElement(By.xpath(object)).isDisplayed();
			stepPass("Object is displayed. Object : " + object);
		} catch (Exception e) {
			stepException("Exception while checking element displayed or not. Object : " + object);
		}
		return status;
	}

	public void clearText(String object) {

		try {
			driver.findElement(By.xpath(object)).clear();
			stepPass("Object text is cleared. Object : " + object);
		} catch (Exception e) {
			stepException("Exception while clearing text. Object : " + object);
		}
	}

	public boolean isEnabled(String object) {

		boolean status = false;
		try {
			status = driver.findElement(By.xpath(object)).isEnabled();
			stepPass("Object is enabled. Object : " + object);
		} catch (Exception e) {
			stepException("Exception while checking object is enabled. Object : " + object);
		}
		return status;
	}

	public boolean isSelected(String object) {

		boolean status = false;
		try {
			status = driver.findElement(By.xpath(object)).isSelected();
			stepPass("Object is selected. Object : " + object);
		} catch (Exception e) {
			stepException("Exception while checking object is selected. Object : " + object);
		}
		return status;
	}

	public String getProperty(String object, String property) {

		String propertyValue = null;
		try {
			propertyValue = driver.findElement(By.xpath(object)).getAttribute(property);
			stepPass("Object propery extracted. Object : " + object + ", Property : " + property);
		} catch (Exception e) {
			stepException(
					"Exception while getting attribute of a property. Object : " + object + ", Property : " + property);
		}
		return propertyValue;
	}

	public void sendText(String object, String textToSend) {

		try {
			driver.findElement(By.xpath(object)).sendKeys(textToSend);
			stepPass("Text : " + textToSend + " is send to Object : " + object);
		} catch (Exception e) {
			stepException(
					"Exception while sending text to an object. Object : " + object + ", TextToSend : " + textToSend);
		}
	}

	public String getText(String object) {

		String text = null;
		try {
			text = driver.findElement(By.xpath(object)).getText();
			stepPass("Object text is extracted. Object : " + object + ", Text Extracted : " + text);
		} catch (Exception e) {
			stepException(
					"Exception while getting text from the object. Object : " + object + ", Text Extracted : " + text);
		}
		return text;
	}

	public String getCurrentUrl() {

		String url = null;
		try {
			url = driver.getCurrentUrl();
			stepPass("Getting current url on device. url : " + url);
		} catch (Exception e) {
			stepException("Exception while getting url on device.");
		}
		return url;
	}

	public String getPageSource() {

		String pageSource = null;
		try {
			pageSource = driver.getPageSource();
			stepPass("Getting current page source on device. url : " + pageSource);
		} catch (Exception e) {
			stepException("Exception while getting page source on device.");
		}
		return pageSource;
	}

	public String getPageTitle() {

		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
			stepPass("Getting current page title on device. url : " + pageTitle);
		} catch (Exception e) {
			stepException("Exception while getting page title on device.");
		}
		return pageTitle;
	}

	public void setTimeOut(long timeInMilliSecond) {

		try {
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			stepException("Exception while setting the time out for commands.");
		}
	}

	public void getUrl(String url) {

		try {
			driver.get(url);
			stepPass("Getting url on device. url : " + url);
		} catch (Exception e) {
			stepException("Exception while getting url on device.");
		}
	}
	
	public void setPageLoadTimeout(long timeInMilliSecond) {

		try {
			driver.manage().timeouts().pageLoadTimeout(timeInMilliSecond, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			stepException("Exception while setting timeout for page load.");
		}
	}
	
	public void setFullScreenWindow() {

		try {
			driver.manage().window().fullscreen();
			stepPass("Window set to full screen");
		} catch (Exception e) {
			stepException("Exception while setting full screen window.");
		}
	}
	
	public void maximizeWindow() {

		try {
			driver.manage().window().maximize();
			stepPass("Window is maximized");
		} catch (Exception e) {
			stepException("Exception while maximizing window.");
		}
	}
	

	public String takeScreenshotAndEncodeToString() {
		String encodedFile;

		try {
			File screenCaptureFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			fileInputStream = new FileInputStream(screenCaptureFile);
			byte[] encodedBytes = new byte[(int) screenCaptureFile.length()];
			fileInputStream.read(encodedBytes);
			encodedFile = new String(Base64.encodeBase64(encodedBytes), "UTF-8");
		} catch (Exception ex) {
			encodedFile = "";
		}
		return encodedFile;
	}

}
