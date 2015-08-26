package pages.util;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class PageUtil {
    private static Logger logger = Logger.getLogger(PageUtil.class);

    public static void takeScreenShot(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFileToDirectory(screenshot, new File("screenshots"));
            logger.info("Screenshot saved");
        } catch (IOException e) {
            logger.error("Can't copy screenshot to directory: screenshots");
        }
    }
}
