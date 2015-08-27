package pages;

import driver.Driver;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.util.PageUtil;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
public abstract class Page extends PageObject {

    protected WebDriver driver;


    public void takeScreenShot() {
        PageUtil.takeScreenShot(driver);
    }

    public void highlightElement(WebElement element) {
        PageUtil.highlightElement(driver, element);
    }
}
