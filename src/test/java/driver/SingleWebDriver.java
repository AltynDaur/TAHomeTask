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
        if (driver == null) {
            switch (driverName) {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    createAndStartChromeService();
                    driver = new ChromeDriver((ChromeDriverService) service);
                    break;
                case "ie":
                    createAndStartIEService();
                    driver = new InternetExplorerDriver((InternetExplorerDriverService) service);
            }
        }
        return driver;
    }

    public static void createAndStartChromeService() {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(CHROMEDRIVER_PATH))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createAndStartIEService() {
        service = new InternetExplorerDriverService.Builder()
                .usingDriverExecutable(new File(IEDRIVER_PATH))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void stopService() {
        service.stop();
    }
}
