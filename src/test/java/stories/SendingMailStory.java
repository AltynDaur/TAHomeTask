package stories;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.jbehave.core.annotations.BeforeStory;
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
@RunWith(ThucydidesRunner.class)
@Story(GmailApplication.SendingMails.class)
public class SendingMailStory {
    public static final String MAIL_ADDRESS = "test.auto@inbox.ru";
    public static final String MAIL_THEME = "Nice test";
    public static final String MAIL_BODY = "You're the best";

    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    public SendingMailSteps steps;

    @Steps
    public LoginSteps loginSteps;

    @Test
    public void sentMailFromDraft() {
        loginSteps.login();
        steps.createNewMail(MAIL_ADDRESS, MAIL_THEME, MAIL_BODY);
        steps.saveNewMailToDraft();
        steps.sentNewMailFromDraft();
    }



}
