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
    private final String INVENTORY_PAGE_URL = BASE_URL + PageUrlPaths.INVENTORY_PAGE;
    private final WebDriver driver;

    // Low Level Library
    // Locators for Web Elements on that page
    private final By usernameTextFieldLocator = By.id("user-name");
    private final By passwordTextFieldLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        log.debug("LoginPage()");
    }

    // Mid-Level Library
    // Basic methods for actions on located elements
    public LoginPage openLoginPage() {
        log.debug("openLoginPage: {}", LOGIN_PAGE_URL);
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    public LoginPage typeUsername(String username) {
        log.debug("Type Username: {}", username);
        WebElement usernameTextField = driver.findElement(usernameTextFieldLocator);
        usernameTextField.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        log.debug("Type Password: {}", password);
        WebElement passwordTextField = driver.findElement(passwordTextFieldLocator);
        passwordTextField.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        log.debug("Click Login Button");
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
        return this;
    }

    public String getLoginPageUrl() {
        return LOGIN_PAGE_URL;
    }
    public String getInventoryPageUrl() {
        return INVENTORY_PAGE_URL;
    }

    // High Level Library
    // Complex methods -> containing calls to 2 or more basic methods
}
