package com.saha.cucumber.step;

import com.testinium.driver.TestiniumAndroidDriver;
import com.testinium.driver.TestiniumIOSDriver;
import com.testinium.util.Constants;
import com.testinium.util.TestiniumEnvironment;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.testinium.util.Constants.CapabilityConstants.*;
import static com.testinium.util.Constants.PLATFORM_NAME;


public class Steps {

    private Logger logger = LoggerFactory.getLogger(getClass());
    protected static AppiumDriver driver;
    protected URL hubUrl;

    protected static FluentWait<AppiumDriver> appiumFluentWait;

    Boolean DeviceAndroid =false;

    @Before
    public void setUp() {
        try {
            System.out.println("isAndroid:" + TestiniumEnvironment.isPlatformAndroid());
            if(DeviceAndroid || TestiniumEnvironment.isPlatformAndroid()){
                DesiredCapabilities overridden = new DesiredCapabilities();
                overridden.setCapability(PLATFORM_NAME, Platform.ANDROID);
                overridden.setCapability(UDID, "YOUR_UDID");
                overridden.setCapability(APPIUM_AUTOMATION_NAME, "YOUR_AUTOMATION_NAME");
                overridden.setCapability(APPIUM_APP_PACKAGE, "YOUR_APP_PACKAGE");
                overridden.setCapability(APPIUM_APP_ACTIVITY, "YOUR_APP_ACTIVITY");
                overridden.setCapability(APPIUM_AUTO_GRANT_PERMISSIONS, true);
                overridden.setCapability(APPIUM_NEW_COMMAND_TIMEOUT, 60000);
                hubUrl = new URL("http://192.168.1.89:4723/");
                driver = new TestiniumAndroidDriver(hubUrl,overridden);

                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                appiumFluentWait = new FluentWait<AppiumDriver>(driver);


                appiumFluentWait.withTimeout(Duration.ofSeconds(8))
                        .pollingEvery(Duration.ofMillis(350))
                        .ignoring(NoSuchElementException.class);
            }
            else {
                System.out.println("IOS");
                hubUrl = new URL("http://192.168.1.89:4723/");
                DesiredCapabilities overridden = new DesiredCapabilities();
                overridden.setCapability(Constants.PLATFORM_NAME, Platform.IOS);
                overridden.setCapability(UDID, "YOUR_UDID");
                overridden.setCapability(APPIUM_AUTOMATION_NAME, "YOUR_AUTOMATION_NAME");
                overridden.setCapability(APPIUM_BUNDLE_ID, "YOUR_BUNDLE_ID");
                overridden.setCapability(APPIUM_AUTO_ACCEPT_ALERTS, true);
                driver = new TestiniumIOSDriver(hubUrl, overridden);


                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                appiumFluentWait = new FluentWait<AppiumDriver>(driver);
                appiumFluentWait.withTimeout(Duration.ofSeconds(8))
                        .pollingEvery(Duration.ofMillis(350))
                        .ignoring(NoSuchElementException.class);
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
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
