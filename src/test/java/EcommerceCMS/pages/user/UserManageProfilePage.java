package EcommerceCMS.pages.user;

import com.hieutester.drivers.DriverManager;
import com.hieutester.helpers.ExcelHelper;
import com.hieutester.helpers.PropertiesHelper;
import com.hieutester.helpers.SystemHelper;
import com.hieutester.keywords.WebUI;
import com.hieutester.reports.AllureManager;
import com.hieutester.utils.LogUtils;
import common.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Hashtable;

public class UserManageProfilePage {

    private By headerManageProfile = By.xpath("//h1[normalize-space()='Manage Profile']");
    private By inputName = By.xpath("//input[@placeholder='Your name']");
    private By inputPhone = By.xpath("//input[@placeholder='Your Phone']");
    private By buttonBrowse = By.xpath("//div[contains(text(),'Browse')]");
    private By imageTester = By.xpath("(//div[@title='hieutester.png']//img[contains(@class, 'img-fit')])[1]");
    private By buttonAddFile = By.xpath("//button[normalize-space()='Add Files']");
    private By buttonUploadNew = By.xpath("//a[normalize-space()='Upload New']");
    private By buttonBrowseWithUploadFile = By.xpath("//button[normalize-space()='Browse']");
    private By buttonUpdateProfile = By.xpath("//button[normalize-space()='Update Profile']");
    private By buttonAddNewAddress = By.xpath("//div[@class='alpha-7' and normalize-space()='Add New Address']");
    private By buttonEllipsis = By.xpath(".//button[contains(@class, 'bg-gray')]");
    private By buttonEdit = By.xpath(".//a[normalize-space()='Edit']");
    private By buttonDelete = By.xpath(".//a[normalize-space()='Delete']");
    private By checkFileUpload = By.xpath("//label[text()='Photo']/following::div[contains(@class, 'file-amount')][1]");
    private By checkImageUpload = By.xpath("//span[@class='text-truncate title']");
    private By allertUpdateProfileSuccess = By.xpath("//span[normalize-space(text())='Your Profile has been updated successfully!']");
    private By infoAddressProfile = By.xpath("//h5[normalize-space()='Address']");
    private By addNewAddressProfile = By.xpath("//div[contains(text(),'Add New Address')]/parent::div");
    private By textAreaAddAddress = By.xpath("//label[normalize-space()='Address']/parent::div/following-sibling::div//textarea[@name='address']");
    private By textAreaEditAddress = By.xpath("//div[@id='edit_modal_body']//textarea[@name='address']");
    private By selectCountry = By.xpath("//label[normalize-space()='Country']/parent::div/following-sibling::div/descendant::button");
    private By selectEditCountry = By.xpath("//label[normalize-space()='Country']/parent::div/following-sibling::div/descendant::button[@data-id='edit_country']");
    private By inputSearchCountry = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By itemVietNam = By.xpath("(//span[@class='text'][normalize-space()='Vietnam'])[1]");
    private By selectState = By.xpath("//label[normalize-space()='State']/parent::div/following-sibling::div/descendant::button");
    private By selectEditState = By.xpath("//label[normalize-space()='State']/parent::div/following-sibling::div/descendant::button[@data-id='edit_state']");
    private By inputSearchState = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By itemHaNoiState = By.xpath("(//span[@class='text'][contains(text(),'Hà Nội')])[1]");
    private By selectCity = By.xpath("//label[normalize-space()='City']/parent::div/following-sibling::div/descendant::button");
    private By selectEditCity = By.xpath("(//label[normalize-space()='City']/parent::div/following-sibling::div/descendant::button)[2]");
    private By inputSearchCity = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By itemHaNoiCity = By.xpath("(//span[@class='text'][contains(text(),'Hà Nội')])[2]");
    private By inputPostalCode = By.xpath("//form[@role='form']//div[@class='modal-body']//input[@placeholder='Your Postal Code']");
    private By inputEditPostalCode = By.xpath("//div[@id='edit_modal_body']//input[@name='postal_code']");
    private By inputPhoneAddress = By.xpath("//form[@role='form']//div[@class='modal-body']//input[@name='phone']");
    private By inputEditPhoneAddress = By.xpath("//div[@id='edit_modal_body']//input[@name='phone']");
    private By buttonSaveAddress = By.xpath("//form[@role='form']//div[contains(@class,'modal-body')]//button[@type='submit']");
    private By buttonSaveEditAddress = By.xpath("//div[@id='edit_modal_body']//button[@type='submit']");
    private By allertEditSuccess = By.xpath("//span[normalize-space(text())='Address info updated successfully']");
    @Step("Action verify menu profile page")
    public void verifyToProfilePage(){
        Assert.assertTrue(WebUI.checkElementExist(headerManageProfile),"the manage profile page is not display");
        Assert.assertEquals(WebUI.getElementText(headerManageProfile),"headerManageProfile","The manage profile header page not match.");
    }

    @Step("Action click button update profile")
    public void clickButtonUpdateProfile() {
        if (WebUI.isElementDisplayed(buttonUpdateProfile)) {
            WebUI.scrollToElement(buttonUpdateProfile);
            WebUI.clickElement(buttonUpdateProfile);
        } else {
            LogUtils.error("clickButtonUpdateProfile: click button update profile fail (button update is not displayed)");
            AllureManager.saveTextLog("clickButtonUpdateProfile: click button update profile fail (button update is not displayed)");
        }

    }

    @Step("Action update profile")
    public void updateUserProfile(){
        WebUI.clearText(inputName);
        WebUI.setText(inputName, "Hieu tester");
        WebUI.clearText(inputPhone);
        WebUI.setText(inputPhone, "0906562794");
        WebUI.scrollToElementAtTop(buttonBrowse);
//        WebUI.clickElement(buttonBrowse);
//        WebUI.sleep(1);
//        WebUI.clickElement(buttonUploadNew);
//        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\imageData\\hieutester.png";
//        WebUI.uploadFileWithRobotClass(buttonBrowseWithUploadFile, filePath);
//        WebUI.clickElement(buttonAddFile);
//        WebUI.sleep(1);
        WebUI.clickElement(buttonBrowse);
        WebUI.sleep(1);
        WebUI.clickElement(imageTester);
        WebUI.clickElement(buttonAddFile);
        WebUI.sleep(1);
        Assert.assertEquals(WebUI.getElementText(checkImageUpload), "hieutester", "Image not match");
        clickButtonUpdateProfile();
    }

    @Step("Action verify update profile succcess")
    public void verifyUpdateProfileSuccess() {
        Assert.assertTrue(WebUI.checkElementExist(allertUpdateProfileSuccess), "The allert message is not display");
        WebUI.assertEquals(WebUI.getElementText(allertUpdateProfileSuccess), "Your Profile has been updated successfully!", "The message not match");
    }

    @Step("Action verify update profile")
    public void verifyUpdateProfile(){
        WebUI.assertEquals(WebUI.getElementAttribute(inputName, "value"), "Hieu tester", "Name not match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputPhone, "value"), "0906562794", "Phone not match");
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(checkFileUpload);
        WebUI.assertEquals(WebUI.getElementText(checkFileUpload), "1 File selected", "File has not been uploaded yet");
        LogUtils.info("verifyUpdateUserProfileSuccess: profile updated successfully!");
    }
    @Step("Action click edit by address")
    public void clickEditByAddress(String addressText) {

        WebUI.scrollToElementAtTop(infoAddressProfile);
        // In ra để debug nếu cần
        LogUtils.info("🔍 Đang tìm địa chỉ: " + addressText);

        // XPath block chứa địa chỉ cần tìm
        String baseXPath = "//div[contains(@class, 'border') and contains(., '" + addressText + "')]";

        // XPath cho dấu 3 chấm và nút Edit trong đúng block
        By ellipsisButton = By.xpath(baseXPath + "//button[contains(@class, 'bg-gray')]");
        By editButton = By.xpath(baseXPath + "//a[normalize-space()='Edit']");

        // Đợi nút dấu 3 chấm hiển thị và click
        WebUI.waitForElementVisible(ellipsisButton, 5);
        WebUI.clickElement(ellipsisButton);

        // Đợi menu xổ ra rồi click "Edit"
        WebUI.waitForElementVisible(editButton, 5);
        WebUI.clickElement(editButton);

        LogUtils.info("✅ Đã click vào nút Edit cho địa chỉ: " + addressText);
    }

    @Step("Action add new address")
    public void addNewAddress(Hashtable<String, String> data) {
        WebUI.scrollToElementAtTop(infoAddressProfile);
        WebUI.clickElement(addNewAddressProfile);
        WebUI.clickElement(textAreaAddAddress);
        WebUI.setText(textAreaAddAddress, data.get("Address"));
        WebUI.clickElement(selectCountry);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearchCountry);
        WebUI.setTextAndKey(inputSearchCountry, data.get("Country"), Keys.ENTER);
        WebUI.clickElement(selectState);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearchState);
        WebUI.setTextAndKey(inputSearchState, data.get("State"), Keys.ENTER);
        WebUI.clickElement(selectCity);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearchCity);
        WebUI.setTextAndKey(inputSearchCity, data.get("City"), Keys.ENTER);
        WebUI.clickElement(inputPostalCode);
        WebUI.setText(inputPostalCode, data.get("Postal Code"));
        WebUI.clickElement(inputPhoneAddress);
        WebUI.setText(inputPhoneAddress, data.get("Phone"));
        WebUI.clickElement(buttonSaveAddress);
    }

    public void verifyEditSuccess(){
        Assert.assertTrue(DriverManager.getDriver().findElement(allertEditSuccess).isDisplayed(), "Message is not displayed");
        //Assert.assertEquals(driver.findElement(alertSuccessMessage).getText(), "Product has been inserted successfully", "Alert message not match");
        WebUI.assertEquals(WebUI.getElementText(allertEditSuccess), "Address info updated successfully", "Alert message not match");
    }

    public void verifyInfoAddress(String addressText, int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_AddressProfile"), "Address");
        clickEditByAddress(addressText);
        WebUI.assertEquals(WebUI.getElementAttribute(textAreaEditAddress, "value"), excelHelper.getCellData("Address", row), "The address name not match");
        WebUI.assertEquals(WebUI.getElementAttribute(selectEditCountry, "title"), excelHelper.getCellData("Country", row), "The country name not match");
        WebUI.assertEquals(WebUI.getElementAttribute(selectEditState, "title"), excelHelper.getCellData("State", row), "The state name not match");
        WebUI.assertEquals(WebUI.getElementAttribute(selectEditCity, "title"), excelHelper.getCellData("City", row), "The product name not match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputEditPostalCode, "value"), excelHelper.getCellData("Postal Code", row), "The postal code not match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputEditPhoneAddress, "value"), excelHelper.getCellData("Phone", row), "The phone not match");
    }

    public void editAddress(String addressText, int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_AddressProfile"), "EditAddress");
        clickEditByAddress(addressText);
        WebUI.clearText(textAreaEditAddress);
        WebUI.setText(textAreaEditAddress, excelHelper.getCellData("Address", row));
        WebUI.clearText(inputEditPostalCode);
        WebUI.setText(inputEditPostalCode, excelHelper.getCellData("Postal Code", row));
        WebUI.clearText(inputEditPhoneAddress);
        WebUI.setText(inputEditPhoneAddress, excelHelper.getCellData("Phone", row));
        WebUI.clickElement(buttonSaveEditAddress);
    }

    public void verifyEditInfoAddress(String addressText, int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_AddressProfile"), "EditAddress");
        clickEditByAddress(addressText);
        WebUI.assertEquals(WebUI.getElementAttribute(textAreaEditAddress, "value"), excelHelper.getCellData("Address", row), "The address name not match");
//        WebUI.assertEquals(WebUI.getElementAttribute(selectEditCountry, "title"), excelHelper.getCellData("Country", row), "The country name not match");
//        WebUI.assertEquals(WebUI.getElementAttribute(selectEditState, "title"), excelHelper.getCellData("State", row), "The state name not match");
//        WebUI.assertEquals(WebUI.getElementAttribute(selectEditCity, "title"), excelHelper.getCellData("City", row), "The product name not match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputEditPostalCode, "value"), excelHelper.getCellData("Postal Code", row), "The postal code not match");
        WebUI.assertEquals(WebUI.getElementAttribute(inputEditPhoneAddress, "value"), excelHelper.getCellData("Phone", row), "The phone not match");
    }

    public void deleteInfoAddress(String addressText){
        WebUI.scrollToElementAtTop(infoAddressProfile);
        // In ra để debug nếu cần
        LogUtils.info("🔍 Đang tìm địa chỉ: " + addressText);

        // XPath block chứa địa chỉ cần tìm
        String baseXPath = "//div[contains(@class, 'border') and contains(., '" + addressText + "')]";

        // XPath cho dấu 3 chấm và nút Edit trong đúng block
        By ellipsisButton = By.xpath(baseXPath + "//button[contains(@class, 'bg-gray')]");
        By deleteButton = By.xpath(baseXPath + "//a[normalize-space()='Delete']");

        // Đợi nút dấu 3 chấm hiển thị và click
        WebUI.waitForElementVisible(ellipsisButton, 5);
        WebUI.clickElement(ellipsisButton);

        // Đợi menu xổ ra rồi click "Edit"
        WebUI.waitForElementVisible(deleteButton, 5);
        WebUI.clickElement(deleteButton);

        LogUtils.info("✅ Đã click vào nút Delete cho địa chỉ: " + addressText);
    }

    public void verifyDeleteAddress(String addressText){
        // XPath block chứa địa chỉ cần tìm
        String baseXPath = "//div[contains(@class, 'border') and contains(., '" + addressText + "')]";

        // XPath cho dấu 3 chấm và nút Edit trong đúng block
        By ellipsisButton = By.xpath(baseXPath + "//button[contains(@class, 'bg-gray')]");
        By deleteButton = By.xpath(baseXPath + "//a[normalize-space()='Delete']");

        int elementCount = DriverManager.getDriver().findElements(deleteButton).size();
        Assert.assertEquals(elementCount, 0, "Address has not been deleted yet: " + addressText);

    }


}
