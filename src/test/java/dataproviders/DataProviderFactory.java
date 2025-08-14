package dataproviders;

import com.hieutester.helpers.ExcelHelper;
import com.hieutester.helpers.PropertiesHelper;
import com.hieutester.helpers.SystemHelper;
import com.hieutester.utils.LogUtils;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class DataProviderFactory {

        @DataProvider(name = "data_login_success_from_excel")
        public Object[][] dataLoginSuccesFromExcel(){
            ExcelHelper excelHelper = new ExcelHelper();
            Object[][] data = excelHelper.getExcelDataProvider(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("EXCEL_UserLogin"), "Login");

            if (data.length > 1) {
                LogUtils.info("Login Data from Excel: "+ data);
                return new Object[][] { data[0] };
            } else {
                return new Object[][] {}; // Trả về mảng rỗng nếu dữ liệu không đủ
            }
        }

        @DataProvider(name = "data_login_fail_with_invalid_email_from_excel")
        public Object[][] dataLoginFailWithInvalidEmailFromExcel(){
            ExcelHelper excelHelper = new ExcelHelper();
            Object[][] data = excelHelper.getExcelDataProvider(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("EXCEL_UserLogin"), "Login");

            if (data.length > 1) {
                LogUtils.info("Login Data from Excel: "+ data);
                return new Object[][] { data[1] };
            } else {
                return new Object[][] {}; // Trả về mảng rỗng nếu dữ liệu không đủ
            }
        }

        @DataProvider(name = "data_login_fail_with_invalid_password_from_excel")
        public Object[][] dataLoginFailWithInvalidPasswordFromExcel(){
            ExcelHelper excelHelper = new ExcelHelper();
            Object[][] data = excelHelper.getExcelDataProvider(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("EXCEL_UserLogin"), "Login");

            if (data.length > 1) {
                LogUtils.info("Login Data from Excel: "+ data);
                return new Object[][] { data[2] };
            } else {
                return new Object[][] {}; // Trả về mảng rỗng nếu dữ liệu không đủ
            }
        }

        @DataProvider(name = "data_add_new_product_form_excel")
        public Object[][] dataAddNewProduct() {
            ExcelHelper excelHelper = new ExcelHelper();
            Object[][] data = excelHelper.getExcelDataProvider(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("EXCEL_SetProduct"), "ProductData");

            LogUtils.info("✅ Read data from Excel: " + Arrays.deepToString(data));
            return data;
        }

    @DataProvider(name = "data_provider_add_new_address_excel_hashtable")
    public Object[][] dataLoginHRMFromExcelHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelDataHashTable(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("EXCEL_AddressProfile"), "Address", 1, 1);
        LogUtils.info("Add new address with data: " + data);
        return data;
    }
}
