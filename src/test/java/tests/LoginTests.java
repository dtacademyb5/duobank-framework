package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignInPage;
import utilities.PropertyReader;

public class LoginTests extends TestBase {

    @Test
    public void positiveLogin(){

        SignInPage signInPage =  new SignInPage();
        signInPage.emailLogin.sendKeys(PropertyReader.readProperty("email"));
        signInPage.passLogin.sendKeys(PropertyReader.readProperty("pass"));

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
