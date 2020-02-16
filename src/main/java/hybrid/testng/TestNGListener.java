package hybrid.testng;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.IAlterSuiteListener;
import org.testng.IInvokedMethod;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import hybrid.HybridBasePage;
import hybrid.HybridConfigurations;
import hybrid.HybridException;
import hybrid.base.HybridCoreBase;
import hybrid.nativexe.HybridTestRunner;

public class TestNGListener implements ITestListener, ISuiteListener, IInvokedMethod, IAlterSuiteListener{

	@Override
	public boolean isTestMethod() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConfigurationMethod() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITestNGMethod getTestMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITestResult getTestResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getDate() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void onTestStart() {
		
	}
	
	public void onTestSuccess() {
		
	}
	
	public void onTestSkipped() {
		
	}
	HybridBasePage basePage ;
	public void beforeInvocation() {
		
		HybridConfigurations hybridConfigurations;
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			hybridConfigurations = HybridConfigurations.getInstance();
			List<Map<String, String>> capability = hybridConfigurations.getCapability();
			Map<String,String> singleCapability = capability.get(0);
			for (Map.Entry<String, String> entry : singleCapability.entrySet()) {
				dc.setCapability(entry.getKey(), entry.getValue());
			}
			HybridCoreBase driver = null;
			basePage = null;
			basePage = HybridBasePage.getHybridBasePage();
			basePage.setDriver(dc);
			driver = basePage.getDriver();
			ResultSet tests = (ResultSet) basePage.getTests();
			try {
				while(tests.next()) {
					HybridTestRunner runner = new HybridTestRunner(singleCapability);
					runner.setTestData(tests);
					basePage.teststart(basePage.getCurrentPlatform() + " ~ " + basePage.getALMID() +" ~ " +  basePage.getTestCaseID() + 
							" ~ " + basePage.getTestCaseName());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (HybridException e) {
			e.printStackTrace();
		}
	}
	
	public void afterInvocation() {
		basePage.testEnd();
	}

	
	
	
	
}
