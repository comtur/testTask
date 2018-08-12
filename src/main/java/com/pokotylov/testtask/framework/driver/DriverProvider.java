package com.pokotylov.testtask.framework.driver;

import org.openqa.selenium.WebDriver;

public interface DriverProvider {
    WebDriver getInstance();
    void destroy();
}
