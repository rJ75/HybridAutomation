package hybrid.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

import org.openqa.selenium.remote.DesiredCapabilities;

import hybrid.HybridConstants.WebExecutionPlatform;

// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
    	
		Object object = Class.forName("ebay.pages.LoginPage").newInstance();
		Method method = object.getClass().getDeclaredMethod("hi", null);
		method.invoke(object, null);
    }
    
    

}