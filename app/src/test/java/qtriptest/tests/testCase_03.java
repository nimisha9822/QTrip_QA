
    // @BeforeSuite(alwaysRun=true)
    // public static void createDriver() throws MalformedURLException {
    //     System.out.println("running before suite");
    //     Singleton singleton = new Singleton();
    //     driver = singleton.getDriver();
    // }

    package qtriptest.tests;

    import java.net.MalformedURLException;
    import java.sql.Date;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.remote.RemoteWebDriver;
    import org.testng.Assert;
    import org.testng.annotations.*;
    import qtriptest.DP;
    import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.*;
    import org.testng.annotations.Test;
 
    public class testCase_03 {
        static RemoteWebDriver driver;
        static ExtentReports report;
        static ExtentTest test;
    
        @BeforeTest
        public static void createDriver() throws MalformedURLException {
            System.out.println(" running before suite");
           
            // Singleton singleton = new Singleton();
            // driver = singleton.getDriver();
            driver = DriverSingleton.getInstanceofSingletonBrowserClass().getDriver(); 
            ReportSingleton rs1 = ReportSingleton.getInstanceOfSingletonReportClass();
            report = rs1.getReport();
             test = report.startTest("ExtentDemo");
        }
    
        @Test(description = "Verify that adventure booking and cancellation works fine", dataProvider = "data-provider", dataProviderClass = DP.class ,  priority = 3, groups = {"Booking flow"})
       
        public static void TestCase03(String NewUserName, String Password, String SearchCity, String AdventureName, String GuestName, String Date, String count) throws Exception {
            
            System.out.println("data from excel = " + Date);

            HomePage home = new HomePage(driver);
            home.gotoHomePage();
            home.navigateToRegisterpage();

            RegisterPage register = new RegisterPage(driver);
            register.registerNewUser(NewUserName, Password, true);
            String username = register.generatedUsername;

            LoginPage Login = new LoginPage(driver);
            Login.performLogin(username, Password);
            Thread.sleep(3000);

            home.searchCity(SearchCity);
            home.selectCity(SearchCity);

            AdventurePage adventures = new AdventurePage(driver);
            adventures.selectAdventure(AdventureName);
            Thread.sleep(3000);

            AdventureDetailsPage adp = new AdventureDetailsPage(driver);
            adp.bookAdventure(GuestName, Date, Integer.parseInt(count));
            Thread.sleep(3000);
            
            HistoryPage history = new HistoryPage(driver);
            history.gotoHistoryPage();
            Thread.sleep(3000);
            System.out.println("Current Transaction ID: " + history.GetReservations());
            var reservations = history.getReservationCount();
            Assert.assertTrue((reservations==1), "Failure while verifying the number of reservations");
            home.logOutUser();
        }
    
        @AfterTest
        public static void quitDriver() {
            System.out.println("quit()");
            driver.quit();
        }
    }
