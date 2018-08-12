package com.pokotylov.testtask.site.page.impl;

import com.pokotylov.testtask.framework.aspect.Loggable;
import com.pokotylov.testtask.site.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyWalletPage extends AbstractPage {
    @FindBy(xpath = "//div[@id='amountval']//div[@role='slider']")
    private WebElement loanValueSlider;
    @FindBy(how = How.XPATH, xpath = "//span[@id='amountval-val']")
    private WebElement currentLoanValue;

    MyWalletPage(WebDriver driver) {
        super(driver);
        switchToTab(1);
    }

    @Loggable
    public boolean isLoanEnable(int value) {
        return value <= getSum(LoanSumType.MAX) && value >= getSum(LoanSumType.MIN);
    }

    private int getSum(LoanSumType type) {
        int currentSliderSum;
        while (true) {
            currentSliderSum = getCurrentSum();
            dragAndDropSlider(type.step, loanValueSlider);
            if (currentSliderSum == getCurrentSum()) {
                break;
            }
        }
        return currentSliderSum;
    }

    private int getCurrentSum() {
        waiter.waitForElement(currentLoanValue);
        return Integer.valueOf(currentLoanValue.getText().replaceAll("[^0-9]+", ""));
    }

    private enum LoanSumType {
        MIN(-25),
        MAX(25);

        private int step;

        LoanSumType(int step) {
            this.step = step;
        }
    }
}
