package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dauren_Altynbekov on 8/14/2015.
 */
public class SingleWebDriver {

    private static WebDriver driver;
    private static DesiredCapabilities capabilities = new DesiredCapabilities();

    private SingleWebDriver() {
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

    public static WebDriver getRemoteDriver() {
        capabilities.setBrowserName("firefox");
        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
