package tests.login;

import data.TestGroups;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.BaseTestClass;
import utils.PropertiesUtils;
import utils.ScreenShotUtils;
import utils.WebdriverUtils;

import static utils.LoggerUtils.log;

@Test(groups = {TestGroups.REGRESSION, TestGroups.LOGIN})
public class TestUnsuccessfulLogin extends BaseTestClass {
    private final String testName = this.getClass().getName();
    private WebDriver driver;
    private String username;
    private String password;

    @BeforeMethod
    public void setUpTest(ITestContext testContext) {
        log.info("[SETUP TEST] {}", testName);
        driver = setUp(testContext);
        username = PropertiesUtils.getUsername();
        password = "wrong_password";
    }

    @Test
    public void testSuccessfulLogin() {
        log.info("[TEST] Starting test: {}", testName);
        LoginPage loginPage = new LoginPage(driver)
                .open()
                .typeUsername(username)
                .typePassword(password)
                .clickLoginButtonNoProgress();

        Assert.assertEquals(driver.getCurrentUrl(), loginPage.getLoginPageUrl(), "Wrong URL");
    }

    @AfterMethod
    public void tearDownTest(ITestResult testResult) {
        log.info("[END TEST] {}", testName);
        if (testResult.getStatus() == ITestResult.FAILURE) {
            ScreenShotUtils.takeScreenShot(driver, testName);
        }
        WebdriverUtils.quitDriver(driver);
    }
}
