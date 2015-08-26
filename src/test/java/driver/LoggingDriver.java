package driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class LoggingDriver implements WebDriver, JavascriptExecutor, TakesScreenshot {

    private static Logger logger = Logger.getLogger(LoggingDriver.class);
    private WebDriver driver;

    public LoggingDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String s) {
        logger.info("Getting " + s);
        driver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        logger.info("Find Elements: " + by.toString());
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        logger.info("Find element: " + by.toString());
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        logger.info("Driver quit");
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        logger.info("Driver switching");
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public Object executeScript(String s, Object... objects) {
        return ((JavascriptExecutor) driver).executeScript(s, objects);
    }

    @Override
    public Object executeAsyncScript(String s, Object... objects) {
        return ((JavascriptExecutor) driver).executeAsyncScript(s, objects);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return ((TakesScreenshot) driver).getScreenshotAs(outputType);
    }
}
