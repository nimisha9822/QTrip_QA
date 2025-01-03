package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class HomePage {
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement register;
    @FindBy(xpath = "//a[text()='Login Here']")
    WebElement loginBtn;
    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutBtn;
    @FindBy(xpath="//button[text()='Register Now']")
    WebElement registerNow;
    @FindBy(xpath = "//input[@class='hero-input']")
    WebElement searchCity;
    @FindBy(xpath = "//h5[text()='No City found']")
    WebElement noCityFound;
    @FindBy(xpath="//ul[@id='results']")
    WebElement autoText;

    public HomePage(RemoteWebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);

    }

    public void gotoHomePage() throws InterruptedException {
        SeleniumWrapper.navigate(this.driver, this.url);
    }

    public void clickRegister() {
        registerNow.click();
    }
    
    // public void logOutUser(){
    //     logoutBtn.click();
    //     Assert.assertTrue(loginBtn.isDisplayed(), "unable to logout");
    // }

    public void logOutUser() throws InterruptedException { 
        SeleniumWrapper.click(logoutBtn,this.driver);
    }

    public void navigateToHomepage(){
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
    }
    public void navigateToRegisterpage(){
        register.click();
        Assert.assertTrue(registerNow.isDisplayed(),"unable to navigate to register page");
    }
    public void SearchCity(String city) throws InterruptedException{
        Thread.sleep(2000);
        searchCity.sendKeys(city);
        Thread.sleep(5000);
        SoftAssert soft=new SoftAssert();
        soft.assertFalse(driver.findElement(By.id("results")).getText()==city,"City found");
        searchCity.clear();
        soft.assertAll();
    }
    public void assertAutoCompleteText(String city) throws InterruptedException {
        searchCity.sendKeys(city);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a/li")).click();
     }
     public void searchCity(String cityName) {
        searchCity.clear();
        SeleniumWrapper.sendKeys(searchCity, cityName);
    }

    public void selectCity(String cityName) throws Exception {
        Thread.sleep(2000);
        By by = new By.ByXPath(String.format("//li[@id='%s']", cityName.toLowerCase()));
        WebElement x = SeleniumWrapper.findElementWithRetry(this.driver, by, 3);
        // WebElement city = driver.findElement(By.xpath(String.format("//li[@id='%s']", cityName.toLowerCase())));
        SeleniumWrapper.click(x,this.driver);
    }

    public boolean isNoCityFound() {
        return noCityFound.isDisplayed();
    }

    public boolean isUserLoggedIn() {
        return true;
    }
}
