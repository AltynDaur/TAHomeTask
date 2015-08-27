package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import pages.GmailDraftsPage;
import pages.GmailInboxPage;

/**
 * Created by Dauren_Altynbekov on 8/26/2015.
 */
public class SendingMailSteps extends ScenarioSteps {
    GmailInboxPage inboxPage = getPages().getPage(GmailInboxPage.class);
    GmailDraftsPage draftsPage = getPages().getPage(GmailDraftsPage.class);

    public SendingMailSteps(Pages pages) {
        super(pages);
    }

    @Step
    public void createNewMail(String address, String theme, String bodyMessage) {
        inboxPage.open();
        inboxPage.startNewMail();
        inboxPage.writeNewMail(address, theme, bodyMessage);
    }

    @Step
    public void sendMailFromCreationDialogBox() {
        inboxPage.sentNewMail();
    }

    @Step
    public void saveNewMailToDraft() {
        inboxPage.closeNewMailDialog();
    }

    @Step
    public void sentNewMailFromDraft() {
        draftsPage.openLastMailInCategory();
        draftsPage.sendMailFromDialog();
    }

    @Step
    public String getLastThemeInDraftsPage() {
        return draftsPage.getLastMailThemeInCategory();
    }
}
