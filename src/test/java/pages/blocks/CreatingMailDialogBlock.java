package pages.blocks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
@FindBy(xpath = "//div[@class='AD']")
public class CreatingMailDialogBlock extends HtmlElement {

    @FindBy(xpath = "//div[@class='AD']//textarea[@name='to']")
    private WebElement newMailAddressInput;

    @FindBy(xpath = "//div[@class='AD']//input[@class='aoT']")
    private WebElement newMailThemeInput;

    @FindBy(xpath = "//div[@class='AD']//div[@role='textbox']")
    private WebElement newMailBodyInput;

    @FindBy(className = "Ha")
    private WebElement closeNewMailDialogBtn;

    @FindBy(xpath = "//div[@class='AD']//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private WebElement sendBtnInDialog;

    public void writeNewMail(String address, String theme, String mailText) {
        newMailAddressInput.sendKeys(address);
        newMailThemeInput.sendKeys(theme);
        newMailBodyInput.sendKeys(mailText);
    }

    public void closeNewMailDialog() {
        closeNewMailDialogBtn.click();
    }


    public void sendMailFromDialog() {
        sendBtnInDialog.click();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}
