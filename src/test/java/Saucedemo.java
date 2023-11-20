import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.TimeoutException;

import java.net.MalformedURLException;
import java.net.URL;

public class Saucedemo {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "storage:filename=General-Store.apk");  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "oauth-ivany-ac0f0");
        sauceOptions.setCapability("accessKey", "e4a7f9b8-915c-47e3-94d7-32dec4145994");
        sauceOptions.setCapability("build", "appium-build-4R6TA");
        sauceOptions.setCapability("name", "sauce demo");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
        caps.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, caps);

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField"))
                .sendKeys("Ivan You");
        Thread.sleep(3000);
        driver.quit();
    }


}
