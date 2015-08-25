package tests;

import driver.Driver;
import org.junit.Ignore;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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

    @BeforeSuite(groups = {"sendingTests"})
    public void login() {
        inboxPage = GmailTestsUtil.login();
    }

    @Test(groups = "sendingTests")
    public void isLoginSuccessfully() {
        inboxPage.waitForLoadingPage();
        Assert.assertTrue(inboxPage.getCurrentUrl().startsWith("https://mail.google.com/mail/#inbox"));
    }

    @Test(groups = "sendingTests")
    public void oneCanCreateNewMail() {
        inboxPage.startNewMail();
        inboxPage.waitForNewMailDialogTitleAppering();
        Assert.assertEquals(inboxPage.getCreatingDialogTitle(), "Новое сообщение");
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"oneCanCreateNewMail"})
    public void saveNewMailToDraft() {
        inboxPage.writeNewMail(MAIL_ADDRESS, MAIL_THEME, MAIL_BODY);
        inboxPage.closeNewMailDialog();
        draftsPage = inboxPage.goToDrafts();
        sizeBeforeSending = draftsPage.getMailListSizeWithTheme(MAIL_THEME);
        Assert.assertEquals(draftsPage.getLastMailThemeInCategory(), MAIL_THEME);
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"saveNewMailToDraft"})
    public void checkAddressNewMailInDraft() {
        draftsPage.openLastMailInCategory();
        Assert.assertEquals(draftsPage.getMailAddressFromDialog(), MAIL_ADDRESS);
    }


    @Test(groups = "sendingTests", dependsOnMethods = {"checkAddressNewMailInDraft"})
    public void checkThemeNewMailInDraft() {
        Assert.assertEquals(draftsPage.getMailThemeFromDialog(), MAIL_THEME);
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"checkThemeNewMailInDraft"})
    public void checkBodyNewMailInDraft() {
        Assert.assertEquals(draftsPage.getMailBodyFromDialog(), MAIL_BODY);
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"checkAddressNewMailInDraft", "checkThemeNewMailInDraft", "checkBodyNewMailInDraft"})
    public void sentMailFromDraft() {
        draftsPage.sendMailFromDialog();
        draftsPage.goBackToMailsList();
        draftsPage.goToDrafts();
        draftsPage.waitForMailCountChanging();
        Assert.assertNotEquals(sizeBeforeSending, draftsPage.getMailListSizeWithTheme(MAIL_THEME), "Size of drafts didn't changed");
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"sentMailFromDraft"})
    public void checkSentMailInSentCategoory() {
        sentPage = draftsPage.goToSent();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        wait.until(ExpectedConditions.urlContains("https://mail.google.com/mail/#sent"));
        Assert.assertEquals(sentPage.getLastMailThemeInCategory().getText(), MAIL_THEME);
        inboxPage = sentPage.goToInbox();
    }

    @Test(groups = "sendingTests")
    public void createNewMailWithoutAddress() {
        inboxPage.startNewMail();
        inboxPage.writeNewMail("", MAIL_THEME, MAIL_BODY);
        inboxPage.sentNewMail();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(inboxPage.getErrorMessage()));
        Assert.assertEquals(inboxPage.getErrorMessage().getText(), "Укажите как минимум одного получателя.");
        inboxPage.closeErrorMessage();
        inboxPage.closeNewMailDialog();
    }

    @AfterSuite
    public void closeGmail() {
        sentPage.logout();
        Driver.closeBrowser();
    }
}
