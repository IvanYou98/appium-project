import page.ConfirmationPage;
import page.GoogleHomePage;
import page.LoginPage;
import page.ProductPage;
import drivers.DriverFactory;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ECommerceTest {

    @Test
    public void fillForm() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickCountryDropdown();
        loginPage.chooseCountry("Argentina");
        loginPage.enterNameField("Lucy");
        loginPage.clickFemaleRadioBtn();
        loginPage.clickLetsShopBtn();
    }

    @Test(enabled = false)
    public void testToastMsg() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickLetsShopBtn();
        assertEquals(loginPage.getToastMsg(), "Please enter your name");
    }

    @Test(dependsOnMethods = "fillForm")
    public void testFindJordan6Rings() {
        ProductPage productPage = new ProductPage();
        productPage.waitPageLoading();
        productPage.addProductToCart("Jordan 6 Rings");
    }

    @Test(dependsOnMethods = "testFindJordan6Rings")
    public void testTotalPrice() {
        ProductPage productPage = new ProductPage();
        productPage.addProductToCart("PG 3");
        productPage.clickShoppingCartIcon();
        ConfirmationPage confirmationPage = new ConfirmationPage();
        confirmationPage.waitPageLoading();
        // calculate total price
        Double actualTotalPrice = 0.0;
        for (Double productPrice : confirmationPage.getProductPrices()) {
            actualTotalPrice += productPrice;
        }
        assertEquals(actualTotalPrice, confirmationPage.getTotalPrice());
    }

    @Test (dependsOnMethods = "testTotalPrice")
    public void testTerms() {
        ConfirmationPage confirmationPage = new ConfirmationPage();
        confirmationPage.longPressTermsBtn();
        confirmationPage.clickCloseTermsBtn();
    }

    @Test(dependsOnMethods = "testTerms")
    public void testWebview() throws InterruptedException{
        ConfirmationPage confirmationPage = new ConfirmationPage();
        confirmationPage.clickVisitWebsiteBtn();
        confirmationPage.switchToWebview();

        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.typeInSearchBar("youtube");
        googleHomePage.switchToNativeView();

        DriverFactory.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.BACK));
        LoginPage loginPage = new LoginPage();
        loginPage.waitPageLoading();
        loginPage.enterNameField("Jack");
    }


    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
        DriverFactory.closeService();
    }
}
