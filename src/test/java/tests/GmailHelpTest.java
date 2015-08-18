package tests;

import driver.SingleWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GmailDraftsPage;
import pages.GmailInboxPage;
import pages.GmailSentPage;
import pages.GmailStartPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dauren_Altynbekov on 8/18/2015.
 */
public class GmailHelpTest {

    public static final String SEARCH_STRING = "черно";
    public static final String EXPECTED_OPTION = "Как сохранить черновик";
    WebDriver driver = SingleWebDriver.getFirefoxDriverInstance();
    GmailStartPage mailStartPage = new GmailStartPage(driver);
    GmailInboxPage inboxPage = new GmailInboxPage();

    @Test
    public void isLoginSuccessfully() {
        mailStartPage.openPage();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        inboxPage = mailStartPage.login("autodaurtest@gmail.com", "autodaurtest1");
        Assert.assertEquals(inboxPage.getCurrentUrl(), "https://mail.google.com/mail/#inbox");
    }

    @Test(dependsOnMethods = "isLoginSuccessfully")
    public void checkDraftHelp() {
        inboxPage.openSettingsDialog();
        inboxPage.openHelpDialog();
        driver.switchTo().frame(driver.findElement(By.id("google-feedback-wizard")));
        inboxPage.search(SEARCH_STRING);
        Assert.assertEquals(inboxPage.isHaveThisOption(EXPECTED_OPTION), true);
    }
}
