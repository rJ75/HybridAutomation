package ebay.testslogicsupports;

import ebay.objects.LoginObjects;
import ebay.pages.LoginPage;
import hybrid.HybridBasePage;
import hybrid.HybridException;
import hybrid.base.HybridCoreBase;

public class LoginTestSupport {
	
	HybridBasePage basePage = HybridBasePage.getHybridBasePage();
	HybridCoreBase driver = basePage.getDriver();

	public void loginToApp() throws HybridException {
		
		LoginPage loginPage = new LoginPage();
		if(basePage.isAndroidWeb()) {
			driver.click(basePage.getObject(LoginObjects.LOGIN_SCREEN, LoginObjects.ACCOUNT_HEAD));
		}
		driver.click(basePage.getObject(LoginObjects.LOGIN_SCREEN, LoginObjects.SIGN_IN));
		loginPage.enterCredentials(basePage.getUsername(), basePage.getPassword());
		loginPage.clickLoginButton();
		driver.isDisplayed(basePage.getObject(LoginObjects.LOGIN_SCREEN, LoginObjects.HEADER));
	}

}
