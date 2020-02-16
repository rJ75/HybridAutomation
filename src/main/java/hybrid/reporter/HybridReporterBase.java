package hybrid.reporter;

public interface HybridReporterBase {
	
	void stepPass(String reportText);
	
	void stepFail(String reportText);
	
	void stepException(String reportText);
	
	void stepWarning(String reportText);
	
	void stepInfo(String reportText);
	
	void stepError(String reportText);
	
	void stepSkip(String reportText);
	
	void teststart(String reportText);
	
	void testEnd();
	

}
