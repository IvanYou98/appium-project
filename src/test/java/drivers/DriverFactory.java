package drivers;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class DriverFactory {
//    private static final AppiumDriverLocalService service;
    private static final ThreadLocal<AndroidDriver> androidDriversThreadLocal = new ThreadLocal<>();

    static {

//        service = new AppiumServiceBuilder()
//                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//                .withIPAddress("127.0.0.1")
//                .usingPort(4723)
//                .build();
//        service.start();
//        System.out.println("Service started...");
    }


    public static AndroidDriver getAndroidDriver() {
        if (androidDriversThreadLocal.get() == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("Pixel 6 Pro");
            options.setChromedriverExecutable("/Users/yuanfanyou/Desktop/general-store/src/test/resources/chromedriver");
            options.setApp("/Users/yuanfanyou/Desktop/general-store/src/test/resources/General-Store.apk");
            try {
                AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
                androidDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                androidDriversThreadLocal.set(androidDriver);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return androidDriversThreadLocal.get();
    }

    public static WebDriver getWebDriver() {
        if (androidDriversThreadLocal.get() == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("IvanPhone");
            options.setChromedriverExecutable("/Users/yuanfanyou/Desktop/general-store/src/test/resources/chromedriver");
            options.setCapability("browserName", "Chrome");
            try {
                AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
                androidDriver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
                androidDriversThreadLocal.set(androidDriver);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return androidDriversThreadLocal.get();
    }

    public static void quitDriver() {
        if (androidDriversThreadLocal.get() != null) {
            androidDriversThreadLocal.get().quit();
            androidDriversThreadLocal.remove();
        }
    }




    public static void closeService() {
//        service.close();
    }
}
