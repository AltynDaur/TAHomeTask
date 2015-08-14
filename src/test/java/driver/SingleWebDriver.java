package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Dauren_Altynbekov on 8/14/2015.
 */
public class SingleWebDriver {

    private static WebDriver driver;

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
}
