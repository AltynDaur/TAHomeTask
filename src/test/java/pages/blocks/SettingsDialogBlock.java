package pages.blocks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Dauren_Altynbekov on 8/18/2015.
 */
@FindBy(xpath = "//div[@class='SK AX']")
public class SettingsDialogBlock extends HtmlElement {
    @FindBy(xpath = "//div[@class='J-N hH']")
    private WebElement helpBtn;

    public void openHelp() {
        helpBtn.click();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}
