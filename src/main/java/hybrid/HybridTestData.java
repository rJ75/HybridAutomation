package hybrid;

import java.io.File;
import java.sql.ResultSet;
import java.util.Arrays;

import hybrid.HybridConstants.Applicaton;
import hybrid.HybridConstants.COMMON_OBJECTS;
import hybrid.HybridConstants.DBConfig;
import hybrid.HybridConstants.MobileExecutionPlatform;
import hybrid.HybridConstants.TestCaseData;
import hybrid.HybridConstants.TestDataOption;
import hybrid.HybridConstants.TestDataSources;
import hybrid.HybridConstants.WebExecutionPlatform;
import hybrid.datahandler.HybridDBHandler;
import hybrid.datahandler.HybridPropertyFileHandler;

public class HybridTestData extends HybridReporter {

	private static HybridPropertyFileHandler testDataPropertyReader;
	private static String dbPropertyFileName;
	private HybridDBHandler dbHandler;
	private String testDataSource;
	private ResultSet result;

	boolean isAndroidEligible, isiPhoneEligible, isiPadEligible, isAndroidWebEligible, isiPhoneWebEligible,
			isiPadWebEligible, isChromeEligible, isFirefoxEligible, isIeEligible, isSafariEligible, isEdgeEligible,
			isOperaEligible, isAPIEligible;

	protected HybridTestData() {

	}

	public void initializeEligibility() {
		isAndroidEligible = false;
		isiPhoneEligible = false;
		isiPadEligible = false;
		isAndroidWebEligible = false;
		isiPhoneWebEligible = false;
		isiPadWebEligible = false;
		isChromeEligible = false;
		isFirefoxEligible = false;
		isIeEligible = false;
		isSafariEligible = false;
		isEdgeEligible = false;
		isOperaEligible = false;
		isAPIEligible = false;
	}
	
	public String getTestCaseType() {
		return TestCaseData.TC_TYPE.getConfigOptionValue();
	}
	
	public String getTestCaseName() {
		return TestCaseData.TC_NAME.getConfigOptionValue();
	}
	
	public String getClassName() {
		return TestCaseData.CLASS_NAME.getConfigOptionValue();
	}
	
	public String getPackageName() {
		return TestCaseData.PACKAGE_NAME.getConfigOptionValue();
	}
	
	public String getPassword() {
		return TestCaseData.PASSWORD.getConfigOptionValue();
	}
	
	public String getUsername() {
		return TestCaseData.USERNAME.getConfigOptionValue();
	}

	public String getTestCaseID() {
		return TestCaseData.TC_ID.getConfigOptionValue();
	}

	public String getModule() {
		return TestCaseData.MODULE.getConfigOptionValue();
	}

	public String getSubModule() {
		return TestCaseData.SUB_MODULE.getConfigOptionValue();
	}

	public String getJSON() {
		return TestCaseData.JSON.getConfigOptionValue();
	}

	public String getALMID() {
		return TestCaseData.ALMID.getConfigOptionValue();
	}

	public void setIsAndroidEligible(boolean isAndroidEligible) {
		this.isAndroidEligible = isAndroidEligible;
	}

	public void setIsiPhoneEligible(boolean isiPhoneEligible) {
		this.isiPhoneEligible = isiPhoneEligible;
	}

	public void setIsiPadEligible(boolean isiPadEligible) {
		this.isiPadEligible = isiPadEligible;
	}

	public void setIsAndroidWebEligible(boolean isAndroidWebEligible) {
		this.isAndroidWebEligible = isAndroidWebEligible;
	}

	public void setIsiPhoneWebEligible(boolean isiPhoneWebEligible) {
		this.isiPhoneWebEligible = isiPhoneWebEligible;
	}

	public void setIsiPadWebEligible(boolean isiPadWebEligible) {
		this.isiPadWebEligible = isiPadWebEligible;
	}

	public void setIsChromeEligible(boolean isChromeEligible) {
		this.isChromeEligible = isChromeEligible;
	}

	public void setIsFirefoxEligible(boolean isFirefoxEligible) {
		this.isFirefoxEligible = isFirefoxEligible;
	}

	public void setIsIeEligible(boolean isIeEligible) {
		this.isIeEligible = isIeEligible;
	}

	public void setIsSafariEligible(boolean isSafariEligible) {
		this.isSafariEligible = isSafariEligible;
	}

	public void setIsEdgeEligible(boolean isEdgeEligible) {
		this.isEdgeEligible = isEdgeEligible;
	}

	public void setIsOperaEligible(boolean isOperaEligible) {
		this.isOperaEligible = isOperaEligible;
	}

	public void setISAPIEligible(boolean isAPIEligible) {
		this.isAPIEligible = isAPIEligible;
	}

	public boolean isAndroidEligible() {
		return isAndroidEligible;
	}

	public boolean isIsiPhoneEligible() {
		return isiPhoneEligible;
	}

	public boolean isIsiPadEligible() {
		return isiPadEligible;
	}

	public boolean isAndroidWebEligible() {
		return isAndroidWebEligible;
	}

	public boolean isIsiPhoneWebEligible() {
		return isiPhoneWebEligible;
	}

	public boolean isIsiPadWebEligible() {
		return isiPadWebEligible;
	}

	public boolean isChromeEligible() {
		return isChromeEligible;
	}

	public boolean isFirefoxEligible() {
		return isFirefoxEligible;
	}

	public boolean isIeEligible() {
		return isIeEligible;
	}

	public boolean isSafariEligible() {
		return isSafariEligible;
	}

	public boolean isEdgeEligible() {
		return isEdgeEligible;
	}

	public boolean isOperaEligible() {
		return isOperaEligible;
	}

	public boolean isAPIEligible() {
		return isAPIEligible;
	}

	protected void intializeTestData() throws HybridException {

		testDataSource = TestDataOption.TEST_DATA_SOURCE.getTestDataOptionValue();

		if (testDataSource.equals(TestDataSources.DB.name())) {
			initializeDB();
		} else if (testDataSource.equals(TestDataSources.YAML.name())) {
			initializeYAML();
		} else if (testDataSource.equals(TestDataSources.XML.name())) {
			initializeXML();
		} else if (testDataSource.equals(TestDataSources.JSON.name())) {
			initializeJSON();
		} else if (testDataSource.equals(TestDataSources.EXCEL.name())) {
			initializeExcel();
		} else if (testDataSource.equals(TestDataSources.CSV.name())) {
			initializeCSV();
		} else if (testDataSource.equals(TestDataSources.PROPERTIES.name())) {
			initializeProperties();
		}
	}
	
	public void closeTestData() throws HybridException {

		testDataSource = TestDataOption.TEST_DATA_SOURCE.getTestDataOptionValue();

		if (testDataSource.equals(TestDataSources.DB.name())) {
			closeDB();
		} else if (testDataSource.equals(TestDataSources.YAML.name())) {
			closeYAML();
		} else if (testDataSource.equals(TestDataSources.XML.name())) {
			closeXML();
		} else if (testDataSource.equals(TestDataSources.JSON.name())) {
			closeJSON();
		} else if (testDataSource.equals(TestDataSources.EXCEL.name())) {
			closeExcel();
		} else if (testDataSource.equals(TestDataSources.CSV.name())) {
			closeCSV();
		} else if (testDataSource.equals(TestDataSources.PROPERTIES.name())) {
			closeProperties();
		}
	}

	private void closeProperties() {
		// TODO Auto-generated method stub
		
	}

	private void closeCSV() {
		// TODO Auto-generated method stub
		
	}

	private void closeExcel() {
		// TODO Auto-generated method stub
		
	}

	private void closeJSON() {
		// TODO Auto-generated method stub
		
	}

	private void closeXML() {
		// TODO Auto-generated method stub
		
	}

	private void closeYAML() {
		// TODO Auto-generated method stub
		
	}

	private void closeDB() throws HybridException {
		if(dbHandler != null) {
			dbHandler.close();
		} else {
			throw new HybridException("dBHandler object is null!");
		}
	}

	private void initializeDB() throws HybridException {
		setDBConfig();
		connectToDB();
	}

	private void initializeYAML() {

	}

	private void initializeXML() {

	}

	private void initializeJSON() {

	}

	private void initializeExcel() {

	}

	private void initializeCSV() {

	}

	private void initializeProperties() {

	}

	private void setDBConfig() {
		setDBPropertyFilePath();
		testDataPropertyReader = HybridPropertyFileHandler.getPropertyHandler(dbPropertyFileName);
		setDBConfigProperties();
	}

	private void setDBPropertyFilePath() {
		if (dbPropertyFileName == null) {
			dbPropertyFileName = HybridConstants.CONFIG_PROPERTIES_FOLDER + File.separator
					+ TestDataOption.TEST_DATA_SOURCE.getTestDataOptionValue() + "_"
					+ Applicaton.AUT_NAME.getApplicationOptionValue() + HybridConstants.PROPERTIES_EXTENSION;
		}
	}

	private void setDBConfigProperties() {
		DBConfig.TEST_REPOSITORY_NAME.setDBConfigOptionValue(
				testDataPropertyReader.getProperty(DBConfig.TEST_REPOSITORY_NAME.getDBConfigOption()));
		DBConfig.OBJECT_REPOSITORY_NAME.setDBConfigOptionValue(
				testDataPropertyReader.getProperty(DBConfig.OBJECT_REPOSITORY_NAME.getDBConfigOption()));
		DBConfig.DRIVER_CLASS
				.setDBConfigOptionValue(testDataPropertyReader.getProperty(DBConfig.DRIVER_CLASS.getDBConfigOption()));
		DBConfig.DB_URL.setDBConfigOptionValue(testDataPropertyReader.getProperty(DBConfig.DB_URL.getDBConfigOption()));
		DBConfig.DB_USERNAME
				.setDBConfigOptionValue(testDataPropertyReader.getProperty(DBConfig.DB_USERNAME.getDBConfigOption()));
		DBConfig.DB_PASSWORD
				.setDBConfigOptionValue(testDataPropertyReader.getProperty(DBConfig.DB_PASSWORD.getDBConfigOption()));
		DBConfig.TEST_QUERY
				.setDBConfigOptionValue(testDataPropertyReader.getProperty(DBConfig.TEST_QUERY.getDBConfigOption()));
	}

	private void connectToDB() throws HybridException {
		dbHandler = new HybridDBHandler();
		dbHandler.connect(DBConfig.DRIVER_CLASS.getDBConfigOptionValue(), DBConfig.DB_URL.getDBConfigOptionValue(),
				DBConfig.DB_USERNAME.getDBConfigOptionValue(), DBConfig.DB_PASSWORD.getDBConfigOptionValue());
	}
	
	

	protected String getObject(String screenName, String objectName, String platform) throws HybridException {

		String object = null;
		if (testDataSource.equals(TestDataSources.DB.name())) {
			object = getObjectFromDB(screenName, objectName, platform);
		} else if (testDataSource.equals(TestDataSources.YAML.name())) {

		} else if (testDataSource.equals(TestDataSources.XML.name())) {

		} else if (testDataSource.equals(TestDataSources.JSON.name())) {

		} else if (testDataSource.equals(TestDataSources.EXCEL.name())) {

		} else if (testDataSource.equals(TestDataSources.CSV.name())) {

		} else if (testDataSource.equals(TestDataSources.PROPERTIES.name())) {

		}
		return object;
	}

	protected String getObjectFromDB(String screenName, String objectName, String platform) throws HybridException {

		String object = null;
		try {
			String query = String.format("%s %s %s%s%s%s%s", "SELECT * FROM",
					DBConfig.OBJECT_REPOSITORY_NAME.getDBConfigOptionValue(), "WHERE SCREEN ='", screenName,
					"' AND OBJECT ='", objectName, "'");
		
//		String query = "SELECT * FROM " + DBConfig.OBJECT_REPOSITORY_NAME.getDBConfigOptionValue() + " WHERE screen = '" + screenName + "' AND object = '" + objectName + "'";
		System.out.println(query);	
		result = dbHandler.executeQuery(query);
		result.next();
		object = result.getString(platform);
		if (object == null || object.isEmpty()) {
			if (platform.equalsIgnoreCase(MobileExecutionPlatform.ANDROID.getExecutionPlatform())
					|| platform.equalsIgnoreCase(MobileExecutionPlatform.IPHONE.getExecutionPlatform())
					|| platform.equalsIgnoreCase(MobileExecutionPlatform.IPAD.getExecutionPlatform())) {
				object = result.getString(COMMON_OBJECTS.MOBILE_COMMON.name());
			} else if (platform.equalsIgnoreCase(MobileExecutionPlatform.ANDROID_WEB.getExecutionPlatform())
					|| platform.equalsIgnoreCase(MobileExecutionPlatform.IPHONE_WEB.getExecutionPlatform())
					|| platform.equalsIgnoreCase(MobileExecutionPlatform.IPAD_WEB.getExecutionPlatform())) {
				object = result.getString(COMMON_OBJECTS.MOBILE_WEB_COMMON.name());
			} else if (Arrays.toString(WebExecutionPlatform.values()).contains(platform)) {
				object = result.getString(COMMON_OBJECTS.WEB_COMMON.name());
			} else if (object == null || object.isEmpty()) {
				throw new HybridException("Value is null for the given object");
			}
		}
		} catch (Exception e) {
			throw new HybridException(e, "Exception in HybridTestData.getObjectFromDB()");
		}
		return object;
	}

	public Object getTests() throws HybridException {

		Object object = null;

		if (testDataSource.equals(TestDataSources.DB.name())) {
			object = getTestsFromDB();
		} else if (testDataSource.equals(TestDataSources.YAML.name())) {

		} else if (testDataSource.equals(TestDataSources.XML.name())) {

		} else if (testDataSource.equals(TestDataSources.JSON.name())) {

		} else if (testDataSource.equals(TestDataSources.EXCEL.name())) {

		} else if (testDataSource.equals(TestDataSources.CSV.name())) {

		} else if (testDataSource.equals(TestDataSources.PROPERTIES.name())) {

		}
		return object;
	}

	protected ResultSet getTestsFromDB(String query) throws HybridException {

		return dbHandler.executeQuery(query);
	}

	protected ResultSet getTestsFromDB() throws HybridException {

		String query;

		if (DBConfig.TEST_QUERY.getDBConfigOptionValue().isEmpty()
				|| DBConfig.TEST_QUERY.getDBConfigOptionValue() == null) {
			query = String.format("%s %s", "SELECT * FROM", DBConfig.TEST_REPOSITORY_NAME.getDBConfigOptionValue());
		} else {
			query = DBConfig.TEST_QUERY.getDBConfigOptionValue();
		}

		return getTestsFromDB(query);
	}

}
