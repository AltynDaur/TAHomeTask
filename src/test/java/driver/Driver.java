package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dauren_Altynbekov on 8/14/2015.
 */
public class Driver {

    public static final String CHROMEDRIVER_PATH = "chromedriver.exe";
    public static final String IEDRIVER_PATH = "IEDriverServer.exe";
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getFirefoxDriverInstance() {
        if (driver == null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public static void closeBrowser() {
        driver.quit();
        driver = null;
    }


    public static WebDriver getDriver() {
        if (driver == null) {
            init();
        }
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        return driver;
    }

    private static void init() {
        Properties prop = new Properties();
        FileInputStream propFile;
        try {
            propFile = new FileInputStream("test.properties");
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
        switch (System.getProperty("browser")) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
                driver = new ChromeDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", IEDRIVER_PATH);
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new AssertionError("Unsupported browser: " + System.getProperty("browser"));
        }

    }
}
