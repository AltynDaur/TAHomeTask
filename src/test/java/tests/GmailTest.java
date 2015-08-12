package tests;

import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import pages.GMailStartPage;


/**
 * Created by Dauren_Altynbekov on 8/12/2015.
 */
public class GmailTest {
    @Test
    public void oneCanLoginGmail(){
        WebDriver driver = new FirefoxDriver();
        GMailStartPage mailStartPage = new GMailStartPage(driver);
        mailStartPage.openPage();
        mailStartPage.login("autodaurtest@gmail.com","autodaurtest1");
    }
}
