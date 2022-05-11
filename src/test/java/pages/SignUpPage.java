package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignUpPage {


    public SignUpPage(){
        PageFactory.initElements( Driver.getDriver(), this);
    }


    @FindBy (id = "inputfirstname4")
    public  WebElement first;

    @FindBy (name = "last_name")
    public  WebElement last;


    @FindBy (xpath = "//input[@placeholder='Email address']")
    public  WebElement email;



    @FindBy (id = "exampleInputPassword1")
    public  WebElement pass;



    @FindBy (id = "register")
    public  WebElement registerButton;

}
