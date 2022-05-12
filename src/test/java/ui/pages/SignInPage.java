package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignInPage {
    @FindBy (id = "exampleInputPassword1")
    public WebElement passLogin;


    // PageFactory - design pattern based on @FindBy

    public SignInPage(){
        PageFactory.initElements( Driver.getDriver(), this);
    }


    @FindBy (xpath = "//a[@href='register.php']")
    public WebElement signUpLink;

    @FindBy (id = "exampleInputEmail1")
    public WebElement emailLogin;



    // TDD  - test driven development (TestNG)
    // BDD -  behavior driven development (Cucumber)
    

    // Data-Driven-Testing (DDT) - a technique of separating your test script from the data
     // testng - dataprovider
    // cucumber - scenario outline and examples table

}
