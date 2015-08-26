package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.blocks.HeaderBlock;
import pages.blocks.MailsListBlock;
import pages.blocks.MainMenuBlock;
import pages.util.PageUtil;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dauren_Altynbekov on 8/14/2015.
 */
public class GmailSentPage extends Page {

    public static final String GMAIL_SENT_PAGE = "https://mail.google.com/mail/#sent";

    private MailsListBlock mailsList;
    private HeaderBlock header;
    private MainMenuBlock mainMenu;

    public GmailSentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public GmailSentPage() {

    }

    public void openPage() {
        this.driver.get(GMAIL_SENT_PAGE);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement getLastMailThemeInCategory() {
        return mailsList.getLastMailThemeInCategory();
    }

    public void logout() {
        header.openAccountManagementDialog();
        header.logout();
    }

    public GmailInboxPage goToInbox() {
        mainMenu.goToInbox();
        return new GmailInboxPage(this.driver);
    }

}
