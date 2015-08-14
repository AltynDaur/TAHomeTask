package pages.blocks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
@FindBy(xpath = "//div[@class='AO']")
public class EditingMailMainBlock extends HtmlElement {

    @FindBy(xpath = "//div[@class='nH']//form//div[@class='oL aDm az9']/span")
    private WebElement mailAddressInput;

    @FindBy(xpath = "//h2[@class='hP']")
    private WebElement mailThemeInput;

    @FindBy(xpath = "//div[@class='Ar Au Ao']/div")
    private WebElement mailBodyInput;

    @FindBy(xpath = "//div[@class='nH']//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private WebElement sendBtnInDialog;

    public String getMailAddressFromDialog() {
        return mailAddressInput.getAttribute("email");
    }

    public String getMailThemeFromDialog() {
        return mailThemeInput.getText();
    }

    public String getMailBodyFromDialog() {
        return mailBodyInput.getText();
    }

    public void sendMailFromDialog() {
        sendBtnInDialog.click();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}
