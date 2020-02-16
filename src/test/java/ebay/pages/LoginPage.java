package ebay.pages;

import ebay.objects.LoginObjects;
import hybrid.HybridBasePage;
import hybrid.HybridException;
import hybrid.base.HybridCoreBase;

public class LoginPage {
	
	HybridBasePage basePage;
	HybridCoreBase driver;
	
	public LoginPage(){
		basePage = HybridBasePage.getHybridBasePage();
		driver = basePage.getDriver();
	}

	public void clickUsername() throws HybridException {
		driver.click(basePage.getObject(LoginObjects.LOGIN_SCREEN, LoginObjects.USERNAME));
	}
	
	public void clickPassword() throws HybridException {
		driver.click(basePage.getObject(LoginObjects.LOGIN_SCREEN, LoginObjects.PASSWORD));
	}
	
	public void enterUsername(String username) throws HybridException {
		driver.sendText(basePage.getObject(LoginObjects.LOGIN_SCREEN, LoginObjects.USERNAME), username);
	}
	
	public void entePassword(String password) throws HybridException {
		driver.sendText(basePage.getObject(LoginObjects.LOGIN_SCREEN, LoginObjects.PASSWORD), password);
	}
	
	public void clickLoginButton() throws HybridException {
		driver.click(basePage.getObject(LoginObjects.LOGIN_SCREEN, LoginObjects.LOGIN_BUTTON));
	}
	
	public void enterCredentials(String username, String password) throws HybridException {
		clickUsername();
		enterUsername(username);
		clickPassword();
		entePassword(password);
	}
	
	
	


}
