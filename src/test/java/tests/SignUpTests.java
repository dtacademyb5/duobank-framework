package tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignInPage;
import pages.SignUpPage;
import utilities.PropertyReader;

public class SignUpTests extends TestBase{





    @Test
    public void signUpUser() throws InterruptedException {
        // Page Object Model - design pattern for automation framework


        SignInPage signInPage = new SignInPage();
        signInPage.signUpLink.click();

        driver.findElement(By.id("inputfirstname4")).sendKeys("John");
        driver.findElement(By.name("last_name")).sendKeys("Doe");
        String str = new Faker().internet().emailAddress();
        driver.findElement(By.xpath("//input[@placeholder='Email address']")).sendKeys(str);
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("jd12345");
        driver.findElement(By.id("register")).click();

        Thread.sleep(2000L);
        Assert.assertTrue(driver.getPageSource().contains("Registration Successful"));


    }


    @Test
    public void signUpUserPageObjectModel() throws InterruptedException {
        // Page Object Model - design pattern for automation framework


        SignInPage signInPage = new SignInPage();
        signInPage.signUpLink.click();

        SignUpPage signUpPage =  new SignUpPage();

        signUpPage.first.sendKeys("John");
        signUpPage.last.sendKeys("Doe");
        String str = new Faker().internet().emailAddress();
        signUpPage.email.sendKeys(str);
        signUpPage.pass.sendKeys("jd12345");
        signUpPage.registerButton.click();

        Thread.sleep(2000L);
        Assert.assertTrue(driver.getPageSource().contains("Registration Successful"));


    }

    @Test
    public void signUpUserNegative() throws InterruptedException {
        // Page Object Model - design pattern for automation framework


        SignInPage signInPage = new SignInPage();
        signInPage.signUpLink.click();

        SignUpPage signUpPage =  new SignUpPage();

        signUpPage.first.sendKeys("John");
        signUpPage.last.sendKeys("Doe");

        signUpPage.email.sendKeys("cbhsvdsvc");  // incorrect
        signUpPage.pass.sendKeys("jd12345");
        signUpPage.registerButton.click();

        Thread.sleep(2000L);
        Assert.assertFalse(driver.getPageSource().contains("Registration Successful"));


    }


}
