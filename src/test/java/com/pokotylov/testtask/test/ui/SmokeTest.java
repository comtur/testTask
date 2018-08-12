package com.pokotylov.testtask.test.ui;

import com.pokotylov.testtask.site.page.impl.MyWalletPage;
import com.pokotylov.testtask.site.page.impl.StartPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SmokeTest extends BaseTest {

    @Test
    public void takeLoanWithMinCommissionTest() {
        int loanSum = 6000;
        MyWalletPage page = new StartPage(driverProvider.getInstance())
                .openPage()
                .setSumBySlider(loanSum)
                .selectOfferWithMinCommission();
        Assert.assertTrue(page.isLoanEnable(loanSum));
    }
}
