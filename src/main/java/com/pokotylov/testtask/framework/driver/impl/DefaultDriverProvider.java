package com.pokotylov.testtask.framework.driver.impl;

import com.pokotylov.testtask.framework.driver.DriverProvider;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.Optional;

public class DefaultDriverProvider implements DriverProvider {

    private ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    @Override
    public WebDriver getInstance() {
        return Optional.ofNullable(webDriverThreadLocal.get())
                .orElseGet(this::createDriverInstance);
    }

    private WebDriver createDriverInstance() {
        final WebDriver driverInstance = DriverFactory.getDriver();
        webDriverThreadLocal.set(driverInstance);
        return driverInstance;
    }

    @Override
    public void destroy() {
        if (Objects.nonNull(webDriverThreadLocal)) {
            webDriverThreadLocal.get().quit();
            webDriverThreadLocal = null;
        }
    }
}

