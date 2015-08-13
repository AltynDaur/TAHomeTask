package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.blocks.CreatingMailDialogBlock;
import pages.blocks.MailsListBlock;
import pages.blocks.MainMenuBlock;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
public class GmailInboxPage implements Page {

    public static final String GMAIL_INBOX_PAGE = "https://mail.google.com/mail/#inbox";
    private MainMenuBlock mainMenu;
    private MailsListBlock mailsList;
    private CreatingMailDialogBlock mailDialog;
    private WebDriver driver;

    public GmailInboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public void openPage() {
        this.driver.get(GMAIL_INBOX_PAGE);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void startNewMail() {
        mainMenu.startNewMail();
    }

    public void writeNewMail(String mailAddress, String mailTheme, String mailBody) {
        mailDialog.writeNewMail(mailAddress, mailTheme, mailBody);
    }

    public void closeNewMailDialog() {
        mailDialog.closeNewMailDialog();
    }

    public GmailDraftsPage goToDrafts() {
        mainMenu.goToDrafts();
        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
        return new GmailDraftsPage(this.driver);
    }
}
