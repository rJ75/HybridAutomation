package hybrid.base;

public interface HybridMobileWebBase extends HybridCoreBase {
	
	boolean click(String object);
	
	boolean isDisplayed(String object);
	
	void clearText(String object);
	
	boolean isEnabled(String object);
	
	boolean isSelected(String object);
	
	String getProperty(String object, String property);
	
	void sendText(String object, String textToSend);
	
	String getText(String object);

	String getDeviceTime();
	
	void typeThroughKeyboard(String keysToSend);
	
	String getCurrentUrl();
	
	String getPageSource();
	
	String getPageTitle();
	
	void closeKeyboard();
	
	void setTimeOut(long timeInMilliSecond);
	
	void setContext();
	
	void getUrl(String url);

}
