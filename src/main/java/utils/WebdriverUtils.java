package utils;

import data.Browsers;
import data.Timeouts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

public class WebdriverUtils {

    public static WebDriver setUpDriver() {
        final String browser = PropertiesUtils.getBrowser();
        WebDriver driver = null;
        LoggerUtils.log.info("setUpDriver({})", browser);
        switch (browser) {
            case Browsers.CHROME: {
                driver = new ChromeDriver();
                break;
            }
            case Browsers.FIREFOX: {
                driver = new FirefoxDriver();
                break;
            }
            case Browsers.EDGE: {
                driver = new EdgeDriver();
                break;
            }
            default: {
                Assert.fail("Can't create driver! Browser: " + browser + " is not supported!");
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts.IMPLICIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Timeouts.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Timeouts.SCRIPT_LOAD_TIMEOUT));

        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
