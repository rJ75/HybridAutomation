package hybrid;

public class HybridException extends Exception {

	private static final long serialVersionUID = 1L;
	private String exceptionMessage;
	
	private String getExceptionMessage() {
		return exceptionMessage;
	}

	private void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public HybridException(String exMessage){
		super(exMessage);
		setExceptionMessage(exMessage);
		
	}
	
	public void handleException() {
		System.out.println("***************************************************************************************************");
		System.out.println("Exception occured : \n");
		System.out.println(getExceptionMessage());
		System.out.println(getMessage());
		printStackTrace();
		System.out.println("***************************************************************************************************");
		System.out.println("\n\n");
	}
	
}
