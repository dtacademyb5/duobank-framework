package ui.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.Driver;
import utilities.PropertyReader;
import utilities.SeleniumUtils;

import java.lang.reflect.Method;

public class TestBase {

    WebDriver driver;

    protected static ExtentReports reporter;
    protected static ExtentSparkReporter htmlReporter;
    protected static ExtentTest logger;


     @BeforeSuite
     public void setupReport(){
         reporter =  new ExtentReports();




         String path =  System.getProperty("user.dir") + "/target/extentReports/index.html";

         htmlReporter = new ExtentSparkReporter(path);

         htmlReporter.config().setReportName("DUOBANK AUTOMATION TESTS");

         reporter.attachReporter(htmlReporter);

         // Configuration settings
         reporter.setSystemInfo("Tester", "John Doe");
         reporter.setSystemInfo("Environment", "TEST_ENV");
         reporter.setSystemInfo("Browser", PropertyReader.readProperty("browser"));
     }



    @AfterSuite
    public void tearDownReport(){
         reporter.flush();
    }



    @BeforeMethod
    public void beforeMethod(Method method){
        WebDriverManager.chromedriver().setup();
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.get(PropertyReader.readProperty("url"));
        logger = reporter.createTest("TestGithub Case: " + method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult){

         if(testResult.getStatus() == ITestResult.SUCCESS){
             logger.pass("TestGithub passed: " + testResult.getName());
         }else if(testResult.getStatus() == ITestResult.SKIP){
             logger.skip("TestGithub skipped: " + testResult.getName());
         }else if(testResult.getStatus() == ITestResult.FAILURE){
             logger.fail("TestGithub failed: " + testResult.getName());
             logger.fail(testResult.getThrowable());
             logger.addScreenCaptureFromPath(SeleniumUtils.getScreenshotOnFailure());



//             TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
//             File source = ts.getScreenshotAs(OutputType.FILE);
//             String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//             String fileName = "failed" + date + ".png";
//             String target = System.getProperty("user.dir") + "/target/extentReports/" + fileName;
//             File finalDestination = new File(target);
//             try {
//                 FileUtils.copyFile(source, finalDestination);
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
             // logger.addScreenCaptureFromPath(fileName);

         }



        Driver.quitDriver();

    }
}
