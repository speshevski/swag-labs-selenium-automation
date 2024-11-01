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
import utils.PropertiesUtils;
import utils.WebdriverUtils;

import java.time.Duration;

@Test(groups = {TestGroups.REGRESSION, TestGroups.LOGIN})
public class LoginTests {

    @Test(groups = {TestGroups.SANITY})
    public void testSuccessfulLogin() {
        WebDriver driver = WebdriverUtils.setUpDriver();
        final String BASE_URL = PropertiesUtils.getBaseUrl();
        final String USERNAME = PropertiesUtils.getUsername();
        final String PASSWORD = PropertiesUtils.getPassword();
        final String LOGIN_PAGE_URL = BASE_URL + PageUrlPaths.LOGIN_PAGE;
        final String INVENTORY_PAGE_URL = BASE_URL + PageUrlPaths.INVENTORY_PAGE;

        try {
            driver.get(LOGIN_PAGE_URL);
            WebElement inputName = driver.findElement(By.id("user-name"));
            inputName.sendKeys(USERNAME);

            WebElement inputPassword = driver.findElement(By.id("password"));
            inputPassword.sendKeys(PASSWORD);

            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // explicit wait
            wait.until(ExpectedConditions.urlToBe(INVENTORY_PAGE_URL));
            WebElement pageTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test='title']")));
            Assert.assertEquals(pageTitle.getText(), CommonStrings.INVENTORY_PAGE_TITLE, "Incorrect page title!");
        } finally {
            WebdriverUtils.quitDriver(driver);
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        WebDriver driver = WebdriverUtils.setUpDriver();
        final String BASE_URL = PropertiesUtils.getBaseUrl();
        final String USERNAME = PropertiesUtils.getUsername();
        final String PASSWORD = "wrong_password";
        final String LOGIN_PAGE_URL = BASE_URL + PageUrlPaths.LOGIN_PAGE;

        try {
            driver.get(LOGIN_PAGE_URL);
            WebElement inputName = driver.findElement(By.id("user-name"));
            inputName.sendKeys(USERNAME);

            WebElement inputPassword = driver.findElement(By.id("password"));
            inputPassword.sendKeys(PASSWORD);

            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL, "Wrong URL");

        } finally {
            WebdriverUtils.quitDriver(driver);
        }
    }
}
