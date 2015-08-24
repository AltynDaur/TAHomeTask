package tests;

import driver.Driver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
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
    GmailInboxPage inboxPage = new GmailInboxPage();
    GmailDraftsPage draftsPage = new GmailDraftsPage();
    GmailSentPage sentPage = new GmailSentPage();
    private int sizeBeforeSending = 0;

    @Test
    public void isLoginSuccessfully() {
        inboxPage = GmailTestsUtil.login();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        wait.withTimeout(7, TimeUnit.SECONDS);
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
        sizeBeforeSending = draftsPage.getMailListSizeWithTheme(MAIL_THEME);
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

    @Test(dependsOnMethods = {"checkAddressNewMailInDraft", "checkThemeNewMailInDraft", "checkBodyNewMailInDraft"})
    public void sentMailFromDraft() {
        draftsPage.sendMailFromDialog();
        draftsPage.goBackToMailsList();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        wait.withTimeout(5, TimeUnit.SECONDS);
        Assert.assertNotEquals(sizeBeforeSending, draftsPage.getMailListSizeWithTheme(MAIL_THEME), "Size of drafts didn't changed");
    }

    @Test(dependsOnMethods = {"sentMailFromDraft"})
    public void checkSentMailInSentCategoory() {
        sentPage = draftsPage.goToSent();
        Assert.assertEquals(sentPage.getLastMailThemeInCategory(), MAIL_THEME);
    }

    @AfterSuite
    public void closeGmail() {
        sentPage.logout();
        Driver.closeBrowser();
    }
}
