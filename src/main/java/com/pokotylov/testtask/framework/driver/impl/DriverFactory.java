package com.pokotylov.testtask.framework.driver.impl;

import com.pokotylov.testtask.framework.property.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.pokotylov.testtask.framework.property.PropertyNames.BROWSER;
import static com.pokotylov.testtask.framework.property.PropertyNames.CHROME_DRIVER_PATH;

public class DriverFactory {
    private static final String CHROME_BROWSER = "chrome";
    private static final String CHROME_PROPERTY = "webdriver.chrome.driver";

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (CHROME_BROWSER.equalsIgnoreCase(PropertyReader.getProperty(BROWSER))) {
            return getDefaultChromeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name: " + PropertyReader.getProperty(BROWSER));
        }
    }

    private static WebDriver getDefaultChromeDriver() {
        System.setProperty(CHROME_PROPERTY , PropertyReader.getProperty(CHROME_DRIVER_PATH));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
