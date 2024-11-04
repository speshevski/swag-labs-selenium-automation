package data;

import dev.failsafe.internal.util.Assert;
import utils.PropertiesUtils;

import java.util.Properties;

public final class CommonStrings {
    public static final String localeFilePath = "locale_" + PropertiesUtils.getLocale() + ".loc";
    public static Properties locale = PropertiesUtils.loadPropertiesFile(localeFilePath);

    private static String getLocalizedString(String property) {
        String value = locale.getProperty(property);
        Assert.notNull(value, "String " + property + " does not exist in the locale file");
        return value;
    }

    public static String getInventoryPageTitle() {
        return getLocalizedString("inventoryPageTitle");
    }
}
