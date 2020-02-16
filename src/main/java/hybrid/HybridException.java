package hybrid;

public class HybridException extends Exception {

	private static final long serialVersionUID = 1L;
	private static String exceptionMessage = "HybridException";
	
	private String getExceptionMessage() {
		return exceptionMessage;
	}

	private void setExceptionMessage(String exceptionMessage) {
		HybridException.exceptionMessage = HybridException.exceptionMessage + "\n" + exceptionMessage;
	}

	public HybridException() {
		super();
		exceptionMessage = exceptionMessage + "\n" + getMessage();
	}
	
	public HybridException(String exMessage){
		super(exMessage);
		setExceptionMessage(exMessage);
	}
	
	
	public HybridException(Exception e, String exMessage){
		super(e);
		setExceptionMessage(exMessage);
	}
	
	
	public void handleException() {
		System.err.println("\n\n***************************************************************************************************");
		System.err.println("Exception occured : \n");
		System.err.println(getExceptionMessage() + " : " + getMessage() + "\n");
		
		printStackTrace();
		System.err.println("***************************************************************************************************");
		System.out.println("\n\n");
		HybridException.exceptionMessage = null;
	}
	
}
