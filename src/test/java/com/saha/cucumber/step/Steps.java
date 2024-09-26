package com.saha.cucumber.step;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Steps {

    public static final String hubURL = "http://172.25.1.25:4444/wd/hub";
    protected static AppiumDriver<MobileElement> driver;


    @Before
    public static void setUp() throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();

        if (!StringUtils.isEmpty(System.getProperty("key"))) {
            capabilities.setCapability("key", System.getProperty("key"));
            if (System.getProperty("platform").equals("ANDROID")) {
                capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
                driver = new AndroidDriver<MobileElement>(new URL(hubURL), capabilities);
            } else {
                capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.MAC);
                driver = new IOSDriver<MobileElement>(new URL(hubURL), capabilities);
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

    /**
    * @param seconds
    */
    @Given("^Wait (\\d+) seconds$")
    public void waitSeconds(int seconds) {
        wait(seconds);
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
            driver.getPageSource();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
