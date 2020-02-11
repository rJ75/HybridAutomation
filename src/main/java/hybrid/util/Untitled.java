package hybrid.util;

//package <set your test package>;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class Untitled {
	private String reportDirectory = "Reports";
	private String reportFormat = "xml";
	private String testName = "dummy";
	protected AndroidDriver<AndroidElement> driver = null;

	DesiredCapabilities dc = new DesiredCapabilities();

	public static void main(String[] args) {
		Untitled untitled = new Untitled();
		try {
			untitled.setUp();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		untitled.testdummy();
		untitled.tearDown();

	}

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		dc.setCapability("reportDirectory", reportDirectory);
		dc.setCapability("reportFormat", reportFormat);
		dc.setCapability("testName", testName);
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANdroid");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
//		dc.setBrowserName(MobileBrowserType.CHROME);

		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
				".LoginActivity");
		
		driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
		driver.setLogLevel(Level.INFO);
	}

	@Test
	public void testdummy() {
//		 driver.get("http://www.google.com");
//	        String pageSource1 = driver.getPageSource();

//		driver.installApp("com.experitest.ExperiBank/.LoginActivity");
		try {
			Thread.sleep(2000);
		} catch (Exception ignore) {
		}
		driver.startActivity(new Activity("com.experitest.ExperiBank", ".LoginActivity"));
		try {
			Thread.sleep(2000);
			MobileElement ele = null;
			ele = driver.findElementByXPath("//*[@text='OK']");
			if(ele != null) {
				driver.findElement(By.xpath("//*[@text='OK']")).click();
			}
		} catch (Exception ignore) {
		}
		
		
		
		try {
			Thread.sleep(2000);
			WebElement ele = null;
			ele =  driver.findElement(By.xpath("//*[@id='usernameTextField']"));
			if(ele != null) {
				driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("hello");
			}
		} catch (Exception ignore) {
			ignore.getMessage();
			ignore.printStackTrace();
		}
		
		try {
			Thread.sleep(2000);
		} catch (Exception ignore) {
		}
		driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("login");
		try {
			Thread.sleep(2000);
		} catch (Exception ignore) {
		}
		driver.findElement(By.xpath("//*[@id='loginButton']")).click();
		try {
			Thread.sleep(2000);
		} catch (Exception ignore) {
		}
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Close']")));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}