package pages;

import data.PageUrlPaths;
import data.Timeouts;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.PropertiesUtils;

import static utils.LoggerUtils.log;

public class LoginPage extends BasePageClass {
    private final String BASE_URL = PropertiesUtils.getBaseUrl();
    private final String LOGIN_PAGE_URL = BASE_URL + PageUrlPaths.LOGIN_PAGE;

    // Low Level Library
    // Locators for Web Elements on that page

    // default way of fetching selectors
    // private final By usernameTextFieldLocator = By.id("user-name");
    // private final By passwordTextFieldLocator = By.id("password");
    // private final By loginButtonLocator = By.id("login-button");

    // Page Factory model
    @FindBy(id = "user-name")
    private WebElement usernameTextField;

    @FindBy(id = "password")
    private WebElement passwordTextField;

    @FindBy(id = "login-button")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        log.debug("LoginPage()");
    }

    // Mid-Level Library
    // Basic methods for actions on located elements
    public LoginPage open(boolean verifyPage) {
        log.debug("openLoginPage: {}", LOGIN_PAGE_URL);
        openUrl(LOGIN_PAGE_URL);
        if (verifyPage) {
            verifyLoginPage();
        }
        return this;
    }

    public LoginPage open() {
        return open(true);
    }

    public Boolean isUsernameTextFieldDisplayed() {
        log.debug("isUsernameTextFieldDisplayed()");
        return isWebElementDisplayed(usernameTextField, Timeouts.TIME_SHORTEST);
    }

    public Boolean isUsernameTextFieldEnabled() {
        log.debug("isUsernameTextFieldEnabled()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username field is not displayed on Login Page!");
        return isWebElementEnabled(usernameTextField);
    }

    public LoginPage typeUsername(String username) {
        log.debug("Type Username: {}", username);
        Assert.assertTrue(isUsernameTextFieldEnabled(), "Username field is not enabled on Login Page!");
        clearAndInputTextToWebElement(usernameTextField, username);
        return this;
    }

    public LoginPage typePassword(String password) {
        log.debug("Type Password: {}", password);
        clearAndInputTextToWebElement(passwordTextField, password);
        return this;
    }

    public Boolean isLoginButtonDisplayed() {
        log.debug("isLoginButtonDisplayed()");
        return isWebElementDisplayed(loginButton);
    }

    public Boolean isLoginButtonEnabled() {
        log.debug("isLoginButtonEnabled()");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login button is not displayed on Login Page!");
        return isWebElementEnabled(loginButton);
    }

    public InventoryPage clickLoginButton() {
        log.debug("Click Login Button");
        Assert.assertTrue(isLoginButtonEnabled(), "Login button is not enabled on Login Page!");
        clickOnWebElement(loginButton);
        return new InventoryPage(driver).verifyInventoryPage();
    }

    // Example how to use Javascript Executor
    public InventoryPage clickLoginButtonJs() {
        log.debug("clickLoginButtonJavascriptExecutor()");
        Assert.assertTrue(isLoginButtonEnabled(), "Login button is not enabled on Login Page!");
        clickOnWebElementJs(loginButton);
        return new InventoryPage(driver).verifyInventoryPage();
    }

    public LoginPage clickLoginButtonNoProgress() {
        log.debug("Click Login Button with no progress");
        Assert.assertTrue(isLoginButtonEnabled(), "Login button is not enabled on Login Page!");
        clickOnWebElement(loginButton);
        return new LoginPage(driver).verifyLoginPage();
    }

    public String getLoginButtonTitle() {
        log.debug("getLoginButtonTitle()");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login button is not displayed on Login Page!");
        return getAttributeValueFromWebElement(loginButton, "value");
    }

    public LoginPage verifyLoginPage() {
        waitForUrlChangeToExactUrl(LOGIN_PAGE_URL, Timeouts.TIME_SHORTEST);
        return this;
    }

    public String getLoginPageUrl() {
        return LOGIN_PAGE_URL;
    }

    // High Level Library
    // Complex methods -> containing calls to 2 or more basic methods

    /**
     * Login to SwagLabs with specified user
     *
     * @param username {String} username
     * @param password {String} password
     * @return [InventoryPage] new InventoryPage instance
     */
    public InventoryPage loginUser(String username, String password) {
        log.info("loginUser(): username: {}, password: {}", username, password);
        return typeUsername(username)
                .typePassword(password)
                .clickLoginButton();
    }
}
