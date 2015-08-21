package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.blocks.HeaderBlock;
import pages.blocks.MailsListBlock;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dauren_Altynbekov on 8/14/2015.
 */
public class GmailSentPage implements Page {

    public static final String GMAIL_SENT_PAGE = "https://mail.google.com/mail/#sent";
    private WebDriver driver;

    private MailsListBlock mailsList;
    private HeaderBlock header;

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

    public String getLastMailThemeInCategory() {
        return mailsList.getLastMailThemeInCategory();
    }

    public void logout() {
        header.openAccountManagementDialog();
        header.logout();
    }
}
