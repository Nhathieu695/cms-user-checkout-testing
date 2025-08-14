package EcommerceCMS.testcases.user;

import EcommerceCMS.pages.user.UserDashBoardPage;
import EcommerceCMS.pages.user.UserLoginPage;
import EcommerceCMS.pages.user.UserManageProfilePage;
import common.BaseTest;
import dataproviders.DataProviderFactory;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestUpdateUserProfile extends BaseTest {

    UserLoginPage userLoginPage;
    UserManageProfilePage userManageProfilePage;
    UserDashBoardPage userDashBoardPage;

    @Epic("Regression")
    @Feature("User Profile Management")
    @Story("Update User Profile")
    @Owner("Hieu")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 0)
    @Description("This Test Case Will Update New Info to Profile")
    public void testUpdateUserProfile(){
        userLoginPage = new UserLoginPage();
        userDashBoardPage = userLoginPage.loginCMS();
        userDashBoardPage.verifyToDashBoardPage();
        userManageProfilePage =  userDashBoardPage.clickMenuManageProfile();
        userManageProfilePage.updateUserProfile();
        userManageProfilePage.verifyUpdateProfileSuccess();
        userManageProfilePage.verifyUpdateProfile();


    }

    @Epic("Regression")
    @Feature("User Profile Management")
    @Story("Add Address User Profile")
    @Owner("Hieu")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 0, dataProvider = "data_provider_add_new_address_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    @Description("This Test Case Will Add Address User to Profile")
    public void testAddAddressProfile(Hashtable< String, String > data){
        userLoginPage = new UserLoginPage();
        userDashBoardPage = userLoginPage.loginCMS();
        userDashBoardPage.verifyToDashBoardPage();
        userManageProfilePage =  userDashBoardPage.clickMenuManageProfile();
        userManageProfilePage.addNewAddress(data);
        userManageProfilePage.verifyInfoAddress("201 Nguyễn Thái Sơn", 1);
    }

    @Epic("Regression")
    @Feature("User Profile Management")
    @Story("Edit Address User Profile")
    @Owner("Hieu")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 0)
    @Description("This Test Case Will Edit Address User to Profile")
    public void testEditAddressProfile(){
        userLoginPage = new UserLoginPage();
        userDashBoardPage = userLoginPage.loginCMS();
        userDashBoardPage.verifyToDashBoardPage();
        userManageProfilePage =  userDashBoardPage.clickMenuManageProfile();
        userManageProfilePage.editAddress("Lê Văn Việt", 1);
        userManageProfilePage.verifyEditSuccess();
        userManageProfilePage.verifyEditInfoAddress("100 Nguyễn Văn Nghi", 1);
    }

    @Epic("Regression")
    @Feature("User Profile Management")
    @Story("Delete Address User Profile")
    @Owner("Hieu")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 0)
    @Description("This Test Case Will Delete Address User to Profile")
    public void testDeleteAddressProfile(){
        userLoginPage = new UserLoginPage();
        userDashBoardPage = userLoginPage.loginCMS();
        userDashBoardPage.verifyToDashBoardPage();
        userManageProfilePage =  userDashBoardPage.clickMenuManageProfile();
        userManageProfilePage.deleteInfoAddress("150 Phan Văn Trị");
        userManageProfilePage.verifyDeleteAddress("150 Phan Văn Trị");
    }
}
