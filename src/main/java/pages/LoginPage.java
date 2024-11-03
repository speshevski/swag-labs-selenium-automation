package pages;

import data.PageUrlPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertiesUtils;

import static utils.LoggerUtils.log;

public class LoginPage {
    private final String BASE_URL = PropertiesUtils.getBaseUrl();
    private final String LOGIN_PAGE_URL = BASE_URL + PageUrlPaths.LOGIN_PAGE;
    private final WebDriver driver;

    // Low Level Library
    // Locators for Web Elements on that page
    private WebElement inputName;
    private WebElement inputPassword;
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        log.debug("LoginPage()");
    }

    // Mid-Level Library
    // Basic methods for actions on located elements

    public LoginPage openLoginPage() {
        driver.get(LOGIN_PAGE_URL);
        initializeWebElements();
        return this;
    }

    private void initializeWebElements() {
        inputName = driver.findElement(By.id("user-name"));
        inputPassword = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.id("login-button"));
    }

    public LoginPage typeUsername(String username) {
        log.debug("Type Username: {}", username);
        inputName.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        log.debug("Type Password: {}", password);
        inputPassword.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        log.debug("Click Login Button");
        loginButton.click();
        return this;
    }
    // High Level Library
    // Complex methods -> containing calls to 2 or more basic methods
}
