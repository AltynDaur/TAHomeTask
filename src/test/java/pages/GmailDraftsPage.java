package pages;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.blocks.EditingMailMainBlock;
import pages.blocks.ToolBarBlock;
import pages.blocks.MailsListBlock;
import pages.blocks.MainMenuBlock;
import pages.util.PageUtil;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
public class GmailDraftsPage extends Page {
    public static final String GMAIL_DRAFTS_PAGE = "https://mail.google.com/mail/#drafts";

    private MainMenuBlock mainMenu;
    private MailsListBlock mailsList;
    private EditingMailMainBlock mailMainBlock;
    private ToolBarBlock toolBar;

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
        return mailsList.getLastMailThemeInCategory().getText();
    }

    public void openLastMailInCategory() {
        mailsList.openLastMailInCategory();
    }

    public String getMailAddressFromDialog() {
        return mailMainBlock.getMailAddressFromDialog();
    }

    public String getMailThemeFromDialog() {
        return mailMainBlock.getMailThemeFromDialog();
    }

    public String getMailBodyFromDialog() {
        return mailMainBlock.getMailBodyFromDialog();
    }

    public void sendMailFromDialog() {
        mailMainBlock.sendMailFromDialog();
    }

    public GmailSentPage goToSent() {
        mainMenu.goToSent();
        return new GmailSentPage(this.driver);
    }

    public int getMailListSizeWithTheme(String mailTheme) {
        return mailsList.getMailsWithTheme(mailTheme).size();
    }

    public void goBackToMailsList() {
        toolBar.backToMailList();
    }

    public void goToDrafts() {
        mainMenu.goToDrafts();
    }


    public List<WebElement> getMailList() {

        return mailsList.getMailList();
    }

    public void waitForMailCountChanging() {
        final int numberOfMailLabels = driver
                .findElements(By.xpath("//div[@class='BltHke nH oy8Mbf' and @role='main']//tr[@class='zA yO']//div[@class='y6']/span[1]"))
                .size();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver driver) {
                List<WebElement> mailLabelList = driver.findElements(By.xpath("//div[@class='BltHke nH oy8Mbf' and @role='main']//tr[@class='zA yO']//div[@class='y6']/span[1]"));
                return numberOfMailLabels != mailLabelList.size();
            }
        });
    }

}
