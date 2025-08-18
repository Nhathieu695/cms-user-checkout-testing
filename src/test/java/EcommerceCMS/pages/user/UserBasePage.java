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
    private static By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");
    private static By menuManageProfile = By.xpath("(//div[@class='sidemnenu mb-3']/descendant::ul/li/a/span[.='Manage Profile'])[1]");
    private static By productAfterSearch = By.xpath("//div[normalize-space()='Products']/following-sibling::ul/li[1]/a");

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
    public UserListProductPage  searchProductWithEnter(String product){
        WebUI.clearText(searchProduct);
        WebUI.setTextAndKey(searchProduct, product, Keys.ENTER);
        WebUI.sleep(1);
        return new UserListProductPage();
    }

    @Step("Search product : {0}")
    public static void  searchProduct(String product){
        WebUI.clearText(searchProduct);
        WebUI.setText(searchProduct, product);
        WebUI.sleep(2);

    }

    @Step("Click button my panel")
    public static void clickButtonMyPanel(){
        WebUI.clickElement(buttonMyPanel);
        WebUI.waitForPageLoaded();
    }

    @Step("Click menu manage profile")
    public  UserManageProfilePage clickMenuManageProfile(){
        WebUI.clickElement(menuManageProfile);
        WebUI.waitForPageLoaded();

        return new UserManageProfilePage();
    }

    @Step("Click product after search")
    public UserProductDetailPage clickProductAfterSearch(){
        WebUI.clickElement(productAfterSearch);
        return new UserProductDetailPage();
    }


}
