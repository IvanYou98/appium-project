package steps;

import io.cucumber.java.en.And;
import page.ProductPage;

public class ProductSteps {
    private final ProductPage productPage;

    public ProductSteps() {
        this.productPage = new ProductPage();
    }

    @And("I add products to the shopping cart and click shopping cart icon")
    public void addProductToShoppingCardAndConfirm() {
        productPage.addProductToCart("Jordan 6 Rings");
        productPage.addProductToCart("PG 3");
        productPage.clickShoppingCartIcon();
    }
}
