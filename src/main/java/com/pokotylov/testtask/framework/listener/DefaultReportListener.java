package com.pokotylov.testtask.framework.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class DefaultReportListener implements ITestListener, ISuiteListener {

    @Override
    public void onTestStart(ITestResult result) { Reporter.log("<br>START test</br>"); }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("<br>Loan is available </br> <br>END test </br>");
    }

    @Override
    public void onTestFailure(ITestResult result) { Reporter.log("<br>Loan is not available </br> <br>END test </br>");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onStart(ISuite suite) {
    }

    @Override
    public void onFinish(ISuite suite) {
    }
}
