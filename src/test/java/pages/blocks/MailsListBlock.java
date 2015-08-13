package pages.blocks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
@FindBy(xpath = "//div[@class='ae4 UI']")
public class MailsListBlock extends HtmlElement {
    @FindBy(xpath = "//table[@class='F cf zt']//tr[1]//td[@class='xY a4W']//div[@class='y6']/span")
    private WebElement lastMailThemeInCategoryLabel;

    @FindBy(xpath = "//table[@class='F cf zt']//tr[1]")
    private WebElement lastMailInCategoryLink;

    public String getLastMailThemeInCategory() {
        return lastMailThemeInCategoryLabel.getText();
    }

    public void openLastMailInCategory() {
        lastMailInCategoryLink.click();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}
