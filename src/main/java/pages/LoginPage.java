package pages;

import data.PageUrlPaths;
import data.Timeouts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
        openUrl(LOGIN_PAGE_URL);
        return this;
    }

    public Boolean isUsernameTextFieldDisplayed() {
        log.debug("isUsernameTextFieldDisplayed()");
        return isWebElementDisplayed(usernameTextFieldLocator, Timeouts.TIME_SHORTEST);
    }

    public Boolean isUsernameTextFieldEnabled() {
        log.debug("isUsernameTextFieldEnabled()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username field is not displayed on Login Page!");
        return isWebElementEnabled(usernameTextFieldLocator, Timeouts.TIME_SHORTEST);
    }

    public LoginPage typeUsername(String username) {
        log.debug("Type Username: {}", username);
        Assert.assertTrue(isUsernameTextFieldEnabled(), "Username field is not enabled on Login Page!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        clearAndInputTextToWebElement(usernameTextField, username);
        return this;
    }

    public LoginPage typePassword(String password) {
        log.debug("Type Password: {}", password);
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        clearAndInputTextToWebElement(passwordTextField, password);
        return this;
    }

    public Boolean isLoginButtonDisplayed() {
        log.debug("isLoginButtonDisplayed()");
        return isWebElementDisplayed(loginButtonLocator, Timeouts.TIME_SHORTEST);
    }

    public Boolean isLoginButtonEnabled() {
        log.debug("isLoginButtonEnabled()");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login button is not displayed on Login Page!");
        return isWebElementEnabled(loginButtonLocator, Timeouts.TIME_SHORTEST);
    }

    public LoginPage clickLoginButton() {
        log.debug("Click Login Button");
        Assert.assertTrue(isLoginButtonEnabled(), "Login button is not enabled on Login Page!");
        WebElement loginButton = waitForElementToBeClickable(loginButtonLocator, Timeouts.TIME_SHORTEST);
        clickOnWebElement(loginButton);
        return this;
    }

    public String getLoginButtonTitle() {
        log.debug("getLoginButtonTitle()");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login button is not displayed on Login Page!");
        WebElement loginButton = waitForElementToBeClickable(loginButtonLocator, Timeouts.TIME_SHORTEST);
        return getAttributeValueFromWebElement(loginButton, "value");
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
