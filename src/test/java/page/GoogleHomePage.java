package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage extends BasePage {
    @FindBy(name = "q")
    WebElement searchBar;

    public GoogleHomePage() {
        PageFactory.initElements(driver, this);
    }

    public void typeInSearchBar(String text) {
        searchBar.sendKeys(text);
    }
}
