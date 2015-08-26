package driver.driverCreator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class FirefoxDriverCreator extends WebDriverCreator {
    @Override
    public WebDriver getDriver() {
        return new FirefoxDriver();
    }
}
