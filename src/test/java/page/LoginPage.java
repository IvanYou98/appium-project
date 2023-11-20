package page;

import drivers.DriverFactory;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "android:id/text1")
    @CacheLookup
    private WebElement countryDropdown;

    @FindBy(id = "com.androidsample.generalstore:id/nameField")
    @CacheLookup
    private WebElement nameField;

    @FindBy(id = "com.androidsample.generalstore:id/radioFemale")
    @CacheLookup
    private WebElement femaleRadioBtn;

    @FindBy(id = "com.androidsample.generalstore:id/radioMale")
    @CacheLookup
    private WebElement maleRadioBtn;



    @FindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    @CacheLookup
    private WebElement letsShopBtn;


    public void clickCountryDropdown() {
        countryDropdown.click();
    }

    public void enterNameField(String name) {
        nameField.sendKeys(name);
    }

    public void clickFemaleRadioBtn() {
        femaleRadioBtn.click();
    }

    public void clickMaleRadioBtn() {
        maleRadioBtn.click();
    }
    public void clickLetsShopBtn() {
        letsShopBtn.click();
    }

    public String getToastMsg() {
        return DriverFactory.getAndroidDriver().findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
    }

    public void chooseCountry(String country) {
        scrollUntilVisible(country);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text = '" + country + "']")).click();
    }

    public void waitPageLoading() {
        super.waitPageLoading("General Store");
    }
}
