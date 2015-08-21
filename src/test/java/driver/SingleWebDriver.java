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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dauren_Altynbekov on 8/14/2015.
 */
public class SingleWebDriver {

    public static final String CHROMEDRIVER_PATH = "chromedriver.exe";
    public static final String IEDRIVER_PATH = "IEDriverServer.exe";
    private static WebDriver driver;
    private static DesiredCapabilities capabilities = new DesiredCapabilities();
    private static DriverService service;

    private SingleWebDriver() {
    }

    public static WebDriver getFirefoxDriverInstance() {
        if (driver == null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public static void closeBrowser() {
        stopService();
        driver.quit();
        driver = null;
    }

    public static WebDriver getRemoteDriver() {
        capabilities.setBrowserName("firefox");
        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WebDriver getDriver(String driverName) {
        switch (driverName) {
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
                throw new AssertionError("Unsupported browser: " + driverName);
        }

        return driver;
    }


    public static void stopService() {
        service.stop();
    }
}
