package EcommerceCMS.pages.user;

import com.hieutester.drivers.DriverManager;
import com.hieutester.helpers.PropertiesHelper;
import com.hieutester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class UserLoginPage {

    private By headerLogin = By.xpath("//h1[normalize-space()='Login to your account.']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[contains(@class, 'alert-danger')]/span");

    public void enterEmail(String email) {
        WebUI.setText(inputEmail, email);
   }

    public void enterPassWord(String password) {
        WebUI.setText(inputPassword, password);
    }

    public void clicklLoginButton(){
        WebUI.clickElement(buttonLogin);
    }

    public void verifyLoginSuccess(){
        WebUI.assertNotContains(DriverManager.getDriver().getCurrentUrl(), "login", "Fail vẫn còn ở trang Login");
    }

    public void verifyLoginFail() {
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("login"), "FAIL. Không còn ở trang Login");
        Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Error message NOT displays");
        //Assert.assertEquals(driver.findElement(errorMessage).getText(), "Invalid login credentials", "Content of error massage NOT match.");
        WebUI.assertEquals(DriverManager.getDriver().findElement(errorMessage).getText(), "Invalid login credentials", "Content of error massage NOT match.");
    }

    public void loginCMS(String email, String password) {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        UserBasePage.clickButtonClosePopup();
        UserBasePage.clickButtonLoginHomePage();
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);

    }

    public UserDashBoardPage loginCMS() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        UserBasePage.clickButtonClosePopup();
        UserBasePage.clickButtonLoginHomePage();
        WebUI.setText(inputEmail, PropertiesHelper.getValue("EMAIL"));
        WebUI.setText(inputPassword, PropertiesHelper.getValue("PASSWORD"));
        WebUI.clickElement(buttonLogin);
        verifyLoginSuccess();
        return new UserDashBoardPage();
    }
}
