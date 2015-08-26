package pages;

import driver.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.util.PageUtil;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
public abstract class Page {

    protected WebDriver driver;


    public void takeScreenShot() {
        PageUtil.takeScreenShot(driver);
    }

    public void highlightElement(WebElement element) {
        String background = element.getCssValue("backgroundColor");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].style.backgroundColor = 'red'", element);
        takeScreenShot();
        executor.executeScript("arguments[0].style.backgroundColor = '" + background + "'", element);
    }
}
