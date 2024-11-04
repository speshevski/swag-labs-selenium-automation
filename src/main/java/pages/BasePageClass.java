package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

import static utils.LoggerUtils.log;

public abstract class BasePageClass {
    protected WebDriver driver;

    protected BasePageClass(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait getWebDriverWait(int timeout) {
        log.trace("getWebDriverWait(timeout: {})", timeout);
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    protected void openUrl(String url) {
        log.trace("openUrl(url: {})", url);
        driver.get(url);
    }

    protected String getCurrentUrl() {
        log.trace("getCurrentUrl()");
        return driver.getCurrentUrl();
    }

    protected WebElement getWebElement(By locator) {
        log.trace("getWebElement(locator: {})", locator);
        return driver.findElement(locator);
    }

    protected WebElement getWebElement(By locator, int timeout) {
        log.trace("getWebElement(locator: {}, timeout: {})", locator, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected Boolean isWebElementDisplayed(By locator) {
        log.trace("isWebElementDisplayed(locator: {})", locator);
        try{
            return getWebElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected Boolean isWebElementDisplayed(By locator, int timeout) {
        log.trace("isWebElementDisplayed(locator: {}, timeout: {})", locator, timeout);
        try{
            return getWebElement(locator, timeout).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected Boolean isWebElementDisplayed(WebElement element) {
        log.trace("isWebElementDisplayed(element: {})", element);
        try{
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected Boolean isWebElementEnabled(By locator) {
        log.trace("isWebElementEnabled(locator: {})", locator);
        try{
            return getWebElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected Boolean isWebElementEnabled(By locator, int timeout) {
        log.trace("isWebElementEnabled(locator: {}, timeout: {})", locator, timeout);
        try{
            return getWebElement(locator, timeout).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected Boolean isWebElementEnabled(WebElement element) {
        log.trace("isWebElementEnabled(element: {})", element);
        try{
            return element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void waitForUrlChange(String url, int timeout) {
        log.trace("waitForUrlChange(rul: {}, timeout: {})", url, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        wait.until(ExpectedConditions.urlContains(url));
    }

    protected void waitForUrlChangeToExactUrl(String url, int timeout) {
        log.trace("waitForUrlChangeToExactUrl(rul: {}, timeout: {})", url, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    protected WebElement waitForElementToBeVisible(By locator, int timeout) {
        log.trace("waitForElementToBeVisible(locator: {}, timeout: {})", locator, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementToBeVisible(WebElement element, int timeout) {
        log.trace("waitForElementToBeVisible(element: {}, timeout: {})", element, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementToBeClickable(By locator, int timeout) {
        log.trace("waitForElementToBeClickable(locator: {}, timeout: {})", locator, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementToBeClickable(WebElement element, int timeout) {
        log.trace("waitForElementToBeClickable(element: {}, timeout: {})", element, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected Boolean waitForElementToBeSelected(By locator, int timeout) {
        log.trace("waitForElementToBeSelected(locator: {}, timeout: {})", locator, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    protected Boolean waitForElementToBeSelected(WebElement element, int timeout) {
        log.trace("waitForElementToBeSelected(element: {}, timeout: {})", element, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    protected void clickOnWebElement(WebElement element) {
        log.trace("clickWebElement(element: {})", element);
        element.click();
    }

    protected void clickOnWebElement(WebElement element, int timeout) {
        log.trace("clickWebElement(element: {}, timeout: {})", element, timeout);
        waitForElementToBeClickable(element, timeout).click();
    }

    protected void clickOnWebElement(By locator, int timeout) {
        log.trace("clickWebElement(locator: {}, timeout: {})", locator, timeout);
        WebElement element = waitForElementToBeClickable(locator, timeout);
        element.click();
    }

    protected void inputTextToWebElement(WebElement element, String text) {
        log.trace("inputTextToWebElement(element: {}, text: {})", element, text);
        element.sendKeys(text);
    }

    protected void clearAndInputTextToWebElement(WebElement element, String text) {
        log.trace("clearAndInputTextToWebElement(element: {}, text: {})", element, text);
        element.clear();
        element.sendKeys(text);
    }

    protected String getTextFromWebElement(WebElement element) {
        log.trace("getTextFromWebElement(element: {})", element);
        return element.getText();
    }
}
