package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import utils.ScreenShotUtils;
import utils.WebdriverUtils;

import static utils.LoggerUtils.log;

public abstract class BaseTestClass {

    protected WebDriver setUp(ITestContext context) {
        return WebdriverUtils.setUpDriver();
    }

    protected void tearDown(WebDriver driver, ITestResult testResult) {
        final String testName = testResult.getClass().getName();
        try {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                ScreenShotUtils.takeScreenShot(driver, testName);
            }
        } catch (Exception e) {
            log.error("Exception occurred in tearDown() of test: {}. Message: {}", testName, e.getMessage());
        } finally {
            WebdriverUtils.quitDriver(driver);
        }
    }
}
