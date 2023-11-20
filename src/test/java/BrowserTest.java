import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrowserTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.getWebDriver();
    }


    @Test
    public void sanityTest() throws InterruptedException{
        driver.navigate().to("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Hello World!");
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
