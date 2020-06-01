package test.java.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import test.java.po.HomePage;


public class Listener implements
        ISuiteListener,
        ITestListener,
        IInvokedMethodListener {
    public Logger logger = LogManager.getLogger(HomePage.class);
    Screenshot screenshot;


    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
       logger.trace("Before invocation");
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        logger.trace("After invocation");
    }

    @Override
    public void onStart(ISuite iSuite) {

    }

    @Override
    public void onFinish(ISuite iSuite) {

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.trace("Test successed");
    }

    //снятие скриншотов только для упавших тестов
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.trace("Test failed");
        screenshot = new Screenshot((WebDriver) iTestResult.getTestContext().getAttribute("driver"));
        screenshot.getScreenshot(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
