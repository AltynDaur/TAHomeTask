package pages.blocks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Dauren_Altynbekov on 8/14/2015.
 */
@FindBy(xpath = "//div[@role='banner']")
public class HeaderBlock extends HtmlElement {

    @FindBy(xpath = "//div[@role='banner']//div[@class='gb_Pa gb_Kb gb_Nd gb_R']//a[@class='gb_b gb_Na gb_R gb_Ja']")
    private WebElement accountManagementLink;

    @FindBy(xpath = "//div[@role='banner']//div[@class='gb_Ua gb_ga']//a[@class='gb_0c gb_7c gb_xa']")
    private WebElement logOutBtn;

    public void openAccountManagementDialog() {
        accountManagementLink.click();
    }

    public void logout() {
        logOutBtn.click();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}
