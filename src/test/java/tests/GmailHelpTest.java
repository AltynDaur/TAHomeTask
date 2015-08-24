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
    WebDriver driver = Driver.getDriver();
    GmailInboxPage inboxPage = new GmailInboxPage();
    GmailHelpFramePage helpFramePage = new GmailHelpFramePage();


    @BeforeClass
    public void prepareTest() {
        inboxPage = GmailTestsUtil.login();
    }

    @Test
    public void checkDraftHelp() {
        inboxPage.openSettingsDialog();
        helpFramePage = inboxPage.openHelpDialog();
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
