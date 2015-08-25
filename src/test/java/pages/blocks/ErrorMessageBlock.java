package pages.blocks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Dauren_Altynbekov on 8/25/2015.
 */
@FindBy(xpath = "//div[@class='Kj-JD']")
public class ErrorMessageBlock extends HtmlElement {

    @FindBy(xpath = "//div[@class='Kj-JD-Jz']")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[@class='J-at1-auR']")
    private Button agreedBtn;

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public void closeErrorMessage() {
        agreedBtn.click();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}
