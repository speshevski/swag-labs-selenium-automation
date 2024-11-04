package tests;

import data.CommonStrings;
import data.TestGroups;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.PropertiesUtils;
import utils.WebdriverUtils;

import static utils.LoggerUtils.log;

@Test(groups = {TestGroups.REGRESSION, TestGroups.LOGIN})
public class LoginTests {


    @Test(groups = {TestGroups.SANITY})
    public void testSuccessfulLogin() {
        log.info("[TEST] Starting test: testSuccessfulLogin()");
        WebDriver driver = WebdriverUtils.setUpDriver();
        final String USERNAME = PropertiesUtils.getUsername();
        final String PASSWORD = PropertiesUtils.getPassword();

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage
                    .open()
                    .loginUser(USERNAME, PASSWORD);

            InventoryPage inventoryPage = new InventoryPage(driver);
            inventoryPage.verifyInventoryPage();
            Assert.assertEquals(inventoryPage.getInventoryPageTitle(), CommonStrings.getInventoryPageTitle(), "Incorrect page title!");
        } finally {
            log.info("[TEST] Finished test: testSuccessfulLogin()");
            WebdriverUtils.quitDriver(driver);
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        log.info("[TEST] Starting test: testUnsuccessfulLoginWrongPassword()");
        WebDriver driver = WebdriverUtils.setUpDriver();
        final String USERNAME = PropertiesUtils.getUsername();
        final String PASSWORD = "wrong_password";

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage
                    .open()
                    .typeUsername(USERNAME)
                    .typePassword(PASSWORD)
                    .clickLoginButton();

            Assert.assertEquals(driver.getCurrentUrl(), loginPage.getLoginPageUrl(), "Wrong URL");

        } finally {
            log.info("[TEST] Finished test: testUnsuccessfulLoginWrongPassword()");
            WebdriverUtils.quitDriver(driver);
        }
    }
}
