package tests;

import driver.SingleWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dauren_Altynbekov on 8/18/2015.
 */
public class GmailHelpTest {

    public static final String SEARCH_STRING = "черно";
    public static final String EXPECTED_OPTION = "Как сохранить черновик";
    public static final String TESTING_EMAIL = "autodaurtest@gmail.com";
    public static final String TESTING_EMAIL_PASSWORD = "autodaurtest1";
    WebDriver driver;
    GmailStartPage mailStartPage = new GmailStartPage();
    GmailInboxPage inboxPage = new GmailInboxPage();
    GmailHelpFramePage helpFramePage = new GmailHelpFramePage();


    @Parameters("driver")
    @BeforeClass
    public void config(@Optional("firefox") String driverName) {
        driver = SingleWebDriver.getDriver(driverName);
        mailStartPage.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
    }

    @Test
    public void isLoginSuccessfully() {
        mailStartPage.openPage();
        inboxPage = mailStartPage.login(TESTING_EMAIL, TESTING_EMAIL_PASSWORD);
        Assert.assertEquals(inboxPage.getCurrentUrl(), "https://mail.google.com/mail/#inbox");
    }

    @Test(dependsOnMethods = "isLoginSuccessfully")
    public void checkDraftHelp() {
        inboxPage.openSettingsDialog();
        inboxPage.openHelpDialog();
        driver.switchTo().frame(driver.findElement(By.id("google-feedback-wizard")));
        helpFramePage.setDriver(driver);
        helpFramePage.search(SEARCH_STRING);
        Assert.assertEquals(helpFramePage.isHaveThisOption(EXPECTED_OPTION), true);
    }

    @AfterTest
    public void closeGmail() {
        helpFramePage.closeHelpFrame();
        driver.switchTo().defaultContent();
        inboxPage.logout();
        SingleWebDriver.closeBrowser();
    }
}
