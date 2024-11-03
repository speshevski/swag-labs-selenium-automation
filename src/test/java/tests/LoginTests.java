package tests;

import data.CommonStrings;
import data.PageUrlPaths;
import data.TestGroups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.PropertiesUtils;
import utils.WebdriverUtils;

import java.time.Duration;

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
                    .openLoginPage()
                    .typeUsername(USERNAME)
                    .typePassword(PASSWORD)
                    .clickLoginButton();

            log.debug("Verify Inventory Page");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // explicit wait
            wait.until(ExpectedConditions.urlToBe(loginPage.getInventoryPageUrl()));
            WebElement pageTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test='title']")));
            Assert.assertEquals(pageTitle.getText(), CommonStrings.getInventoryPageTitle(), "Incorrect page title!");
        } finally {
            log.info("[TEST] Finished test: testSuccessfulLogin()");
            WebdriverUtils.quitDriver(driver);
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        WebDriver driver = WebdriverUtils.setUpDriver();
        final String USERNAME = PropertiesUtils.getUsername();
        final String PASSWORD = "wrong_password";

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage
                    .openLoginPage()
                    .typeUsername(USERNAME)
                    .typePassword(PASSWORD)
                    .clickLoginButton();

            Assert.assertEquals(driver.getCurrentUrl(), loginPage.getLoginPageUrl(), "Wrong URL");

        } finally {
            WebdriverUtils.quitDriver(driver);
        }
    }
}
