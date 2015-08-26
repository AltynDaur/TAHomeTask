package driver.driverCreator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class IEDriverCreator extends WebDriverCreator {
    @Override
    public WebDriver getDriver() {
        System.setProperty("webdriver.ie.driver", System.getProperty("IEDriverPath"));
        return new InternetExplorerDriver();
    }
}
