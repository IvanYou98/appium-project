package page;

import com.google.common.collect.ImmutableList;
import drivers.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected AndroidDriver driver;

    @FindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    @CacheLookup
    WebElement toolBarTitle;

    public BasePage() {
        this.driver = DriverFactory.getAndroidDriver();
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return toolBarTitle.getAttribute("text");
    }

    protected void waitPageLoading(String title) {
        new WebDriverWait(DriverFactory.getAndroidDriver(), Duration.ofSeconds(3))
                .until((driver) -> title.equals(toolBarTitle.getAttribute("text")));
    }


    protected void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }

    protected void longPress(Point point, Duration duration) {
        swipe(point, point, duration);
    }

    public void scrollDown() {
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();
        int startX = width / 2;
        int startY = (int) (0.2 * height);
        int endY = (int) (0.8 * height);
        swipe(new Point(startX, startY), new Point(startX, endY), Duration.ofMillis(300));
    }

    public void scrollUntilVisible(String text) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }

    public void switchToWebview() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> driver.getContextHandles().size() == 2);
        for (String contextHandle : driver.getContextHandles()) {
            if (!contextHandle.equals("NATIVE_APP")) {
                driver.context(contextHandle);
                break;
            }
        }
    }


    public void switchToNativeView() {
        driver.context("NATIVE_APP");
    }
}
