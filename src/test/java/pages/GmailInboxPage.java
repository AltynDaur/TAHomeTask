package pages;

import com.google.common.base.Predicate;
import driver.Driver;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.blocks.*;
import pages.util.PageUtil;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
@DefaultUrl("https://mail.google.com/mail/#inbox")
public class GmailInboxPage extends Page {

    public static final String GMAIL_INBOX_PAGE = "https://mail.google.com/mail/#inbox";
    private MainMenuBlock mainMenu;
    private MailsListBlock mailsList;
    private CreatingMailDialogBlock mailDialog;
    private SettingsDialogBlock settingsDialog;
    private ToolBarBlock helpToolBar;
    private HeaderBlock header;
    private ErrorMessageBlock errorMessage;

    public GmailInboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public GmailInboxPage() {

    }

    public void openPage() {
        this.driver.get(GMAIL_INBOX_PAGE);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void startNewMail() {
        mainMenu.startNewMail();
        PageUtil.waitForNewMailDialogTitleAppering(driver);

    }

    public void writeNewMail(String mailAddress, String mailTheme, String mailBody) {
        mailDialog.writeNewMail(mailAddress, mailTheme, mailBody);
    }

    public void closeNewMailDialog() {
        mailDialog.closeNewMailDialog();
    }

    public GmailDraftsPage goToDrafts() {
        mainMenu.goToDrafts();
        PageUtil.waitForMailCountChanging(driver);
        return new GmailDraftsPage(this.driver);
    }

    public void openSettingsDialog() {
        helpToolBar.openSettingsDialog();
    }

    public GmailHelpFramePage openHelpDialog() {
        settingsDialog.openHelp();
        return new GmailHelpFramePage(driver);
    }


    public String getCreatingDialogTitle() {
        return mailDialog.getTitle();
    }

    public void logout() {
        header.openAccountManagementDialog();
        header.logout();
    }

    public WebElement getHeader() {
        return header;
    }

    public void sentNewMail() {
        mailDialog.sendMailFromDialog();
    }

    public WebElement getErrorMessage() {
        return errorMessage.getErrorMessage();
    }

    public void closeErrorMessage() {
        errorMessage.closeErrorMessage();
    }

    public void waitForErrorMessageAppering() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(errorMessage.getErrorMessage()));
    }

    public void sentNewMailWithError() {
        mailDialog.sendMailFromDialog();
        waitForErrorMessageAppering();
    }
}
