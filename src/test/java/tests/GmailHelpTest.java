package tests;

import driver.Driver;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pages.*;

/**
 * Created by Dauren_Altynbekov on 8/18/2015.
 */

public class GmailHelpTest extends AbstractTest {

    public static final String SEARCH_STRING = "черно";
    public static final String EXPECTED_OPTION = "Как сохранить черновик";
    private static Logger logger = Logger.getLogger(GmailHelpTest.class);
    GmailInboxPage inboxPage = new GmailInboxPage(Driver.getDriver());
    GmailHelpFramePage helpFramePage = new GmailHelpFramePage();


    @Test(groups = "userHelpTests")
    public void checkDraftHelp() {
        inboxPage.openSettingsDialog();
        helpFramePage = inboxPage.openHelpDialog();
        driver.switchTo().frame(helpFramePage.getHelpDialog());
        helpFramePage.search(SEARCH_STRING);
        logger.info("Searching: " + SEARCH_STRING);
        helpFramePage.takeScreenShot();
        Assert.assertEquals(helpFramePage.isHaveThisOption(EXPECTED_OPTION), true);
    }

    @AfterSuite
    public void afterSuite() {
        helpFramePage.closeHelpFrame();
        driver.switchTo().defaultContent();
        inboxPage.logout();
        Driver.closeBrowser();
    }
}
