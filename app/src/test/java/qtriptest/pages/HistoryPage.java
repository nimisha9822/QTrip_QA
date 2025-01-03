
package qtriptest.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class HistoryPage {
    RemoteWebDriver driver;

    public HistoryPage(RemoteWebDriver driver) {
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    // @FindBy(xpath = "//*[@id='navbarNavDropdown']/ul/li[2]/a")
    // WebElement history;

    //not needed as we already have the gotoHistoryPage 
    
    @FindBy(xpath = "//tbody//tr//th")
    WebElement  transaction;
    @FindBy(xpath = "//button[@class='cancel-button']")
    WebElement cancel;
    @FindBy(xpath ="//div[@id='no-reservation-banner']")
    WebElement noReservation;

    public void gotoHistoryPage() {
                driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html");
    }
    
    public String GetReservations() throws InterruptedException{
        // history.click();
        // Thread.sleep(3000);
        List<WebElement> transactionId = driver.findElements(By.xpath("//tbody//tr//th"));
        return transactionId.get(0).getText();
    }

    public int getReservationCount() throws InterruptedException {
        // history.click();
        // Thread.sleep(3000);
        List<WebElement> transactionId = driver.findElements(By.xpath("//tbody//tr//th"));
        return transactionId.size(); // Return the number of transaction IDs found
    }

    public void CancelReservations() throws InterruptedException{
        cancel.click();
        Thread.sleep(5000);
        Assert.assertTrue(noReservation.isDisplayed(),"reserved");
    }
}
