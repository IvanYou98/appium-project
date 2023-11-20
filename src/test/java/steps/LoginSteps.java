package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.ConfirmationPage;
import page.LoginPage;
import page.ProductPage;

import static org.testng.Assert.assertEquals;

public class LoginSteps {
    private final LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    @Given("I have launched the mobile application for general store")
    public void launchMobileApp() {

    }

    @When("I fill out the info form")
    public void fillOutLoginForm() {
        loginPage.clickCountryDropdown();
        loginPage.chooseCountry("China");
        loginPage.enterNameField("Ivan");
        loginPage.clickMaleRadioBtn();
        loginPage.clickLetsShopBtn();
    }

}
