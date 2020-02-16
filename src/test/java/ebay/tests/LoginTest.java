package ebay.tests;

import org.testng.annotations.Test;

import ebay.testslogicsupports.LoginTestSupport;
import hybrid.HybridException;

public class LoginTest {
	
	
	/**
	 * Test to login to the app
	 * @author roshan.james
	 * @throws HybridException 
	 */
	@Test
	public void Test01() throws HybridException {

		LoginTestSupport loginTestSupport = new LoginTestSupport();
		loginTestSupport.loginToApp();
		
	}

}
