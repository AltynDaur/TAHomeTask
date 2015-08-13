package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GmailDraftsPage;
import pages.GmailStartPage;
import pages.GmailInboxPage;

import java.util.concurrent.TimeUnit;


/**
 * Created by Dauren_Altynbekov on 8/12/2015.
 */
public class GmailTest {

    public static final String MAIL_ADDRESS = "altyndaur@gmail.com";
    public static final String MAIL_THEME = "Nice test";
    public static final String MAIL_BODY = "You're the best";
    WebDriver driver = new FirefoxDriver();
    GmailStartPage mailStartPage = new GmailStartPage(driver);
    GmailInboxPage inboxPage = new GmailInboxPage(driver);
    GmailDraftsPage draftsPage = new GmailDraftsPage(driver);


    @Test
    public void oneCanLoginGmail(){
        mailStartPage.openPage();
        inboxPage = mailStartPage.login("autodaurtest@gmail.com", "autodaurtest1");
    }

    @Test(dependsOnMethods = {"oneCanLoginGmail"})
    public void isLoginSuccessfully(){
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        Assert.assertEquals(inboxPage.getCurrentUrl(), "https://mail.google.com/mail/#inbox");
    }

    @Test(dependsOnMethods = {"oneCanLoginGmail"})
    public void oneCanCreateNewMail(){
        inboxPage.openPage();
        inboxPage.startNewMail();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='aYF']")).getText(),"Новое сообщение");
    }

    @Test(dependsOnMethods = {"oneCanCreateNewMail"})
    public void oneCanFillMailFields(){
        inboxPage.writeNewMail(MAIL_ADDRESS, MAIL_THEME, MAIL_BODY);
    }

    @Test(dependsOnMethods = {"oneCanFillMailFields"})
    public void saveNewMailToDraft(){
        inboxPage.closeNewMailDialog();
    }

    @Test(dependsOnMethods = {"saveNewMailToDraft"})
    public void checkNewMailInDraft(){
        draftsPage = inboxPage.goToDrafts();
        Assert.assertEquals(draftsPage.getLastMailThemeInCategory(), MAIL_THEME);
    }

    @Test(dependsOnMethods = {"checkNewMailInDraft"})
    public void checkAddressNewMailInDraft() {
        draftsPage.openLastMailInCategory();
        Assert.assertEquals(draftsPage.getMailAddressFromDialog(), MAIL_ADDRESS);
    }


    @Test(dependsOnMethods = {"checkNewMailInDraft"})
    public void checkThemeNewMailInDraft() {
        draftsPage.openLastMailInCategory();
        Assert.assertEquals(draftsPage.getMailThemeFromDialog(), MAIL_THEME);
    }

    @Test(dependsOnMethods = {"checkNewMailInDraft"})
    public void checkBodyNewMailInDraft() {
        draftsPage.openLastMailInCategory();
        Assert.assertEquals(draftsPage.getMailBodyFromDialog(), MAIL_BODY);
    }

    @Test(dependsOnMethods = {"checkAddressNewMailInDraft", "checkThemeNewMailInDraft", "checkBodyNewMailInDraft"})
    public void sendMailFromDraft(){
        draftsPage.sendMailFromDialog();
    }

    @Test(dependsOnMethods = {"sendMailFromDraft"},expectedExceptions = NoSuchElementException.class)
    public void checkMailInDraftAfterSending(){
        Assert.assertEquals(draftsPage.getLastMailThemeInCategory(), MAIL_THEME);
    }

    @Test(dependsOnMethods = {"sendMailFromDraft"})
    public void checkSendedMailInSendCategoory(){

    }
}
