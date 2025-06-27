package EcommerceCMS.pages.user;

import com.hieutester.drivers.DriverManager;
import com.hieutester.keywords.WebUI;
import com.hieutester.utils.LogUtils;
import common.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class UserBasePage extends BaseTest {

    private static By buttonHome = By.xpath("//a[contains(text(),'Home')]");
    private static By searchProduct = By.xpath("//input[@id='search']");
    private static By buttonSearch = By.xpath("//input[@id='search']/parent::div/child::div/button");
    private static By headerPopup = By.xpath("//b[normalize-space()='Website Demo']");
    private static By buttonClosePopup = By.xpath("//b[normalize-space()='Website Demo']/following::button[@data-value='removed']");
    private static By buttonLoginHomePage = By.xpath("//a[.='Login']");

    @Step("Click button login homepage")
    public static void clickButtonLoginHomePage(){
        WebUI.clickElement(buttonLoginHomePage);
    }

    @Step("Click button close Popup ")
    public static void clickButtonClosePopup(){
        if (WebUI.isElementDisplayed(headerPopup)) {

            WebUI.clickElement(buttonClosePopup);
        }
    }

    @Step("Search product : {0}")
    public static void  searchProduct(String product){
        WebUI.clearText(searchProduct);
        WebUI.setTextAndKey(searchProduct, product, Keys.ENTER);

    }
}
