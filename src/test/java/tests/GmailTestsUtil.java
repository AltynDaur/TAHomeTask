package tests;

import driver.Driver;
import pages.GmailInboxPage;
import pages.GmailStartPage;

/**
 * Created by Dauren_Altynbekov on 8/24/2015.
 */
public class GmailTestsUtil {

    public static final String TESTING_EMAIL = "autodaurtest@gmail.com";
    public static final String TESTING_EMAIL_PASSWORD = "autodaurtest1";

    private static GmailStartPage mailStartPage = new GmailStartPage(Driver.getDriver());

    public static GmailInboxPage login() {
        mailStartPage.openPage();
        return mailStartPage.login(TESTING_EMAIL, TESTING_EMAIL_PASSWORD);
    }
}
