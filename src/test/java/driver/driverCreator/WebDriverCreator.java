package driver.driverCreator;

import org.openqa.selenium.WebDriver;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public abstract class WebDriverCreator {

    protected WebDriver driver;

    public abstract WebDriver getDriver();
}
