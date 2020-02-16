package hybrid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import hybrid.reporter.HybridExtentReporter;

public class HybridReporter extends HybridExtentReporter {

	private static FileInputStream fileInputStreamReader;

	public HybridReporter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private static String encodeFileToBase64Binary(File file) throws HybridException {
		
		String encodedfile = null;
		try {
			fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			throw new HybridException(e, "Exception while encoding file");
		}
		return encodedfile;
	}
	
	public static void stepFailAndAttachScreenShot(String reportText, File file) throws HybridException, Exception {
		String EncodedString = encodeFileToBase64Binary(file);
		logger.fail("details", MediaEntityBuilder.createScreenCaptureFromBase64String(EncodedString).build());
	}
	
	public static void stepPassAndAttachScreenShot(String reportText, File file) throws HybridException, Exception {
		String EncodedString = encodeFileToBase64Binary(file);
		logger.pass("details", MediaEntityBuilder.createScreenCaptureFromBase64String(EncodedString).build());
	}
	
	public static void stepFatalAndAttachScreenShot(String reportText, File file) throws HybridException, Exception {
		String EncodedString = encodeFileToBase64Binary(file);
		logger.fatal("details", MediaEntityBuilder.createScreenCaptureFromBase64String(EncodedString).build());
	}
	
	public static void stepWarningAndAttachScreenShot(String reportText, File file) throws HybridException, Exception {
		String EncodedString = encodeFileToBase64Binary(file);
		logger.warning("details", MediaEntityBuilder.createScreenCaptureFromBase64String(EncodedString).build());
	}
	
	public static void stepErrorAndAttachScreenShot(String reportText, File file) throws HybridException, Exception {
		String EncodedString = encodeFileToBase64Binary(file);
		logger.warning("details", MediaEntityBuilder.createScreenCaptureFromBase64String(EncodedString).build());
	}
	
	public static void stepInfoAndAttachScreenShot(String reportText, File file) throws HybridException, Exception {
		String EncodedString = encodeFileToBase64Binary(file);
		logger.warning("details", MediaEntityBuilder.createScreenCaptureFromBase64String(EncodedString).build());
	}

}
