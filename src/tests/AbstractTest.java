package tests;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.GmailInboxPage;
import pages.GmailStartPage;
import pages.util.PageUtil;

/**
 * Created by Dauren_Altynbekov on 8/25/2015.
 */
public abstract class AbstractTest {
    public static final String TESTING_EMAIL = "autodaurtest@gmail.com";
    public static final String TESTING_EMAIL_PASSWORD = "autodaurtest1";
    protected WebDriver driver;


    public void login() {
        GmailStartPage mailStartPage = new GmailStartPage(driver);
        mailStartPage.openPage();
        mailStartPage.login(TESTING_EMAIL, TESTING_EMAIL_PASSWORD);
        PageUtil.waitForChangingPage(driver, "https://mail.google.com/mail/#inbox");
    }


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        driver = Driver.getDriver();
        login();
    }

    @AfterSuite
    public void afterSuite() {
        GmailInboxPage inboxPage = new GmailInboxPage(Driver.getDriver());
        inboxPage.logout();
        Driver.closeBrowser();
    }
}
