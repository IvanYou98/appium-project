package page;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ConfirmationPage extends BasePage {

    @FindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> prices;

    @FindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalPrice;

    @FindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsBtn;

    @FindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement visitWebsiteBtn;


    public List<Double> getProductPrices() {
        return prices
                .stream()
                .map(element -> element.getAttribute("text").substring(1))
                .mapToDouble(Double::parseDouble)
                .boxed()
                .collect(Collectors.toList());
    }

    public Double getTotalPrice() {
        return Double.parseDouble(totalPrice.getAttribute("text").substring(1));
    }

    public void waitPageLoading() {
        super.waitPageLoading("Cart");
    }

    public void longPressTermsBtn() {
        longPress(termsBtn.getLocation(), Duration.ofSeconds(1));
    }

    public void clickCloseTermsBtn() {
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }

    public void clickVisitWebsiteBtn() {
        visitWebsiteBtn.click();
    }
}
