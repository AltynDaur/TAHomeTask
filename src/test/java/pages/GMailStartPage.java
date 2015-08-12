package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dauren_Altynbekov on 8/12/2015.
 */
public class GMailStartPage {
    public static final String HTTP_GMAIL_COM = "http://gmail.com";
    private WebDriver driver;

    @FindBy(id="Email")
    private WebElement emailInput;

    @FindBy(id="next")
    private WebElement nextToPasswdBtn;

    @FindBy(xpath="//input[contains(@type,'password')]")
    private WebElement passwdInput;

    @FindBy(id="signIn")
    private WebElement signInBtn;

    public GMailStartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public void openPage(){
        this.driver.get(HTTP_GMAIL_COM);
    }

    public void login(String email, String password){
        emailInput.sendKeys(email);
        nextToPasswdBtn.click();
        passwdInput.sendKeys(password);
        signInBtn.click();
    }
}
