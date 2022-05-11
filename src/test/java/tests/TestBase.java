package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.Driver;
import utilities.PropertyReader;

public class TestBase {

    WebDriver driver;


    @BeforeMethod
    public void beforeMethod(){
        WebDriverManager.chromedriver().setup();
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.get(PropertyReader.readProperty("url"));
    }

    @AfterMethod
    public void afterMethod(){

        Driver.quitDriver();

    }
}
