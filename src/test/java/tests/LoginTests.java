package tests;

import data.CommonStrings;
import data.TestGroups;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.PropertiesUtils;
import utils.ScreenShotUtils;
import utils.WebdriverUtils;

import static utils.LoggerUtils.log;

@Test(groups = {TestGroups.REGRESSION, TestGroups.LOGIN})
public class LoginTests {

    @Test(groups = {TestGroups.SANITY})
    public void testSuccessfulLogin() {
        final String testName = "testSuccessfulLogin()";
        boolean testSuccess = false;

        log.info("[TEST] Starting test: {}", testName);
        WebDriver driver = WebdriverUtils.setUpDriver();
        final String USERNAME = PropertiesUtils.getUsername();
        final String PASSWORD = PropertiesUtils.getPassword();

        try {
            InventoryPage inventoryPage = new LoginPage(driver)
                    .open()
                    .loginUser(USERNAME, PASSWORD)
                    .verifyInventoryPage();

            Assert.assertEquals(inventoryPage.getInventoryPageTitle(), CommonStrings.getInventoryPageTitle(), "Incorrect page title!");
            testSuccess = true;
        } finally {
            log.info("[TEST] Finished test: {}", testName);
            if (!testSuccess) {
                ScreenShotUtils.takeScreenShot(driver, testName);
            }
            WebdriverUtils.quitDriver(driver);
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        final String testName = "testUnsuccessfulLoginWrongPassword()";
        boolean testSuccess = false;
        log.info("[TEST] Starting test {}:", testName);
        WebDriver driver = WebdriverUtils.setUpDriver();
        final String USERNAME = PropertiesUtils.getUsername();
        final String PASSWORD = "wrong_password";

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage
                    .open()
                    .typeUsername(USERNAME)
                    .typePassword(PASSWORD)
                    .clickLoginButtonNoProgress();

            Assert.assertEquals(driver.getCurrentUrl(), loginPage.getLoginPageUrl(), "Wrong URL");
            testSuccess = true;
        } finally {
            log.info("[TEST] Finished test {}:", testName);
            if (!testSuccess) {
                ScreenShotUtils.takeScreenShot(driver, testName);
            }
            WebdriverUtils.quitDriver(driver);
        }
    }
}
