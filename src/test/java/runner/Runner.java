package runner;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Dauren_Altynbekov on 8/18/2015.
 */
public class Runner {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        TestNG testNG = new TestNG();
        for (XmlSuite suite : new Parser("./testng.xml").parseToList()) {
            testNG.setCommandLineSuite(suite);
        }
        testNG.run();
    }
}
