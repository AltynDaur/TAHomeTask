package tests;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.GmailInboxPage;
import pages.GmailStartPage;

/**
 * Created by Dauren_Altynbekov on 8/25/2015.
 */
public class AbstractTest {
    public static final String TESTING_EMAIL = "autodaurtest@gmail.com";
    public static final String TESTING_EMAIL_PASSWORD = "autodaurtest1";
    private static WebDriver driver;
    GmailInboxPage inboxPage = new GmailInboxPage(Driver.getDriver());

    public static GmailInboxPage login() {
        GmailStartPage mailStartPage = new GmailStartPage(Driver.getDriver());
        mailStartPage.openPage();
        GmailInboxPage inboxPage = mailStartPage.login(TESTING_EMAIL, TESTING_EMAIL_PASSWORD);
        waitForLoadingPage();
        return inboxPage;
    }

    private static void waitForLoadingPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://mail.google.com/mail/#inbox"));
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        driver = Driver.getDriver();
        inboxPage = login();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        inboxPage.logout();
        Driver.closeBrowser();
    }
}
