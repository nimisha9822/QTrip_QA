package qtriptest.tests;

import static org.testng.Assert.*;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import qtriptest.pages.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;

public class testCase_02 {

    static RemoteWebDriver driver;
    static ExtentReports report;
    static ExtentTest test;

    @BeforeTest
    public static void createDriver() throws MalformedURLException {
        System.out.println("running before suite");
       
        // Singleton singleton = new Singleton();
        // driver = singleton.getDriver();
        driver = DriverSingleton.getInstanceofSingletonBrowserClass().getDriver(); 
        ReportSingleton rs1 = ReportSingleton.getInstanceOfSingletonReportClass();
        report = rs1.getReport();
        test = report.startTest("ExtentDemo");
    }

    @Test(description = "Verify that Search and filters work fine" , dataProvider ="data-provider" , dataProviderClass = DP.class ,  priority = 2, groups = {"Search and Filter Flow"})
    public static void TestCase02(String CityName, String Category_Filter, String DurationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws Exception {
        
        HomePage home = new HomePage(driver);
        home.gotoHomePage();
        Thread.sleep(1000);
        home.searchCity("nowhere");
        Thread.sleep(1000);
        assertTrue(home.isNoCityFound());
        home.searchCity(CityName);
        home.selectCity(CityName);
        AdventurePage adventures = new AdventurePage(driver);
        adventures.setCategoryValue(Category_Filter);
        adventures.setFilterValue(DurationFilter);
        assertTrue(adventures.getResultCount() == Integer.parseInt(ExpectedFilteredResults),"Mismatch in result count Expected vs actual");
        adventures.clearFilters();
        assertTrue(adventures.getResultCount() == Integer.parseInt(ExpectedUnFilteredResults),"Mismatch in result count Expected vs actual after clearing filters");
       
    }

    @AfterTest
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
    }
}

