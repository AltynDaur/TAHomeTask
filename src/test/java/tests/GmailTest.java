package tests;

import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GMailMainPage;
import pages.GMailStartPage;

import java.util.concurrent.TimeUnit;


/**
 * Created by Dauren_Altynbekov on 8/12/2015.
 */
public class GmailTest {

    public static final String MAIL_ADDRESS = "altyndaur@gmail.com";
    public static final String MAIL_THEME = "Nice test";
    public static final String MAIL_BODY = "You're the best!";
    WebDriver driver = new FirefoxDriver();
    GMailStartPage mailStartPage = new GMailStartPage(driver);
    GMailMainPage mailMainPage = new GMailMainPage(driver);

    @Test
    public void oneCanLoginGmail(){
        mailStartPage.openPage();
        mailMainPage = mailStartPage.login("autodaurtest@gmail.com", "autodaurtest1");
    }

    @Test(dependsOnMethods = {"oneCanLoginGmail"})
    public void isLoginSuccessfully(){
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        Assert.assertEquals(mailMainPage.getCurrentUrl(), "https://mail.google.com/mail/#inbox");
    }

    @Test(dependsOnMethods = {"oneCanLoginGmail"})
    public void oneCanCreateNewMail(){
        mailMainPage.openPage();
        mailMainPage.startNewMail();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='aYF']")).getText(),"Новое сообщение");
    }

    @Test(dependsOnMethods = {"oneCanCreateNewMail"})
    public void oneCanFillMailFields(){
        mailMainPage.writeNewMail(MAIL_ADDRESS, MAIL_THEME, MAIL_BODY);
    }

    @Test(dependsOnMethods = {"oneCanFillMailFields"})
    public void saveNewMailToDraft(){
        mailMainPage.closeNewMailDialog();
    }

    @Test(dependsOnMethods = {"saveNewMailToDraft"})
    public void checkNewMailInDraft(){
        mailMainPage.goToDrafts();
        Assert.assertEquals(mailMainPage.getLastMailThemeInCategory(), MAIL_THEME);
    }

    @Test(dependsOnMethods = {"saveNewMailToDraft"})
    public void checkAllParametersNewMailInDraft(){
        mailMainPage.goToDrafts();
        mailMainPage.openLastMailInCategory();
        Assert.assertEquals(mailMainPage.getMailParametersFromDialog(), new String[]{MAIL_ADDRESS,MAIL_THEME,MAIL_BODY});
    }

    @Test(dependsOnMethods = {"checkAllParametersNewMailInDraft"})
    public void sendMailFromDraft(){
        mailMainPage.sendMailFromDialog();
    }

    @Test(dependsOnMethods = {"sendMailFromDraft"},expectedExceptions = NoSuchElementException.class)
    public void checkMailInDraftAfterSending(){
        mailMainPage.goToDrafts();
        Assert.assertEquals(mailMainPage.getLastMailThemeInCategory(), MAIL_THEME);
    }

    @Test(dependsOnMethods = {"sendMailFromDraft"})
    public void checkSendedMailInSendCategoory(){

    }
}
