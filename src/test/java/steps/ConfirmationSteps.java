package steps;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import page.ConfirmationPage;

import java.util.Set;

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

    @When("I click the visit website button")
    public void clickVisitWebsite() {
        confirmationPage.clickVisitWebsiteBtn();
    }

    @Then("I can search in the search bar")
    public void search() throws Exception{
        Thread.sleep(3000);
        AndroidDriver driver = DriverFactory.getAndroidDriver();
        Set<String> contextNames = driver.getContextHandles();

        for (String contextName : contextNames) {
            System.out.println(contextName); // Prints out something like NATIVE_APP \n WEBVIEW_1

            if (contextName.contains("WEBVIEW")) {
                driver.context(contextName); // Switch to Webview context
                driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("https://appium.io/docs/en/2.1/");
                new Actions(driver)
                        .sendKeys(Keys.ENTER)
                        .perform();
                break;
            }
        }


    }



}
