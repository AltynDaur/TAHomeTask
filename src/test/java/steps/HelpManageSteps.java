package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;
import pages.GmailHelpFramePage;
import pages.GmailInboxPage;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class HelpManageSteps extends ScenarioSteps {
    GmailInboxPage inboxPage = getPages().getPage(GmailInboxPage.class);
    GmailHelpFramePage helpFramePage = getPages().getPage(GmailHelpFramePage.class);

    @Step
    public void openHelpFrame(WebDriver driver) {
        inboxPage.openSettingsDialog();
        inboxPage.openHelpDialog();
        driver.switchTo().frame(helpFramePage.getHelpDialog());

    }

    @Step
    public void startSearching(String searchString) {
        helpFramePage.search(searchString);
    }


}
