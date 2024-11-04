package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    protected WebElement getWebElement(By locator) {
        log.trace("getWebElement(locator: {})", locator);
        return driver.findElement(locator);
    }

    protected WebElement getWebElement(By locator, int timeout) {
        log.trace("getWebElement(locator: {}, timeout: {})", locator, timeout);
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
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
}
