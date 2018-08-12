package com.pokotylov.testtask.test.ui;

import com.pokotylov.testtask.framework.driver.DriverProvider;
import com.pokotylov.testtask.framework.driver.impl.DefaultDriverProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected DriverProvider driverProvider;

    @BeforeMethod
    public void init() {
        driverProvider = new DefaultDriverProvider();
    }

    @AfterMethod
    public void tearDown() {
        driverProvider.destroy();
    }
}
