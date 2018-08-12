package com.pokotylov.testtask.site.page;

import com.pokotylov.testtask.framework.driver.impl.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public abstract class AbstractPage{
    protected WebDriver driver;
    protected Waiter waiter;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        waiter = new Waiter(driver);
        PageFactory.initElements(driver, this);
    }

    protected void dragAndDropSlider(int step, WebElement slider) {
        Actions move = new Actions(driver);
        move.dragAndDropBy(slider, step, 0).perform();
    }

    protected void switchToTab(int tabIndex) {
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(tabIndex));
    }
}
