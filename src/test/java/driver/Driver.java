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

    private static WebDriver driver;

    private Driver() {
    }

    public static void closeBrowser() {
        driver.quit();
        driver = null;
    }


    public static WebDriver getDriver() {
        if (driver == null) {
            init();
        }
        return driver;
    }

    private static void init() {
        TestProperties.getProperties();

        switch (System.getProperty("browser")) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("chromeDriverPath"));
                driver = new ChromeDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", System.getProperty("IEDriverPath"));
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new AssertionError("Unsupported browser: " + System.getProperty("browser"));
        }
        driver.manage().timeouts().implicitlyWait(Long.parseLong(System.getProperty("implicityWait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(System.getProperty("pageLoadWait")), TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(Long.parseLong(System.getProperty("scriptWait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
