package tests;

import driver.Driver;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pages.GmailDraftsPage;
import pages.GmailSentPage;
import pages.GmailStartPage;
import pages.GmailInboxPage;


/**
 * Created by Dauren_Altynbekov on 8/12/2015.
 */
public class GmailSendingTest extends AbstractTest {

    public static final String MAIL_ADDRESS = "test.auto@inbox.ru";
    public static final String MAIL_THEME = "Nice test";
    public static final String MAIL_BODY = "You're the best";
    private static Logger logger = Logger.getLogger(GmailSendingTest.class);
    GmailInboxPage inboxPage = new GmailInboxPage(Driver.getDriver());
    GmailDraftsPage draftsPage = new GmailDraftsPage();
    GmailSentPage sentPage = new GmailSentPage();
    private int sizeBeforeSending = 0;

    @Test(groups = "sendingTests")
    public void isLoginSuccessfully() {
        logger.info("Checking login");
        inboxPage.takeScreenShot();
        Assert.assertTrue(inboxPage.getCurrentUrl().startsWith("https://mail.google.com/mail/#inbox"));
    }

    @Test(groups = "sendingTests")
    public void oneCanCreateNewMail() {
        inboxPage.startNewMail();
        logger.info("Checking creating new mail");
        inboxPage.takeScreenShot();
        Assert.assertEquals(inboxPage.getCreatingDialogTitle(), "Новое сообщение");
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"oneCanCreateNewMail"})
    public void saveNewMailToDraft() {
        inboxPage.writeNewMail(MAIL_ADDRESS, MAIL_THEME, MAIL_BODY);
        inboxPage.closeNewMailDialog();
        draftsPage = inboxPage.goToDrafts();
        sizeBeforeSending = draftsPage.getMailListSizeWithTheme(MAIL_THEME);
        logger.info("Checking last mail in draft list: " + draftsPage.getLastMailThemeInCategory());
        draftsPage.takeScreenShot();
        Assert.assertEquals(draftsPage.getLastMailThemeInCategory(), MAIL_THEME);
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"saveNewMailToDraft"})
    public void checkAddressNewMailInDraft() {
        draftsPage.openLastMailInCategory();
        logger.info("Checking mail address: " + draftsPage.getMailAddressFromDialog());
        draftsPage.takeScreenShot();
        Assert.assertEquals(draftsPage.getMailAddressFromDialog(), MAIL_ADDRESS);
    }


    @Test(groups = "sendingTests", dependsOnMethods = {"checkAddressNewMailInDraft"})
    public void checkThemeNewMailInDraft() {
        logger.info("Checking mail theme: " + draftsPage.getMailThemeFromDialog());
        draftsPage.takeScreenShot();
        Assert.assertEquals(draftsPage.getMailThemeFromDialog(), MAIL_THEME);
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"checkThemeNewMailInDraft"})
    public void checkBodyNewMailInDraft() {
        logger.info("Checking mail body: " + draftsPage.getMailBodyFromDialog());
        draftsPage.takeScreenShot();
        Assert.assertEquals(draftsPage.getMailBodyFromDialog(), MAIL_BODY);
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"checkAddressNewMailInDraft", "checkThemeNewMailInDraft", "checkBodyNewMailInDraft"})
    public void sentMailFromDraft() {
        draftsPage.sendMailFromDialog();
        draftsPage.goBackToMailsList();
        draftsPage.goToDrafts();
        logger.info("Checking mail list size before and after sending mail: " + sizeBeforeSending + " and " + draftsPage.getMailListSizeWithTheme(MAIL_THEME));
        draftsPage.takeScreenShot();
        Assert.assertNotEquals(sizeBeforeSending, draftsPage.getMailListSizeWithTheme(MAIL_THEME), "Size of drafts didn't changed");
    }

    @Test(groups = "sendingTests", dependsOnMethods = {"sentMailFromDraft"})
    public void checkSentMailInSentCategoory() {
        sentPage = draftsPage.goToSent();

        String lastThemeInList = sentPage.getLastMailThemeInCategory().getText();
        inboxPage = sentPage.goToInbox();
        logger.info("Checking last mail in sent list: " + lastThemeInList);
        inboxPage.takeScreenShot();
        Assert.assertEquals(lastThemeInList, MAIL_THEME);
    }

    @Test(groups = "sendingTests", dependsOnMethods = "checkSentMailInSentCategoory")
    public void createNewMailWithoutAddress() {
        inboxPage.startNewMail();
        inboxPage.writeNewMail("", MAIL_THEME, MAIL_BODY);
        inboxPage.sentNewMailWithError();
        String errorMessageText = inboxPage.getErrorMessage().getText();
        inboxPage.takeScreenShot();
        inboxPage.closeErrorMessage();
        inboxPage.closeNewMailDialog();
        logger.info("Checking error message: " + errorMessageText);
        Assert.assertEquals(errorMessageText, "Укажите как минимум одного получателя.");

    }

}
