package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.GmailStartPage;
import pages.util.PageUtil;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class LoginSteps extends ScenarioSteps {
    public static final String TESTING_EMAIL = "autodaurtest@gmail.com";
    public static final String TESTING_EMAIL_PASSWORD = "autodaurtest1";

    @Step
    public void login() {
        GmailStartPage startPage = getPages().getPage(GmailStartPage.class);
        startPage.open();
        startPage.login(TESTING_EMAIL, TESTING_EMAIL_PASSWORD);
        PageUtil.waitForChangingPage(getDriver(), "https://mail.google.com/mail/#inbox");
    }
}
