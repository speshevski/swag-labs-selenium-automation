package tests;

import data.TestGroups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WebdriverUtils;

import java.time.Duration;

@Test(groups = {TestGroups.REGRESSION, TestGroups.LOGIN})
public class LoginTests {
    private static final String BASE_URL = "https://www.saucedemo.com/";

    @Test(groups = {TestGroups.SANITY})
    public void testSuccessfulLogin() {
        WebDriver driver = WebdriverUtils.setUpDriver();
        try {
            driver.get(BASE_URL);
            WebElement inputName = driver.findElement(By.id("user-name"));
            inputName.sendKeys("standard_user");

            WebElement inputPassword = driver.findElement(By.id("password"));
            inputPassword.sendKeys("secret_sauce");

            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // explicit wait
            wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
            WebElement pageTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test='title']")));
            Assert.assertEquals(pageTitle.getText(), "Products", "Incorrect page title!");
        } finally {
            WebdriverUtils.quitDriver(driver);
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        WebDriver driver = WebdriverUtils.setUpDriver();
        try {
            driver.get(BASE_URL);
            WebElement inputName = driver.findElement(By.id("user-name"));
            inputName.sendKeys("standard_user");

            WebElement inputPassword = driver.findElement(By.id("password"));
            inputPassword.sendKeys("wrong_password");

            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Wrong URL");

        } finally {
            WebdriverUtils.quitDriver(driver);
        }
    }
}
