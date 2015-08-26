package driver.driverCreator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class ChromeDriverCreator extends WebDriverCreator {
    @Override
    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("chromeDriverPath"));
        return new ChromeDriver();
    }
}
