package page;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage{
    @FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    WebElement shoppingCartIcon;

    public void clickShoppingCartIcon() {
        shoppingCartIcon.click();
    }

    public void addProductToCart(String productName) {
        scrollUntilVisible(productName);
        AndroidDriver driver = DriverFactory.getAndroidDriver();
        List<WebElement> productNames = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getAttribute("text").equals(productName)) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }
    }

    public void waitPageLoading() {
        waitPageLoading("Products");
    }
}
