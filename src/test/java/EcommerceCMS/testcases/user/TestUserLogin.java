package EcommerceCMS.testcases.user;

import EcommerceCMS.pages.user.UserBasePage;
import EcommerceCMS.pages.user.UserLoginPage;
import common.BaseTest;
import dataproviders.DataProviderFactory;
import io.qameta.allure.Step;
import listener.ITestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ITestListeners.class)
public class TestUserLogin extends BaseTest {
    UserLoginPage userLoginPage;
    @Step("Login with data success")
    @Test(priority = 1, dataProvider = "data_login_success_from_excel", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String password){
        userLoginPage = new UserLoginPage();
        userLoginPage.loginCMS(email, password);
        userLoginPage.verifyLoginSuccess();
    }
    @Step("Login with email ivalid")
    @Test(priority = 2, dataProvider = "data_login_fail_with_invalid_email_from_excel", dataProviderClass = DataProviderFactory.class)
    public void loginFailWithEmailInvalid(String email, String password){
        userLoginPage = new UserLoginPage();
        userLoginPage.loginCMS(email, password);
        userLoginPage.verifyLoginFail();
    }

    @Step("Login with password ivalid")
    @Test(priority = 3, dataProvider = "data_login_fail_with_invalid_password_from_excel", dataProviderClass = DataProviderFactory.class)
    public void loginFailWithPasswordInvalid(String email, String password){
        userLoginPage = new UserLoginPage();
        userLoginPage.loginCMS(email, password);
        userLoginPage.verifyLoginFail();
    }
}
