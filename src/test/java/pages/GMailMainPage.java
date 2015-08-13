package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dauren_Altynbekov on 8/12/2015.
 */
public class GMailMainPage implements Page{
    public static final String GMAIL_MAIN_PAGE = "https://mail.google.com/mail/#inbox";
    private WebDriver driver;

    @FindBy(className = "vO")
    private WebElement newMailAddressInput;

    @FindBy(className = "aoT")
    private WebElement newMailThemeInput;

    @FindBy(xpath = "//div[@class='AD']//div[@role='textbox']")
    private WebElement newMailBodyInput;

    @FindBy(className = "Ha")
    private WebElement closeNewMailDialogBtn;

    @FindBy(xpath = "//table[@class='F cf zt']//tr[1]//td[@class='xY a4W']//div[@class='y6']/span")
    private WebElement lastMailThemeInCategoryLabel;

    @FindBy(xpath = "//table[@class='F cf zt']//tr[1]")
    private WebElement lastMailInCategoryLink;

    @FindBy(xpath = "//div[@class='AD']//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private WebElement sendBtnInDialog;

    public GMailMainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public void openPage() {
        this.driver.get(GMAIL_MAIN_PAGE);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }



    public void writeNewMail(String address, String theme, String mailText){
        newMailAddressInput.sendKeys(address);
        newMailThemeInput.sendKeys(theme);
        newMailBodyInput.sendKeys(mailText);
    }

    public void closeNewMailDialog(){
        closeNewMailDialogBtn.click();
    }



    public String getLastMailThemeInCategory(){
        return lastMailThemeInCategoryLabel.getText();
    }

    public void openLastMailInCategory() {
        lastMailInCategoryLink.click();
    }

    public String[] getMailParametersFromDialog(){
        String[] mailParameters = new String[3];
        System.out.print(newMailAddressInput.getText());
        mailParameters[0] = newMailAddressInput.getText();
        mailParameters[1] = newMailThemeInput.getText();
        mailParameters[2] = newMailBodyInput.getText();
        return mailParameters;
    }

    public void sendMailFromDialog() {
        sendBtnInDialog.click();
    }
}
