package tests.login;

import data.CommonStrings;
import data.TestGroups;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import tests.BaseTestClass;
import utils.PropertiesUtils;

import static utils.LoggerUtils.log;

@Test(groups = {TestGroups.REGRESSION, TestGroups.LOGIN, TestGroups.SANITY})
public class TestSuccessfulLogin extends BaseTestClass {
    private final String testName = this.getClass().getName();
    private WebDriver driver;
    private String username;
    private String password;

    @BeforeMethod
    public void setUpTest(ITestContext testContext) {
        log.info("[SETUP TEST] {}", testName);
        driver = setUp(testContext);
        username = PropertiesUtils.getUsername();
        password = PropertiesUtils.getPassword();
    }

    @Test
    public void testSuccessfulLogin() {
        log.info("[TEST] Starting test: {}", testName);
        InventoryPage inventoryPage = new LoginPage(driver)
                .open()
                .loginUser(username, password)
                .verifyInventoryPage();

        Assert.assertEquals(inventoryPage.getInventoryPageTitle(), CommonStrings.getInventoryPageTitle(), "Incorrect page title!");
    }

    @AfterMethod
    public void tearDownTest(ITestResult testResult) {
        log.info("[END TEST] {}", testName);
        tearDown(driver, testResult);
    }
}
