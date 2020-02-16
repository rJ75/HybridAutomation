package hybrid.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import hybrid.HybridConstants.WebExecutionPlatform;

// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

public class Test {
    public static void main(String[] args) {
//    	System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
    	System.setProperty("webdriver.gecko.driver", "Drivers" + File.separator + "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/test/newtours/");
    }
		
    
    

}