package pages.blocks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dauren_Altynbekov on 8/13/2015.
 */
@FindBy(xpath = "//div[@class='ae4 UI']")
public class MailsListBlock extends HtmlElement {
    @FindBy(xpath = "//div[@class='BltHke nH oy8Mbf' and @role='main']//div[@class='y6']/span[1]")
    private WebElement lastMailThemeInCategoryLabel;

    @FindBy(xpath = "//table[@class='F cf zt']//tr[1]")
    private WebElement lastMailInCategoryLink;

    @FindBy(xpath = "//div[@class='BltHke nH oy8Mbf' and @role='main']//tr[@class='zA yO']//div[@class='y6']/span[1]")
    private List<WebElement> mailThemesList;

    public WebElement getLastMailThemeInCategory() {
        return lastMailThemeInCategoryLabel;
    }

    public void openLastMailInCategory() {
        lastMailInCategoryLink.click();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }

    public List<WebElement> getMailsWithTheme(String mailTheme) {
        List<WebElement> requiredWebElements = new ArrayList<WebElement>();
        for (int i = 0; i < mailThemesList.size(); i++) {
            if (mailThemesList.get(i).getText().equals(mailTheme)) {
                requiredWebElements.add(mailThemesList.get(i));
            }
        }
        return requiredWebElements;
    }

    public List<WebElement> getMailList() {
        return mailThemesList;
    }
}
