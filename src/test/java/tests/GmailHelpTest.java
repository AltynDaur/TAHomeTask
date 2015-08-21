package tests;

import driver.Driver;
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
    WebDriver driver = Driver.getDriver();
    GmailStartPage mailStartPage = new GmailStartPage(driver);
    GmailInboxPage inboxPage = new GmailInboxPage();
    GmailHelpFramePage helpFramePage = new GmailHelpFramePage();


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
        helpFramePage.setDriver(driver);
        driver.switchTo().frame(helpFramePage.getHelpDialog());
        helpFramePage.search(SEARCH_STRING);
        Assert.assertEquals(helpFramePage.isHaveThisOption(EXPECTED_OPTION), true);
    }

    @AfterTest
    public void closeGmail() {
        helpFramePage.closeHelpFrame();
        driver.switchTo().defaultContent();
        inboxPage.logout();
        Driver.closeBrowser();
    }
}
