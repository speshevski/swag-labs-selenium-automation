package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.LoggerUtils.log;

public class ScreenShotUtils {

    private static final String screenShotFolder = System.getProperty("user.dir") + PropertiesUtils.getScreenShotsFolder() + getCurrentDateTime() + "\\";

    private static String createScreenshotPath(String fileName) {
        return screenShotFolder + fileName + ".png";
    }

    public static String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PropertiesUtils.getDateTimeFormat());
        return LocalDateTime.now().format(formatter);
    }

    public static String takeScreenShot(WebDriver driver, String testName) {
        String pathToFile = createScreenshotPath(testName);
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(pathToFile);
        try {
            FileUtils.copyFile(screenshotFile, destinationFile);
            log.info("Screenshot for test: {}, saved to file: {}", testName, pathToFile);
        } catch (IOException e) {
            log.warn("Screenshot for test: {}, could not be saved to file: {}", testName, pathToFile);
            return null;
        }
        return pathToFile;
    }
}
