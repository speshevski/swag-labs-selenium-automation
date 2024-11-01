package utils;

import data.Environments;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    private static final String propertiesPath = "test.properties";
    private static final Properties properties = loadPropertiesFile();

    public static Properties loadPropertiesFile(String filePath) {
        Properties properties = new Properties();
        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            Assert.fail("Unable to load properties file: " + filePath + ": " + e.getMessage());
        }
        return properties;
    }

    public static Properties loadPropertiesFile() {
        return loadPropertiesFile(propertiesPath);
    }

    private static String getProperty(String key) {
        String property = properties.getProperty(key);
        Assert.assertNotNull(property, "Unable to find property: '" + key + "' in properties file: '" + propertiesPath + "'");
        return property;
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getDriverFolder() {
        return getProperty("driverFolder");
    }

    public static String getEnvironment() {
        return getProperty("environment");
    }

    private static String getTestBaseUrl() {
        return getProperty("testBaseUrl");
    }

    private static String getStageBaseUrl() {
        return getProperty("stageBaseUrl");
    }

    private static String getProdBaseUrl() {
        return getProperty("prodBaseUrl");
    }

    public static String getBaseUrl() {
        String env = getEnvironment();
        return switch (env) {
            case Environments.TEST -> getTestBaseUrl();
            case Environments.STAGE -> getStageBaseUrl();
            case Environments.PROD -> getProdBaseUrl();
            default -> throw new IllegalArgumentException("Unsupported environment: " + env);
        };
    }
}
