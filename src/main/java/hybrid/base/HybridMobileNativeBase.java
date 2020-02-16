package hybrid.base;

public interface HybridMobileNativeBase extends HybridCoreBase {
	
	boolean click(String object);
	
	boolean isDisplayed(String object);
	
	void clearText(String object);
	
	boolean isEnabled(String object);
	
	boolean isSelected(String object);
	
	String getProperty(String object, String property);
	
	void sendText(String object, String textToSend);
	
	String getText(String object);

	void closeApp();
	
	String getDeviceTime();
	
	void typeThroughKeyboard(String keysToSend);
	
	boolean uninstallApp(String appBundleID);
	
	void resetApp();
	
	void launchApp();
	
	void closeKeyboard();
	
	void installApp(String appPath);
	
	void killApp(String appBundleId);
	
	void setTimeOut(long timeInMilliSecond);
	
	void setContext();
}
