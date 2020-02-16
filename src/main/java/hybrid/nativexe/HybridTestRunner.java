package hybrid.nativexe;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import hybrid.HybridBasePage;
import hybrid.HybridConstants.TestCaseData;
import hybrid.HybridConstants.TestDataFields;
import hybrid.HybridException;
import hybrid.base.HybridCoreBase;

public class HybridTestRunner implements Runnable {
	
	Map<String, String> capability;
	DesiredCapabilities dc = new DesiredCapabilities();
	HybridCoreBase driver = null;
	static HybridBasePage basePage = null;
	int testCount = 0;
	
	public HybridTestRunner(Map<String, String> capability) {
		this.capability = capability;
	}

	@Override
	public void run() {

		try {
			for (Map.Entry<String, String> entry : capability.entrySet()) {
				dc.setCapability(entry.getKey(), entry.getValue());
			}
			basePage = HybridBasePage.getHybridBasePage();
			basePage.setDriver(dc);
			driver = basePage.getDriver();
			runTestCases();
		
		} catch (HybridException e) {
			e.handleException();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				basePage.closeTestData();
			} catch (HybridException e) {
				e.handleException();
			}
		}
	}
	
	private void runTestCases() throws HybridException {
		
		try {
			ResultSet tests = (ResultSet) basePage.getTests();
			
			if(tests == null) {
				throw new HybridException("Data from the test manager is null. Please check test manager.");
			} else {
				if(tests.last()) {
					testCount = tests.getRow();
					tests.beforeFirst();
				}
				while(tests.next()) {
					setTestData(tests);
					basePage.teststart(basePage.getCurrentPlatform() + " ~ " + basePage.getALMID() +" ~ " +  basePage.getTestCaseID() + 
							" ~ " + basePage.getTestCaseName());
//					Class<?> c = Class.forName(basePage.getPackageName() + "." + basePage.getClassName());
//				    Method main = c.getDeclaredMethod(basePage.getTestCaseID());
//				    main.invoke(c.newInstance());
				    
					Object object = Class.forName(basePage.getPackageName() + "." + basePage.getClassName()).newInstance();
					Method method = object.getClass().getDeclaredMethod(basePage.getTestCaseID());
					method.invoke(object);
					basePage.testEnd();
				}
			}
		} catch(Exception e) {
			throw new HybridException("Exception in HybridTestRunner.runTestCases()");
		}
	}

	public void setTestData(ResultSet tests)  throws HybridException {
		
		try {
			Map<String, String> testData = new HashMap<String, String>();
			testData.put(TestDataFields.ALMID.name(), tests.getString(TestDataFields.ALMID.name()));
			testData.put(TestDataFields.JSON.name(), tests.getString(TestDataFields.JSON.name()));
			testData.put(TestDataFields.MODULE.name(), tests.getString(TestDataFields.MODULE.name()));
			testData.put(TestDataFields.SUB_MODULE.name(), tests.getString(TestDataFields.SUB_MODULE.name()));
			testData.put(TestDataFields.TC_ID.name(), tests.getString(TestDataFields.TC_ID.name()));
			testData.put(TestDataFields.USERNAME.name(), tests.getString(TestDataFields.USERNAME.name()));
			testData.put(TestDataFields.PASSWORD.name(), tests.getString(TestDataFields.PASSWORD.name()));
			testData.put(TestDataFields.PACKAGE_NAME.name(), tests.getString(TestDataFields.PACKAGE_NAME.name()));
			testData.put(TestDataFields.CLASS_NAME.name(), tests.getString(TestDataFields.CLASS_NAME.name()));
			testData.put(TestDataFields.TC_NAME.name(), tests.getString(TestDataFields.TC_NAME.name()));
			testData.put(TestDataFields.TC_TYPE.name(), tests.getString(TestDataFields.TC_TYPE.name()));
			testData.put(TestDataFields.ANDROID_ELIGIBLE.name(), tests.getString(TestDataFields.ANDROID_ELIGIBLE.name()));
			testData.put(TestDataFields.IPHONE_ELIGIBLE.name(), tests.getString(TestDataFields.IPHONE_ELIGIBLE.name()));
			testData.put(TestDataFields.IPAD_ELIGIBLE.name(), tests.getString(TestDataFields.IPAD_ELIGIBLE.name()));
			testData.put(TestDataFields.ANDROID_WEB_ELIGIBLE.name(), tests.getString(TestDataFields.ANDROID_WEB_ELIGIBLE.name()));
			testData.put(TestDataFields.IPHONE_WEB_ELIGIBLE.name(), tests.getString(TestDataFields.IPHONE_WEB_ELIGIBLE.name()));
			testData.put(TestDataFields.IPAD_WEB_ELGIBLE.name(), tests.getString(TestDataFields.IPAD_WEB_ELGIBLE.name()));
			testData.put(TestDataFields.CHROME_ELIGIBLE.name(), tests.getString(TestDataFields.CHROME_ELIGIBLE.name()));
			testData.put(TestDataFields.FIREFOX_ELIGIBLE.name(), tests.getString(TestDataFields.FIREFOX_ELIGIBLE.name()));
			testData.put(TestDataFields.IE_ELIGIBLE.name(), tests.getString(TestDataFields.IE_ELIGIBLE.name()));
			testData.put(TestDataFields.SAFARI_ELIGIBLE.name(), tests.getString(TestDataFields.SAFARI_ELIGIBLE.name()));
			testData.put(TestDataFields.EDGE_ELIGIBLE.name(), tests.getString(TestDataFields.EDGE_ELIGIBLE.name()));
			testData.put(TestDataFields.OPERA_ELIGIBLE.name(), tests.getString(TestDataFields.OPERA_ELIGIBLE.name()));
			testData.put(TestDataFields.API_ELIGIBLE.name(), tests.getString(TestDataFields.API_ELIGIBLE.name()));
			
			TestCaseData.ALMID.setConfigOptionValue(testData.get(TestDataFields.ALMID.name()));
			TestCaseData.JSON.setConfigOptionValue(testData.get(TestDataFields.JSON.name()));
			TestCaseData.MODULE.setConfigOptionValue(testData.get(TestDataFields.MODULE.name()));
			TestCaseData.SUB_MODULE.setConfigOptionValue(testData.get(TestDataFields.SUB_MODULE.name()));
			TestCaseData.TC_ID.setConfigOptionValue(testData.get(TestDataFields.TC_ID.name()));
			TestCaseData.USERNAME.setConfigOptionValue(testData.get(TestDataFields.USERNAME.name()));
			TestCaseData.PASSWORD.setConfigOptionValue(testData.get(TestDataFields.PASSWORD.name()));
			TestCaseData.PACKAGE_NAME.setConfigOptionValue(testData.get(TestDataFields.PACKAGE_NAME.name()));
			TestCaseData.CLASS_NAME.setConfigOptionValue(testData.get(TestDataFields.CLASS_NAME.name()));
			TestCaseData.TC_NAME.setConfigOptionValue(testData.get(TestDataFields.TC_NAME.name()));
			TestCaseData.TC_TYPE.setConfigOptionValue(testData.get(TestDataFields.TC_TYPE.name()));
			basePage.setIsAndroidEligible(testData.get(TestDataFields.ANDROID_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsiPhoneEligible(testData.get(TestDataFields.IPHONE_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsiPadEligible(testData.get(TestDataFields.IPAD_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsAndroidWebEligible(testData.get(TestDataFields.ANDROID_WEB_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsiPhoneWebEligible(testData.get(TestDataFields.IPHONE_WEB_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsiPadWebEligible(testData.get(TestDataFields.IPAD_WEB_ELGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsChromeEligible(testData.get(TestDataFields.CHROME_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsFirefoxEligible(testData.get(TestDataFields.FIREFOX_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsSafariEligible(testData.get(TestDataFields.SAFARI_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsEdgeEligible(testData.get(TestDataFields.EDGE_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsIeEligible(testData.get(TestDataFields.IE_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setIsOperaEligible(testData.get(TestDataFields.OPERA_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setISAPIEligible(testData.get(TestDataFields.API_ELIGIBLE.name()).equalsIgnoreCase("Y"));
			basePage.setTestDataMap(testData);
		} catch(Exception e) {
			throw new HybridException("Exception in HybridTestRunner.setTestData()");
		}
	}
	
	

}
