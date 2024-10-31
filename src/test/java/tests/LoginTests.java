package tests;

import data.TestGroups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

@Test(groups = {TestGroups.REGRESSION, TestGroups.LOGIN})
public class LoginTests {
    private static final String PATH_DRIVER_CHROME = "C:\\Selenium\\chromedriver.exe";
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", PATH_DRIVER_CHROME);
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2)); // waits for DOM structure of the page to be loaded
        driver.set(chromeDriver);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @Test(groups = {TestGroups.SANITY})
    public void testSuccessfulLogin() {
        getDriver().get(BASE_URL);

        WebElement inputName = getDriver().findElement(By.id("user-name"));
        inputName.sendKeys("standard_user");

        WebElement inputPassword = getDriver().findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        WebElement loginButton = getDriver().findElement(By.id("login-button"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3)); // explicit wait
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        WebElement pageTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test='title']")));
        Assert.assertEquals(pageTitle.getText(), "Products", "Incorrect page title!");
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        getDriver().get(BASE_URL);

        WebElement inputName = getDriver().findElement(By.id("user-name"));
        inputName.sendKeys("standard_user");

        WebElement inputPassword = getDriver().findElement(By.id("password"));
        inputPassword.sendKeys("wrong_password");

        WebElement loginButton = getDriver().findElement(By.id("login-button"));
        loginButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/", "Wrong URL");
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();  // Clear the ThreadLocal instance
        }
    }
}
