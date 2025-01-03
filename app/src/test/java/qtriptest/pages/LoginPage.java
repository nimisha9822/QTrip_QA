// package qtriptest.pages;

// import qtriptest.SeleniumWrapper;
// import java.util.concurrent.TimeUnit;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.openqa.selenium.support.FindBy;
// import org.openqa.selenium.support.PageFactory;
// import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.testng.Assert;

// public class LoginPage {
//     RemoteWebDriver driver;
//     String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

//     @FindBy(xpath ="//input[@type='email']")
//     WebElement email;
//     @FindBy(xpath ="//input[@type='password']")
//     WebElement passwordtxt;
//     @FindBy(xpath = "//button[@type='submit']")
//     WebElement loginBtn;
//     @FindBy(xpath = "//div[text()='Logout']")
//     WebElement logoutBtn;
    
//     public LoginPage(RemoteWebDriver driver){
//         this.driver = driver;
//         PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
//     }
//     public void navigateToLoginPage() {
//         SeleniumWrapper.navigate(this.driver, this.url);
//     }
    
//     public void gotoHomePage() throws InterruptedException {
//         driver.navigate().to("https://qtripdynamic-qa-frontend.vercel.app/pages/home/");
//     } 
    

//     public Boolean performLogin(String username, String password) throws InterruptedException {
//         SeleniumWrapper.sendKeys(email,username);
//         SeleniumWrapper.sendKeys(passwordtxt,password);
//         SeleniumWrapper.click(loginBtn, this.driver);

//         try {
//             WebDriverWait wait = new WebDriverWait(driver, 30);
//             wait.until(ExpectedConditions.or(
//                 ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/")));
//         } catch (Exception e) {
//             return false;
//         }

//         return this.driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/");
//     }
// }

package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qtriptest.*;

public class LoginPage {
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    @FindBy(id="floatingInput")
    // @FindBy(xpath ="//input[@type='email']")
    WebElement email;

    @FindBy(xpath="//input[@id='floatingPassword']")
    WebElement password_text_box;

    @FindBy(xpath="//button[normalize-space()='Login to QTrip']")
    WebElement login_button;


    public LoginPage(RemoteWebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    public void navigateToLoginPage() {
        SeleniumWrapper.navigate(this.driver, this.url);
    }

    public Boolean performLogin(String username, String password) throws InterruptedException {
        SeleniumWrapper.sendKeys(email,username);
        SeleniumWrapper.sendKeys(password_text_box,password);
        SeleniumWrapper.click(login_button, this.driver);

        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/")));
        } catch (Exception e) {
            return false;
        }

        return this.driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/");
    }
}

