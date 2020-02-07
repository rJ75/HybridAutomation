package hybrid.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;


public class Untitled {
	
	 private String reportDirectory = "reports";
	    private String reportFormat = "xml";
	    private String testName = "Untitled";
	    protected AndroidDriver<AndroidElement> driver = null;
	
	public static void main(String[] args) {
		Untitled dc = new Untitled();
		try {
			dc.setUp();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dc.testUntitled();
		dc.tearDown();
		 
	}
   

   
   
    public void setUp() throws MalformedURLException {
    	 DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "J7AAGF080730ZA2");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    public void testUntitled() {
        driver.installApp("com.experitest.ExperiBank/.LoginActivity");
    }

    public void tearDown() {
        driver.quit();
    }
}