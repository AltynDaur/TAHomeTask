package stories;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import requirements.GmailApplication;
import steps.LoginSteps;
import steps.SendingMailSteps;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class SendingMailStory {
    public static final String MAIL_ADDRESS = "test.auto@inbox.ru";
    public static final String MAIL_THEME = "Nice test";
    public static final String MAIL_BODY = "You're the best";

    @Steps
    public SendingMailSteps steps;

    @Steps
    public LoginSteps loginSteps;

    @Given("the user enters to Gmail")
    public void enterToGmail() {
        loginSteps.login();

    }

    @When("the user create new mail")
    public void createNewMail() {
        steps.createNewMail(MAIL_ADDRESS, MAIL_THEME, MAIL_BODY);
    }

    @When("save it to draft")
    public void saveToeDraft() {
        steps.saveNewMailToDraft();
    }

    @Then("they should see mail theme in draft list")
    public void shouldSeeMailThemeInDraftList() {
        Assert.assertEquals(MAIL_THEME, steps.getLastThemeInDraftsPage());
    }



}
