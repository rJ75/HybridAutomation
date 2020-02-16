package hybrid.base;

public interface HybridCoreBase {
	
	String DEFAULT_TIMEOUT_1 = "1000";
	String DEFAULT_TIMEOUT_2 = "2000";
	String DEFAULT_TIMEOUT_3 = "3000";
	String DEFAULT_TIMEOUT_4 = "4000";
	String DEFAULT_TIMEOUT_5 = "5000";
	String DEFAULT_TIMEOUT_6 = "10000";
	String DEFAULT_TIMEOUT_7 = "20000";
	String DEFAULT_TIMEOUT_8 = "30000";
	String DEFAULT_TIMEOUT_9 = "60000";
	String DEFAULT_TIMEOUT_10 = "300000";

	
	boolean click(String object);
	
	boolean isDisplayed(String object);
	
	void clearText(String object);
	
	boolean isEnabled(String object);
	
	boolean isSelected(String object);
	
	String getProperty(String object, String property);
	
	void sendText(String object, String textToSend);
	
	String getText(String object);
	
}
