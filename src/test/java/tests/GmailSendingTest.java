package tests;

import driver.SingleWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.GmailDraftsPage;
import pages.GmailSentPage;
import pages.GmailStartPage;
import pages.GmailInboxPage;

import java.util.concurrent.TimeUnit;


/**
 * Created by Dauren_Altynbekov on 8/12/2015.
 */
public class GmailSendingTest {

    public static final String MAIL_ADDRESS = "test.auto@inbox.ru";
    public static final String MAIL_THEME = "Nice test";
    public static final String MAIL_BODY = "You're the best";
    public static final String TESTING_EMAIL = "autodaurtest@gmail.com";
    public static final String TESTING_EMAIL_PASSWORD = "autodaurtest1";

    WebDriver driver = SingleWebDriver.getRemoteDriver();
    GmailStartPage mailStartPage = new GmailStartPage(driver);
    GmailInboxPage inboxPage = new GmailInboxPage();
    GmailDraftsPage draftsPage = new GmailDraftsPage();
    GmailSentPage sentPage = new GmailSentPage();

    @Test
    public void isLoginSuccessfully() {
        mailStartPage.openPage();
        inboxPage = mailStartPage.login(TESTING_EMAIL, TESTING_EMAIL_PASSWORD);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        Assert.assertEquals(inboxPage.getCurrentUrl(), "https://mail.google.com/mail/#inbox");
    }

    @Test(dependsOnMethods = {"isLoginSuccessfully"})
    public void oneCanCreateNewMail() {
        inboxPage.startNewMail();
        Assert.assertEquals(inboxPage.getCreatingDialogTitle(), "Новое сообщение");
    }

    @Test(dependsOnMethods = {"oneCanCreateNewMail"})
    public void saveNewMailToDraft() {
        inboxPage.writeNewMail(MAIL_ADDRESS, MAIL_THEME, MAIL_BODY);
        inboxPage.closeNewMailDialog();
        draftsPage = inboxPage.goToDrafts();
        Assert.assertEquals(draftsPage.getLastMailThemeInCategory(), MAIL_THEME);
    }

    @Test(dependsOnMethods = {"saveNewMailToDraft"})
    public void checkAddressNewMailInDraft() {
        draftsPage.openLastMailInCategory();
        Assert.assertEquals(draftsPage.getMailAddressFromDialog(), MAIL_ADDRESS);
    }


    @Test(dependsOnMethods = {"checkAddressNewMailInDraft"})
    public void checkThemeNewMailInDraft() {
        Assert.assertEquals(draftsPage.getMailThemeFromDialog(), MAIL_THEME);
    }

    @Test(dependsOnMethods = {"checkThemeNewMailInDraft"})
    public void checkBodyNewMailInDraft() {
        Assert.assertEquals(draftsPage.getMailBodyFromDialog(), MAIL_BODY);
    }

    @Test(dependsOnMethods = {"checkAddressNewMailInDraft", "checkThemeNewMailInDraft", "checkBodyNewMailInDraft"}, expectedExceptions = NoSuchElementException.class)
    public void sentMailFromDraft() {
        draftsPage.sendMailFromDialog();
        Assert.assertNotEquals(draftsPage.getLastMailThemeInCategory(), null);
    }

    @Test(dependsOnMethods = {"sentMailFromDraft"})
    public void checkSentMailInSentCategoory() {
        sentPage = draftsPage.goToSent();
        Assert.assertEquals(sentPage.getLastMailThemeInCategory(), MAIL_THEME);
    }

    @AfterTest(dependsOnMethods = {"checkSentMailInSentCategoory"})
    public void closeGmail() {
        sentPage.logout();
        SingleWebDriver.closeBrowser();
    }
}
