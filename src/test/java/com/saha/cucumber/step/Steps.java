package com.saha.cucumber.step;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Steps {

    public static final String hubURL = "http://host.docker.internal:4444/wd/hub";
    protected static AppiumDriver driver; // <MobileElement> KALDIRILDI

    @Before
    public static void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (!StringUtils.isEmpty(System.getProperty("key"))) {
            capabilities.setCapability("key", System.getProperty("key"));

            if ("ANDROID".equalsIgnoreCase(System.getProperty("platform"))) {
                capabilities.setCapability("appium:appPackage", "com.gratis.android");
                capabilities.setCapability("appium:appActivity", "com.app.gratis.ui.splash.SplashActivity");
                capabilities.setCapability("platformName", "Android");

                // <MobileElement> JENERİĞİ YOK
                driver = new AndroidDriver(new URL(hubURL), capabilities);
            } else {
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("appium:bundleId", "com.pharos.Gratis");

                // <MobileElement> JENERİĞİ YOK
                driver = new IOSDriver(new URL(hubURL), capabilities);
            }
        }
    }

    @After
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Given("^Wait (\\d+) seconds$")
    public void waitSeconds(int seconds) { wait(seconds); }

    @Given("^Element with id \"([^\"]*)\" is clicked$")
    public void clickElementById(String elementId) {
        WebElement element = driver.findElement(By.id(elementId));
        element.click();
    }

    @Given("^Element with xpath \"([^\"]*)\" is clicked$")
    public void clickElementByXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }

    public void wait(int seconds) {
        try { TimeUnit.SECONDS.sleep(seconds); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
