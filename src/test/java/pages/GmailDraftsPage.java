package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.blocks.CreatingMailDialogBlock;
import pages.blocks.EditingMailDialogBlock;
import pages.blocks.MailsListBlock;
import pages.blocks.MainMenuBlock;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
public class GmailDraftsPage implements Page {
    public static final String GMAIL_DRAFTS_PAGE = "https://mail.google.com/mail/#drafts";
    private WebDriver driver;

    private MainMenuBlock mainMenu;
    private MailsListBlock mailsList;
    private EditingMailDialogBlock mailDialog;

    public GmailDraftsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public GmailDraftsPage() {

    }

    public void openPage() {
        this.driver.get(GMAIL_DRAFTS_PAGE);
    }

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }


    public String getLastMailThemeInCategory() {
        return mailsList.getLastMailThemeInCategory();
    }

    public void openLastMailInCategory() {
        mailsList.openLastMailInCategory();
    }

    public String getMailAddressFromDialog() {
        return mailDialog.getMailAddressFromDialog();
    }

    public String getMailThemeFromDialog() {
        return mailDialog.getMailThemeFromDialog();
    }

    public String getMailBodyFromDialog() {
        return mailDialog.getMailBodyFromDialog();
    }

    public void sendMailFromDialog() {
        mailDialog.sendMailFromDialog();
    }

    public GmailSentPage goToSent() {
        mainMenu.goToSent();
        return new GmailSentPage(this.driver);
    }
}
