package EcommerceCMS.testcases.user;

import EcommerceCMS.pages.user.*;
import com.hieutester.helpers.PropertiesHelper;
import common.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

public class TestGetInfoProductToExcel extends BaseTest {

    UserLoginPage userLoginPage;
    UserProductDetailPage userProductDetailPage;
    UserDashBoardPage userDashBoardPage;
    UserListProductPage userListProductPage;
    @Epic("Regression")
    @Feature("User Detail Page")
    @Story("Get Info Product To Excel")
    @Owner("Hieu")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 0)
    @Description("This Test Case Will Get Info Product To Excel")
    public void testGetInfoProductToExcel() {
        userLoginPage = new UserLoginPage();
        UserBasePage userBasePage = new UserBasePage();
        userDashBoardPage = userLoginPage.loginCMS();
        userDashBoardPage.verifyToDashBoardPage();
        userListProductPage = userBasePage.searchProductWithEnter("Laptop gaming");
        userListProductPage.verifyProductInfoFromUrl(2);
        String hrefExpected = userListProductPage.getHrefProductFirst();
        userProductDetailPage = userListProductPage.clickLinkProductFirst();
//        userProductDetailPage = userBasePage.clickProductAfterSearch();
        userProductDetailPage.verifyInfoProductDetail(hrefExpected);
        userProductDetailPage.getProductInfoToExcel(PropertiesHelper.getValue("EXCEL_GetProducts"));
        userProductDetailPage.verifyDataDetailPageWithExcel(12);
    }
}
