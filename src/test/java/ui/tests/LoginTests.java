package ui.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.SignInPage;
import utilities.PropertyReader;

public class LoginTests extends TestBase {

    @Test
    public void positiveLogin(){



        SignInPage signInPage =  new SignInPage();

        logger.info("Sending email");
        signInPage.emailLogin.sendKeys(PropertyReader.readProperty("email"));
        logger.info("Sending password");
        signInPage.passLogin.sendKeys(PropertyReader.readProperty("pass"));

        logger.info("Asserting the result");
        Assert.assertTrue(driver.getTitle().contains("Loan Application"));


    }


    @Test
    public void negativeLogin(){

        SignInPage signInPage =  new SignInPage();
        signInPage.emailLogin.sendKeys(PropertyReader.readProperty("email"));
        signInPage.passLogin.sendKeys("vscdscsdcds"); //wrong pass

        Assert.assertTrue(driver.getTitle().equals("Loan Application"));
    }

}
