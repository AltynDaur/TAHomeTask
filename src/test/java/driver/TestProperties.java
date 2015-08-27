package driver;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Dauren_Altynbekov on 8/24/2015.
 */
public class TestProperties {

    public static void getProperties() {
        Properties prop = new Properties();
        FileInputStream propFile;
        try {
            propFile = new FileInputStream("src/test/resources/test.properties");
            prop.load(propFile);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Enumeration<String> enums = (Enumeration<String>) prop.propertyNames();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            System.setProperty(key, prop.getProperty(key));
        }
    }
}
