package pages.blocks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * Created by Dauren_Altynbekov on 8/18/2015.
 */
@FindBy(xpath = "//*[@id='google-feedback-wizard']")
public class HelpDialogBlock extends HtmlElement {

    @FindBy(id = "search-box")
    private WebElement searchBox;

    @FindBy(xpath = "//ul[@role = 'listbox']//div[@class='ghp-autocomplete-label']")
    private List<WebElement> optionsList;

    public boolean isHaveThisOption(String option) {
        boolean isHaveOption = false;
        isHaveOption = optionsList.iterator().next().getText().equalsIgnoreCase(option);
        return isHaveOption;
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }

    public void search(String searchString) {
        searchBox.sendKeys(searchString);
    }
}
