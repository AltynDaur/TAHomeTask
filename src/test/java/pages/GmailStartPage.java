package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dauren_Altynbekov on 8/12/2015.
 */
public class GmailStartPage implements Page {

    public static final String HTTP_GMAIL_COM = "http://gmail.com";
    private WebDriver driver;

    @FindBy(xpath = "//div[@id='gaia_firstform']//input[@id='Email']")
    private WebElement emailInput;

    @FindBy(id = "next")
    private WebElement nextToPasswdBtn;

    @FindBy(id = "Passwd")
    private WebElement passwdInput;

    @FindBy(id = "signIn")
    private WebElement signInBtn;

    public GmailStartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public GmailStartPage() {

    }

    public void openPage() {
        this.driver.get(HTTP_GMAIL_COM);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public GmailInboxPage login(String email, String password) {
        emailInput.sendKeys(email);
        nextToPasswdBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(passwdInput));
        passwdInput.sendKeys(password);
        signInBtn.click();
        return new GmailInboxPage(this.driver);
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
