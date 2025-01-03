package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegisterPage {
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String generatedUsername;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement register;
    @FindBy(xpath="//button[text()='Register Now']")
    WebElement registerNow;
    @FindBy(xpath="//input[@name='email']")
    WebElement email;
    @FindBy(xpath="//input[@name='password']")
    WebElement passwordtxt;
    @FindBy(xpath="//input[@name='confirmpassword']")
    WebElement confirmpassword;
    @FindBy(xpath="//button[text()='Login to QTrip']")
    WebElement loginToQtrip;

    public RegisterPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    
    public Boolean registerNewUser(String username, String pwd,Boolean makeUsernameDynamic) throws InterruptedException{
        String test_data_username;
        if (makeUsernameDynamic)
            // Concatenate the timestamp to string to form unique timestamp
            test_data_username = username + UUID.randomUUID().toString();
        else
            test_data_username = username;

        SeleniumWrapper.sendKeys(email,test_data_username);
        SeleniumWrapper.sendKeys(passwordtxt,pwd);
        SeleniumWrapper.sendKeys(confirmpassword,pwd);
        SeleniumWrapper.click(registerNow,this.driver);

        this.generatedUsername = test_data_username;

        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login/")));
        } catch (Exception e) {
            return false;
        }

        return this.driver.getCurrentUrl().endsWith("/login");
    }
    }


