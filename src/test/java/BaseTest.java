import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    final private String driverType = BrowserType.CHROME;


    @Before
    public void startDriver() {
        if(driverType == BrowserType.CHROME) {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
        } if (driverType == BrowserType.FIREFOX){
            System.setProperty("webdriver.gecko.driver", "C:\\firefox\\geckodriver.exe");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        }
    }

    @After
    public void closeDriver() {
        driver.quit();
    }


}
