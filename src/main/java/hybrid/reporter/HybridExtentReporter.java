package hybrid.reporter;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import hybrid.HybridConstants;
import hybrid.HybridConstants.Report;

@SuppressWarnings("deprecation")
public class HybridExtentReporter implements HybridReporterBase {
	
	
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ExtentHtmlReporter reporter;
	
	public HybridExtentReporter(){
		
		reporter = new ExtentHtmlReporter(Report.REPORT_DIRECTORY.getReportOptionValue() + File.separator
				+ Report.PROJECT_BASE_DIRECTORY.getReportOptionValue() +  HybridConstants.REPORT_EXTENSION);
		configReporting();
		extent = new ExtentReports();
		extent.attachReporter(reporter);
}
	
	
	public static void configReporting() {
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().setEncoding("utf-8");
		reporter.config().setReportName("AutomationReport");
	}
	
	public void setExtentReport(ExtentReports extent) {
		
		HybridExtentReporter.extent = extent;
	}

	public ExtentReports getExtentReport() {
		
		return extent;
	}

	public void setLogger(ExtentTest logger) {
		
		HybridExtentReporter.logger = logger;
	}

	public ExtentTest getLogger() {
		
		return logger;
	}

	public void setReporter(ExtentHtmlReporter reporter) {
		
		HybridExtentReporter.reporter = reporter;
	}

	public ExtentHtmlReporter getReporter() {
		
		return reporter;
	}

	@Override
	public void stepPass(String reportText) {
		
		logger.log(Status.PASS, reportText);
	}

	@Override
	public void stepFail(String reportText) {
		
		logger.log(Status.FAIL, reportText);
	}

	@Override
	public void stepException(String reportText) {
		
		logger.log(Status.FATAL, reportText);
	}

	@Override
	public void stepWarning(String reportText) {
		
		logger.log(Status.WARNING, reportText);
	}

	@Override
	public void stepInfo(String reportText) {
		logger.log(Status.INFO, reportText);
	}
	
	@Override
	public void stepError(String reportText) {
		
		logger.log(Status.ERROR, reportText);
	}

	@Override
	public void stepSkip(String reportText) {

		logger.log(Status.SKIP, reportText);
	}
	
	public void stepDebug(String reportText) {
		
		logger.log(Status.DEBUG, reportText);
	}
	
	@Override
	public void teststart(String testName) {
		logger = extent.createTest(testName);
	}

	@Override
	public void testEnd() {
		extent.flush();
	}

}
