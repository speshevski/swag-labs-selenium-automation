package tests;

import data.TestGroups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {TestGroups.REGRESSION, TestGroups.LOGIN})
public class LoginTests {

    @Test(groups = {TestGroups.SANITY})
    public void testSuccessfulLogin() {
        final String pathDriverChrome = "C:\\Selenium\\chromedriver.exe";
        final String baseUrl = "https://www.saucedemo.com/";
        System.setProperty("webdriver.chrome.driver", pathDriverChrome);
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            driver.get(baseUrl);

            final WebElement inputName = driver.findElement(By.id("user-name"));
            inputName.sendKeys("standard_user");

            final WebElement inputPassword = driver.findElement(By.id("password"));
            inputPassword.sendKeys("secret_sauce");

            final WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Wrong URL");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        final String pathDriverChrome = "C:\\Selenium\\chromedriver.exe";
        final String baseUrl = "https://www.saucedemo.com/";
        System.setProperty("webdriver.chrome.driver", pathDriverChrome);
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            driver.get(baseUrl);

            final WebElement inputName = driver.findElement(By.id("user-name"));
            inputName.sendKeys("standard_user");

            final WebElement inputPassword = driver.findElement(By.id("password"));
            inputPassword.sendKeys("wrong_password");

            final WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Wrong URL");
        } finally {
            driver.quit();
        }
    }
}
