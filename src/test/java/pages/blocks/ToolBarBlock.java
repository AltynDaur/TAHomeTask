package pages.blocks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Dauren_Altynbekov on 8/18/2015.
 */
@FindBy(xpath = "//div[@class='Cr aqJ']")
public class ToolBarBlock extends HtmlElement {
    @FindBy(xpath = "//div[@class='T-I J-J5-Ji ash T-I-ax7 L3']")
    private Button settingsBtn;

    @FindBy(xpath = "//div[@class='iH']/div/div[1]/div")
    private Button backToMailListBtn;

    private SettingsDialogBlock settingsDialog;

    public void openSettingsDialog() {
        settingsBtn.click();
    }

    public void openHelpDialog() {
        settingsDialog.openHelp();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }

    public void backToMailList() {
        backToMailListBtn.click();
    }
}
