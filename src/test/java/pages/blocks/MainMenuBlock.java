package pages.blocks;

import driver.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */

@FindBy(xpath = "//div[@class='nM']")
public class MainMenuBlock extends HtmlElement{

    @FindBy(xpath = "/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[1]/div/div")
    private WebElement newMailBtn;

    @FindBy(xpath = "//a[contains(@href,'https://mail.google.com/mail/#drafts')]")
    private WebElement draftsLink;

    @FindBy(xpath = "//a[contains(@href,'https://mail.google.com/mail/#sent')]")
    private WebElement sentLink;

    public void startNewMail(){
        newMailBtn.click();

    }

    public void goToDrafts(){
        draftsLink.click();
    }

    public void goToSent(){
        sentLink.click();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }

    public WebElement getNewMailBtn() {

        return newMailBtn;
    }
}
