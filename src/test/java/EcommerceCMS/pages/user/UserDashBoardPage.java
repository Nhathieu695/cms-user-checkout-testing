package EcommerceCMS.pages.user;

import com.hieutester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class UserDashBoardPage extends UserBasePage {

    private By headerDashBoardPage = By.xpath("//h1[normalize-space()='Dashboard']");

    @Step("Action verify to dashboard page")
    public void verifyToDashBoardPage(){
        Assert.assertTrue(WebUI.checkElementExist(headerDashBoardPage),"the dashboard page is not display");
        Assert.assertEquals(WebUI.getElementText(headerDashBoardPage),"Dashboard","The dashboard header page not match.");
    }
}
