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

    @FindBy(xpath = "//div[@role='banner']//a[@class='gb_8 gb_Ja gb_M gb_Ea']")
    private WebElement accountManagementLink;

    @FindBy(xpath = "//div[@role='banner']//a[@class='gb_Qc gb_Xc gb_ta']")
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
