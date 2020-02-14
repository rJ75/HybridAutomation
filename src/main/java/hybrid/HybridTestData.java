package hybrid;

import java.io.File;
import java.sql.ResultSet;
import java.util.Arrays;

import hybrid.HybridConstants.Applicaton;
import hybrid.HybridConstants.COMMON_OBJECTS;
import hybrid.HybridConstants.DBConfig;
import hybrid.HybridConstants.MobileExecutionPlatform;
import hybrid.HybridConstants.TestDataOption;
import hybrid.HybridConstants.TestDataSources;
import hybrid.HybridConstants.WebExecutionPlatform;
import hybrid.datahandler.HybridDBHandler;
import hybrid.datahandler.HybridPropertyFileHandler;

public class HybridTestData {

	private static HybridPropertyFileHandler testDataPropertyReader;
	private static String dbPropertyFileName;
	private HybridDBHandler dbHandler;
	private String testDataSource;
	private ResultSet result;

	public HybridTestData() throws HybridException {

		intializeTestData();
	}

	private void intializeTestData() throws HybridException {

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
		DBConfig.TEST_QUERY.setDBConfigOptionValue(testDataPropertyReader.getProperty(DBConfig.TEST_QUERY.getDBConfigOption()));
	}

	private void connectToDB() throws HybridException {
		dbHandler = new HybridDBHandler();
		dbHandler.connect(DBConfig.DRIVER_CLASS.getDBConfigOptionValue(), DBConfig.DB_URL.getDBConfigOptionValue(),
				DBConfig.DB_USERNAME.getDBConfigOptionValue(), DBConfig.DB_PASSWORD.getDBConfigOptionValue());
	}

	public void getObject(String screenName, String objectName, String platform) throws HybridException {

		if (testDataSource.equals(TestDataSources.DB.name())) {
			getObjectFromDB(screenName, objectName, platform);
		} else if (testDataSource.equals(TestDataSources.YAML.name())) {

		} else if (testDataSource.equals(TestDataSources.XML.name())) {

		} else if (testDataSource.equals(TestDataSources.JSON.name())) {

		} else if (testDataSource.equals(TestDataSources.EXCEL.name())) {

		} else if (testDataSource.equals(TestDataSources.CSV.name())) {

		} else if (testDataSource.equals(TestDataSources.PROPERTIES.name())) {

		}
	}

	protected String getObjectFromDB(String screenName, String objectName, String platform) throws HybridException {

		String object = null;
		try {
			String query = String.format("%s %s %s %s %s", "SELECT * FROM",
					DBConfig.OBJECT_REPOSITORY_NAME.getDBConfigOptionValue(), "WHERE SCREEN =", screenName,
					"AND OBJECT =", objectName);
			result = dbHandler.executeQuery(query);
			if (result.isLast()) {
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
			} else {
				throw new HybridException("Duplicate Objects in DB");
			}
		} catch (Exception e) {
			throw new HybridException(e, "Exception in TestData.getObjectFromDB()");
		}
		return object;
	}
	
	protected Object getTests() throws HybridException {
		
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
		
		if(DBConfig.TEST_QUERY.getDBConfigOptionValue().isEmpty() || DBConfig.TEST_QUERY.getDBConfigOptionValue() == null) {
			query = String.format("%s %s", "SELECT * FROM", DBConfig.TEST_REPOSITORY_NAME.getDBConfigOptionValue());
		} else {
			query = DBConfig.TEST_QUERY.getDBConfigOptionValue();
		}
		
		return getTestsFromDB(query);
	}
	
	

}
