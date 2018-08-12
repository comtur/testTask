package com.pokotylov.testtask.site.page.impl;

import com.pokotylov.testtask.framework.aspect.Loggable;
import com.pokotylov.testtask.framework.property.PropertyReader;
import com.pokotylov.testtask.site.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class StartPage extends AbstractPage {
    private static final String PAGE_URL = "page.start";
    private static final String OFFER_COMMISSION_EXT = ".//div[@class='full_commission']";
    private static final String OFFER_SUBMIT_EXT = ".//div[@class='apply-proposition']//a[@class='submit']";

    @FindBy(xpath = "//div[@id='money']//input[@class='val_text']")
    private WebElement currentValue;
    @FindBy(xpath = "//div[@id='money']//div[@class='noUi-handle']")
    private WebElement sumSlider;
    @FindBy(xpath = "//div[@class='bank-position']")
    private List<WebElement> offers;

    public StartPage(WebDriver driver) {
        super(driver);
    }

    @Loggable
    public MyWalletPage selectOfferWithMinCommission() {
        waiter.waitForElement(offers);
        WebElement offerWithMinCommission = offers
                .stream()
                .min(Comparator.comparing(this::returnCommission))
                .orElseThrow(NoSuchElementException::new);
        offerWithMinCommission.findElement(By.xpath(OFFER_SUBMIT_EXT)).click();
        return new MyWalletPage(driver);
    }

    @Loggable
    public StartPage setSumBySlider(int value) {
        waiter.waitForElement(sumSlider);
        int sliderStepValue = 50;
        if (value % sliderStepValue != 0)
            throw new IllegalArgumentException(String.format("Can't set value %s, slider step %s", value, sliderStepValue));
        int step = 200;
        boolean flag = true;
        while (getCurrentValue() != value) {
            if (getCurrentValue() > value) {
                if (!flag) {
                    step /= 2;
                }
                this.dragAndDropSlider(-step, sumSlider);
                flag = true;
            } else {
                if (flag) {
                    step /= 2;
                }
                this.dragAndDropSlider(step, sumSlider);
                flag = false;
            }
        }
        return this;
    }

    @Loggable
    public StartPage openPage() {
        driver.get(PropertyReader.getProperty(PAGE_URL));
        return this;
    }

    private int returnCommission(WebElement offer) {
        return Integer.valueOf(
                offer.findElement(By.xpath(OFFER_COMMISSION_EXT))
                        .getAttribute("innerText")
                        .replaceAll("[^0-9]+", "")
        );
    }

    private int getCurrentValue() {
        return Integer.valueOf(currentValue.getAttribute("value"));
    }
}
