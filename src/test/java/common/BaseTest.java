package common;

import com.hieutester.drivers.DriverManager;
import com.hieutester.helpers.PropertiesHelper;
import com.hieutester.utils.LogUtils;
import listener.ITestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Listeners(ITestListeners.class)
public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("edge") String browser) {
        WebDriver driver = setupDriver(browser);
        DriverManager.setDriver(driver);

        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    public WebDriver setupDriver(String browser) {
        WebDriver driver;

        PropertiesHelper.loadAllFiles();

        switch (browser.trim().toLowerCase()) {
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

    public WebDriver initChrome() {
        // ✅ Thêm ChromeOptions để tắt popup Google Password Manager
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false); //Turn off Save Address popup
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");


        options.setAcceptInsecureCerts(true);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver initEdge() {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
