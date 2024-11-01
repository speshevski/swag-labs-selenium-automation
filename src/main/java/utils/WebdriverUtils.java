package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

public class WebdriverUtils {

    public static WebDriver setUpDriver() {
        String browser = PropertiesUtils.getProperty("browser");
        String driverFolder = PropertiesUtils.getProperty("driverFolder");
        String driverPathChrome = driverFolder + "\\chromedriver.exe";
        String driverPathFirefox = driverFolder + "\\geckodriver.exe";
        String driverPathEdge = driverFolder + "\\msedgedriver.exe";

        WebDriver driver = null;

        switch (browser) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", driverPathChrome);
                driver = new ChromeDriver();
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", driverPathFirefox);
                driver = new FirefoxDriver();
                break;
            }
            case "edge": {
                System.setProperty("webdriver.edge.driver", driverPathEdge);
                driver = new EdgeDriver();
                break;
            }
            default: {
                Assert.fail("Can't create driver! Browser: " + browser + " is not supported!");
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));

        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
