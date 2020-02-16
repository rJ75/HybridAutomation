package hybrid.base;

public interface HybridWebBase extends HybridCoreBase {
	
	boolean click(String object);
	
	boolean isDisplayed(String object);
	
	void clearText(String object);
	
	boolean isEnabled(String object);
	
	boolean isSelected(String object);
	
	String getProperty(String object, String property);
	
	void sendText(String object, String textToSend);
	
	String getText(String object);
	
	String getCurrentUrl();
	
	String getPageSource();
	
	String getPageTitle();
	
	void setTimeOut(long timeInMilliSecond);
	
	void getUrl(String url);
	
	void setPageLoadTimeout(long timeInMilliSecond);
	
	void maximizeWindow();
	
	void setFullScreenWindow();

}
