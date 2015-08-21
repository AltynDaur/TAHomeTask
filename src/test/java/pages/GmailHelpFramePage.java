package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.blocks.HelpDialogBlock;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * Created by Dauren_Altynbekov on 8/20/2015.
 */
public class GmailHelpFramePage {

    private WebDriver driver;
    private HelpDialogBlock helpDialog;

    @FindBy(id = "google-feedback-wizard")
    private WebElement helpFrame;

    public GmailHelpFramePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public GmailHelpFramePage() {

    }

    public void search(String searchString) {
        helpDialog.search(searchString);
    }

    public boolean isHaveThisOption(String expectedOption) {
        return helpDialog.isHaveThisOption(expectedOption);
    }

    public WebElement getHelpDialog() {
        return helpFrame;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public void closeHelpFrame() {
        helpDialog.closeHelpDialog();
    }
}
