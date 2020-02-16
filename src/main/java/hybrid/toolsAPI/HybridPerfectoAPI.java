package hybrid.toolsAPI;

import org.openqa.selenium.remote.DesiredCapabilities;

import hybrid.base.HybridMobileNativeBase;

public class HybridPerfectoAPI implements HybridMobileNativeBase {

	public HybridPerfectoAPI(DesiredCapabilities dc) {
		// TODO Auto-generated constructor stub
	}
//
//
//    public static void main(String[] args) throws MalformedURLException, IOException {
//        System.out.println("Run started");
//
//
//        String browserName = "mobileOS";
//        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
//        String host = "partners.perfectomobile.com";
//        String user = "roshan.james@wipro.com";
//        String pw = "sijosabu@77";
//        
//        capabilities.setCapability("user", user);
//        capabilities.setCapability("password", pw);
//        capabilities.setCapability("deviceName", "F39C49EB5D722D6DBD14CC4366FF6E44084EC96D");
//        capabilities.setCapability("automationName", "Appium");
//
//
//        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
//        setExecutionIdCapability(capabilities, host);
//        
//        // Application settings examples.
//        //capabilities.setCapability("autoLaunch", false);
//        //capabilities.setCapability("fullReset", true);
//        //capabilities.setCapability("app", "PRIVATE:applications/Errands.ipa");
//        capabilities.setCapability("bundleId", "com.gm.myCadillac");
//
//
//        // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
//        //capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);
//
//
//        // Name your script
//        // capabilities.setCapability("scriptName", "AppiumTest");
//
//
//        //AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
//        IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//
//        try {
//            WebElement alert;
//            WebElement alert1;
//            WebElement alert3;
//            driver.launchApp();
//            
//            try {
//            	//alert = driver.findElementByName("Username");
//                alert = driver.findElement(By.className("UIATextField"));
//                //alert.findElement(By.className("UIATextField")).sendKeys("omni1@gm.com");
//                alert.sendKeys("omni1@gm.com");
//                alert1 = driver.findElement(By.className("UIASecureTextField"));
//                alert1.sendKeys("Onstar123");
//                
//                alert3 = driver.findElement(By.name("Log In"));
//                alert3.click();
//                System.out.println("Clicked on logged in");
//                Thread.sleep(20000);
//                System.out.println("dummy");
//                System.out.println("Inside Home "+driver.findElementByXPath("//*[@name='Key Fob]").getText());
//                WebElement nTask = driver.findElementByName("//*[@name='Key Fob]");
//                nTask.click();
//                Thread.sleep(20000);
//                
//            
//                IOSElement a1 = (IOSElement) driver.findElementByXPath("//*[@label='Key Fob']");
//                String abc=a1.getText();
//                
//                
//                IOSElement a2 = (IOSElement) driver.findElementByXPath("//*[@label='Preferred Dealer']");
////                try {
////                     a2.scrollTo("Preferred Dealer");
////                }  catch (Exception e) {
////                } 
////                
////                IOSElement tbl = (IOSElement) driver.findElementByClassName("UIATableCell");
////                try {
////                     tbl.scrollTo("HILEY BUICK GMC OF FORT WORTH");
////                }  catch (Exception e) {
////                } 
////                
//                
//                
//                
//            }catch (NoSuchElementException n1) {
//                n1.printStackTrace();
//            	System.out.println(" NoSuchElementException Occured" + n1);
//            }catch(Exception e){
//            	e.printStackTrace();
//            	System.out.println(" Exception Occured" + e);
//            }
//           
//
//
//            // Verify that arrive at the New Task screen
//            try {
//                WebElement nTask = driver.findElementByName("2015 XTS");
//                System.out.println("Element Found!");
//            } catch (NoSuchElementException n) {
//                System.out.println("Did not reach New Task window!");
//                throw n;
//            }
//            
////            // Enter a title for the new task
////            WebElement title = driver.findElementByIosUIAutomation("UIATarget.localTarget().frontMostApp().mainWindow().textFields()[0]");
////            //WebElement title = driver.findElementByClassName("UIATextField[1]");
////            title.sendKeys("Prepare the script for Native Application");
////            
////            // Enter text into the Details field
////            WebElement detail = driver.findElementByIosUIAutomation(".textFields()[1]");
////            //WebElement detail = driver.findElementByClassName("UIATextField[2]");
////            detail.sendKeys("Select application, upload, and use Object Spy");
////            
//            // Click "Done" to go to next stage
//            driver.findElementByName("Done").click();
//            
//            // Click on the Due Date field to select the due date
//            try {
//                driver.findElementByXPath("//UIAStaticText[starts-with(@name, 'Due Date')]").click();
//            } catch (NoSuchElementException n) {
//                System.out.println("Not showing second stage display!");
//                throw n;
//            }
//            // select that task is due next week
//            driver.findElementByName("+1 Week").click();
//            
//            // Click "Done" to complete the task definition
//            driver.findElementByName("Done").click();
//            
//            // Verify that task is listed
//            try {
//                WebElement nTask = driver.findElementByName("Prepare the script for Native Application");
//            } catch (NoSuchElementException n) {
//                System.out.println("The task is not listed!");
//                throw n;
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // Retrieve the URL of the Wind Tunnel Report, can be saved to your execution summary and used to download the report at a later point
//                //String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.WIND_TUNNEL_REPORT_URL_CAPABILITY));
//
//
//                driver.close();
//
//
//                // In case you want to download the report or the report attachments, do it here.
//                //PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\report-errands");
//                // PerfectoLabUtils.downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
//                // PerfectoLabUtils.downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            driver.quit();
//        }
//
//
//        System.out.println("Run ended");
//    }
//
//
//    private static void setExecutionIdCapability(DesiredCapabilities capabilities, String host) throws IOException {
//        EclipseConnector connector = new EclipseConnector();
//        String eclipseHost = connector.getHost();
//        if ((eclipseHost == null) || (eclipseHost.equalsIgnoreCase(host))) {
//            String executionId = connector.getExecutionId();
//            capabilities.setCapability(EclipseConnector.ECLIPSE_EXECUTION_ID, executionId);
//        }
//    }


	public boolean click(String object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDisplayed(String object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clearText(String object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled(String object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSelected(String object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getProperty(String object, String property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendText(String object, String textToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getText(String object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeApp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDeviceTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void typeThroughKeyboard(String keysToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean uninstallApp(String appBundleID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetApp() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void launchApp() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void closeKeyboard() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void installApp(String appPath) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void killApp(String appBundleId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setTimeOut(long timeInMilliSecond) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setContext() {
		// TODO Auto-generated method stub
		
	}
}
