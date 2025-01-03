package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class testCase_01 {
    static RemoteWebDriver driver;
    static ExtentReports report;
    static ExtentTest test;
    
    @BeforeTest
    public static void createDriver() throws MalformedURLException {
        System.out.println("running before suite");
        driver = DriverSingleton.getInstanceofSingletonBrowserClass().getDriver();
        ReportSingleton rs1 = ReportSingleton.getInstanceOfSingletonReportClass();
        report=rs1.getReport();
        test = report.startTest("ExtentDemo");
    }
    
     @Test(description = "User Login/Logout", dataProvider="data-provider" , dataProviderClass = DP.class, priority = 1, groups = {"Login Flow"} )
     @Parameters({"Username","Password"})
     public void TestCase01(String Username,String Password) throws InterruptedException{
         HomePage objhome = new HomePage(driver);
         
         objhome.navigateToHomepage();
        
         objhome.navigateToRegisterpage();
    
         RegisterPage objregister = new RegisterPage(driver);
         objregister.registerNewUser( Username,Password,true);
         Thread.sleep(2000);
         LoginPage objlogin = new LoginPage(driver);
         objlogin.navigateToLoginPage();
        
         objlogin.performLogin(objregister.generatedUsername,Password);
         Thread.sleep(2000);
         objhome.logOutUser();
         
     }
       
    @AfterTest
    public static void quitDriver() {

        System.out.println("quit()");
        report.endTest(test);
        driver.quit();
        report.flush();
    }
    
 }
