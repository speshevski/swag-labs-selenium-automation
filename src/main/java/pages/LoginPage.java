package pages;

import data.PageUrlPaths;
import data.Timeouts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertiesUtils;

import static utils.LoggerUtils.log;

public class LoginPage extends BasePageClass {
    private final String BASE_URL = PropertiesUtils.getBaseUrl();
    private final String LOGIN_PAGE_URL = BASE_URL + PageUrlPaths.LOGIN_PAGE;

    // Low Level Library
    // Locators for Web Elements on that page
    private final By usernameTextFieldLocator = By.id("user-name");
    private final By passwordTextFieldLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
        log.debug("LoginPage()");
    }

    // Mid-Level Library
    // Basic methods for actions on located elements
    public LoginPage open() {
        log.debug("openLoginPage: {}", LOGIN_PAGE_URL);
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    public LoginPage typeUsername(String username) {
        log.debug("Type Username: {}", username);
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        usernameTextField.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        log.debug("Type Password: {}", password);
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        passwordTextField.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        log.debug("Click Login Button");
        WebElement loginButton = waitForElementToBeClickable(loginButtonLocator, Timeouts.TIME_SHORTEST);
        clickOnWebElement(loginButton);
        return this;
    }

    public void verifyLoginPage() {
        waitForUrlChangeToExactUrl(LOGIN_PAGE_URL, Timeouts.TIME_SHORTEST);
    }

    public String getLoginPageUrl() {
        return LOGIN_PAGE_URL;
    }

    // High Level Library
    // Complex methods -> containing calls to 2 or more basic methods
    public void loginUser(String username, String password) {
        typeUsername(username);
        typePassword(password);
        clickLoginButton();
    }
}
