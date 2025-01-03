package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {
    private static DriverSingleton instanceofSingletonBrowserClass = null;
    private  RemoteWebDriver driver;

    private  DriverSingleton() throws MalformedURLException {
            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(BrowserType.CHROME);
            driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),capabilities);
            driver.manage().window().maximize();
        }
    public static DriverSingleton getInstanceofSingletonBrowserClass() throws MalformedURLException {
        if(instanceofSingletonBrowserClass==null){
            instanceofSingletonBrowserClass= new DriverSingleton();
       }
        return instanceofSingletonBrowserClass;
    }
    public RemoteWebDriver getDriver() {
        return driver;

    }
}