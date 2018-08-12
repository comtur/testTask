package com.pokotylov.testtask.framework.driver.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Waiter {
    private static final long DEFAULT_TIMEOUT = 10;
    private WebDriver driver;

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElement(WebElement element) {
        getDriverWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElement(List<WebElement> elements) {
        getDriverWait(DEFAULT_TIMEOUT).until(webDriver -> !elements.isEmpty());
        getDriverWait(DEFAULT_TIMEOUT).until(webDriver -> ExpectedConditions.visibilityOf(elements.get(0)));
    }

    private FluentWait<WebDriver> getDriverWait(long timeout) {
        return new WebDriverWait(driver, timeout).pollingEvery(500, TimeUnit.MILLISECONDS);
    }
}

