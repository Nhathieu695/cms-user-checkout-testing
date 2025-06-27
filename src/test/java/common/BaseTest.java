package common;

import com.hieutester.drivers.DriverManager;
import com.hieutester.helpers.PropertiesHelper;
import com.hieutester.utils.LogUtils;
import listener.ITestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(ITestListeners.class)
public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome")String browser) {
        WebDriver driver = setupDriver(browser);
        DriverManager.setDriver(driver);

        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    public WebDriver setupDriver(String browser){
        WebDriver driver;

        PropertiesHelper.loadAllFiles();

        switch (browser.trim().toLowerCase()){
            case "chrome":
                driver = initChrome();
                LogUtils.info("Run with Chrome browser");
                break;
            case "edge":
                driver = initEdge();
                LogUtils.info("Run with Edge browser");
                break;
            default:
                LogUtils.info("Run with Edge browser (default)");
                driver = initEdge();
                break;
        }
        return driver;
    }

    public WebDriver initChrome(){
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver initEdge(){
        WebDriver driver;
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver() {
        if(DriverManager.getDriver() != null){
            DriverManager.quit();
        }
    }
}
