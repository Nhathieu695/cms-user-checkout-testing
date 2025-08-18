package EcommerceCMS.pages.user;

import com.hieutester.keywords.WebUI;
import com.hieutester.utils.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserListProductPage {

    private By linkProductFirst = By.xpath("(//div[@class='p-md-3 p-2 text-left']/child::h3/a)[1]");
    private By resultProduct = By.xpath("//div[@class='text-left']//h1");
    private By listNameProduct = By.xpath("//div[contains(@class,'row gutters-5')]//div[contains(@class,'col')]//h3/a");

    @Step("Action get href from product first")
    public String getHrefProductFirst() {
        return WebUI.getElementAttribute(linkProductFirst, "href");
    }

    @Step("Action click product detail page")
    public UserProductDetailPage clickLinkProductFirst(){
        WebUI.clickElement(linkProductFirst);
        return new UserProductDetailPage();
    }

    @Step("Verify search product from URL and result product")
    public void verifyProductInfoFromUrl(int minMatchWords) {
        String currentUrl = WebUI.getCurrentUrl();

        // Nếu URL có ?keyword= thì tách ra
        String keyword = "";
        if (currentUrl.contains("keyword=")) {
            keyword = currentUrl.substring(currentUrl.indexOf("keyword=") + 8) // lấy sau "keyword="
                    .replace("+", " ") // thay + thành space
                    .toLowerCase()
                    .trim();
        }

        // Chuẩn hóa
        String[] slugWords = keyword.split("\\s+");

        // Lấy tiêu đề "Search result for ..."
        String resultTitle = WebUI.getElementText(resultProduct)
                .replaceAll("[^a-zA-Z0-9 ]", "")
                .toLowerCase()
                .trim();

        // Lấy danh sách tên sản phẩm
        List<WebElement> productElements = WebUI.getWebElements(listNameProduct);
        List<String> productNames = productElements.stream()
                .map(e -> e.getText().replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().trim())
                .collect(Collectors.toList());

        boolean hasMatch = false;

        // 1. So sánh với tiêu đề
        int matchTitle = 0;
        for (String word : slugWords) {
            if (resultTitle.contains(word)) {
                matchTitle++;
            }
        }
        if (matchTitle >= minMatchWords) {
            hasMatch = true;
            LogUtils.info("Matched with search title: " + resultTitle);
        }

        // 2. Nếu chưa match → check danh sách sản phẩm
        if (!hasMatch) {
            for (String productName : productNames) {
                int matchCount = 0;
                String[] nameWords = productName.split("\\s+");
                for (String word : slugWords) {
                    if (Arrays.asList(nameWords).contains(word)) {
                        matchCount++;
                    }
                }
                if (matchCount >= minMatchWords) {
                    hasMatch = true;
                    LogUtils.info("Matched product: " + productName);
                    break;
                }
            }
        }

        LogUtils.info("URL: " + currentUrl);
        LogUtils.info("Slug words: " + Arrays.toString(slugWords));
        LogUtils.info("Result title: " + resultTitle);
        LogUtils.info("Product list: " + productNames);

        Assert.assertTrue(hasMatch,
                String.format("verifyProductInfoFromUrl: Failed - No match found with at least %d words", minMatchWords));
    }



}
