package EcommerceCMS.pages.user;

import com.hieutester.helpers.ExcelHelper;
import com.hieutester.helpers.PropertiesHelper;
import com.hieutester.helpers.SystemHelper;
import com.hieutester.keywords.WebUI;
import com.hieutester.utils.LogUtils;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserProductDetailPage {

    private static final By nameProduct = By.xpath("//div[@class='text-left']/h1");
    private static final By priceProduct = By.xpath("//div[@class='col-sm-10']//del");
    private static final By unitOriginal = By.xpath("//div[@class='col-sm-10']//del/span");
    private static final By discountPriceProduct = By.xpath("//div[@class='col-sm-10']//strong");
    private static final By unitDiscountOrNormal = By.xpath("//div[@class='col-sm-10']//strong/following-sibling::span");
    private static final By colorsProduct = By.xpath("//div[@class='aiz-radio-inline']/child::label");
    private static final By availableQuantityProduct = By.xpath("//span[@id='available-quantity']");
    private static final By descriptionProduct = By.xpath("//div[@class='mw-100 overflow-auto text-left aiz-editor-data']");
    private static final By imageProduct = By.xpath("(//div[@data-fade='true']/descendant::img)[1]");


    @Step("Get product info, verify and write to Excel")
    public void getProductInfoToExcel(String filePath) {
        try {
            // Lấy danh sách màu
            List<WebElement> getColorProduct = WebUI.getWebElements(colorsProduct);
            List<String> colors = getColorProduct.stream()
                    .map(e -> e.getAttribute("data-title"))
                    .collect(Collectors.toList());

            WebUI.scrollToElement(imageProduct);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String currentTime = LocalDateTime.now().format(formatter);

            String originalPrice = "N/A";
            String unit = "";
            String discountPrice = "N/A";

            // Trường hợp có giảm giá
            if (WebUI.checkElementExist(priceProduct)) {
                originalPrice = WebUI.getElementText(priceProduct).replace(WebUI.getElementText(unitOriginal), "").trim();;

                if (WebUI.checkElementExist(unitOriginal)) {
                    unit = WebUI.getElementText(unitOriginal).trim();
                }

                if (WebUI.checkElementExist(discountPriceProduct)) {
                    discountPrice = WebUI.getElementText(discountPriceProduct).trim();
                }

            } else { // Không có giảm giá
                if (WebUI.checkElementExist(discountPriceProduct)) {
                    originalPrice = WebUI.getElementText(discountPriceProduct).trim();
                }

                if (WebUI.checkElementExist(unitDiscountOrNormal)) {
                    unit = WebUI.getElementText(unitDiscountOrNormal).trim();
                }

                discountPrice = ""; // Không có giảm giá
            }

            // ========================
            // VERIFY DỮ LIỆU
            // ========================

            // 1. Verify giá
            if (originalPrice.equals("N/A") || originalPrice.isEmpty()) {
                throw new AssertionError("❌ Original price is missing!");
            }

            // Nếu có giảm giá → Discount Price phải nhỏ hơn Original Price
            if (!discountPrice.equals("N/A") && !discountPrice.isEmpty()) {
                double ori = SystemHelper.parsePrice(originalPrice);
                double dis = SystemHelper.parsePrice(discountPrice);
                if (dis >= ori) {
                    throw new AssertionError("❌ Discount price (" + dis + ") must be less than original price (" + ori + ")");
                }
            }

            // 2. Verify unit
            if (unit.isEmpty()) {
                throw new AssertionError("❌ Unit is missing!");
            }

            // 3. Verify quantity
            String quantity = WebUI.getElementText(availableQuantityProduct);
            if (quantity.isEmpty() || quantity.equals("0")) {
                throw new AssertionError("❌ Product quantity is invalid: " + quantity);
            }

            // 4. Verify image
            String imageUrl = WebUI.getElementAttribute(imageProduct, "src");
            if (!imageUrl.startsWith("http")) {
                throw new AssertionError("❌ Invalid product image URL: " + imageUrl);
            }

            // 5. Verify product URL
            String productUrl = WebUI.getCurrentUrl();
            if (!productUrl.contains("product")) {
                throw new AssertionError("❌ Invalid product URL: " + productUrl);
            }

            // ========================
            // Ghi dữ liệu ra Excel
            // ========================

            List<String> productData = Arrays.asList(
                    WebUI.getElementText(nameProduct),
                    originalPrice,
                    unit,
                    discountPrice,
                    String.join(", ", colors),
                    quantity,
                    WebUI.getElementText(descriptionProduct),
                    imageUrl,
                    productUrl,
                    currentTime
            );

            ExcelHelper.appendDataToExcel(
                    productData,
                    filePath,
                    Arrays.asList(
                            "Product Name", "Original Price", "Unit", "Discount Price",
                            "Colors", "Available Quantity", "Description",
                            "Image URL", "Product URL", "Captured Time"
                    )
            );

        } catch (Exception e) {
            throw new RuntimeException("Error while getting product info", e);
        }
    }

    public void verifyInfoProductDetail(String hrefExpected) {
        LogUtils.info("🚀 Start verifyProductDetailPage");


        String currentURL = WebUI.getCurrentUrl().trim();
        String title = WebUI.getWebsiteTitle().trim();
        String nameProduct1 = WebUI.getElementText(nameProduct).trim();

        LogUtils.info("🔍 Current URL   : " + currentURL);
        LogUtils.info("🔍 Expected URL  : " + hrefExpected);
        LogUtils.info("🔍 Page title    : " + title);
        LogUtils.info("🔍 Product name  : " + nameProduct1);

        // Verify URL match exactly
        Assert.assertEquals(currentURL, hrefExpected,
                String.format("❌ URL mismatch. Expected: %s | Actual: %s", hrefExpected, currentURL));

        // Verify title contains product name (linh hoạt hơn so với equals tuyệt đối)
        Assert.assertTrue(title.contains(nameProduct1),
                String.format("❌ Title mismatch. Title: %s | Expected to contain: %s", title, nameProduct1));

        LogUtils.info("✅ verifyProductDetailPage PASSED!");

    }

    public void verifyDataDetailPageWithExcel(int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_GetProducts"), "InfoProduct");
        WebUI.assertEquals(WebUI.getElementText(nameProduct), excelHelper.getCellData("Product_Name", row), "The product name not match");
        WebUI.assertEquals(WebUI.getElementText(priceProduct).replace(WebUI.getElementText(unitOriginal), "").trim(), excelHelper.getCellData("Price", row), "Price original not match");
        WebUI.assertEquals(WebUI.getElementText(unitOriginal), excelHelper.getCellData("Unit", row), "Unit not match");
        WebUI.assertEquals(WebUI.getElementText(discountPriceProduct), excelHelper.getCellData("DiscountPrice", row), "DiscountPrice not match");
        // Verify Colors
        List<WebElement> getColorProduct = WebUI.getWebElements(colorsProduct);
        List<String> colorsOnPage = getColorProduct.stream()
                .map(e -> e.getAttribute("data-title").trim().toLowerCase())
                .collect(Collectors.toList());

        String excelColors = excelHelper.getCellData("Colors", row);

        // Tách list, loại bỏ "null"
        List<String> colorsFromExcel = Arrays.stream(excelColors.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(c -> !c.equals("null") && !c.isEmpty())
                .collect(Collectors.toList());

        // So sánh: size + từng màu
        WebUI.assertEquals(colorsOnPage.size(), colorsFromExcel.size(),
                "Number of colors not match. Expect: " + colorsFromExcel + " but found: " + colorsOnPage);

        for (String color : colorsFromExcel) {
            Assert.assertTrue(colorsOnPage.contains(color),
                    "Color not found on page: " + color + ". Actual colors: " + colorsOnPage);
        }
        LogUtils.info("✅ Colors verified. Expect: " + colorsFromExcel + " | Actual: " + colorsOnPage);

        WebUI.assertEquals(WebUI.getElementText(availableQuantityProduct), excelHelper.getCellData("Available_Quantity", row), "Available Quantity not match");
        WebUI.assertEquals(WebUI.getElementText(descriptionProduct), excelHelper.getCellData("Description", row), "Description not match");
        WebUI.assertEquals(WebUI.getElementAttribute(imageProduct, "src"), excelHelper.getCellData("Image_URL", row), "Image url not match");
    }

}
