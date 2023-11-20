package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import page.ConfirmationPage;

import static org.testng.Assert.assertEquals;

public class ConfirmationSteps {
    private final ConfirmationPage confirmationPage;


    public ConfirmationSteps() {
        this.confirmationPage = new ConfirmationPage();
    }

    @Then("I should see the correct total price")
    public void iShouldSeeTheCorrectTotalPrice() {
        Double expectedTotalPrice = confirmationPage
                .getProductPrices()
                .stream()
                .mapToDouble(a -> a)
                .sum();
        Double actualTotalPrice = confirmationPage.getTotalPrice();
        assertEquals(actualTotalPrice, expectedTotalPrice);
    }

    @And("I should be able to view the terms")
    public void iShouldBeAbleToViewTerms() {
        confirmationPage.longPressTermsBtn();
        confirmationPage.clickCloseTermsBtn();
    }

}
