package pages;

import data.PageUrlPaths;
import data.Timeouts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PropertiesUtils;

import static utils.LoggerUtils.log;

public class InventoryPage extends BasePageClass {
    private final String BASE_URL = PropertiesUtils.getBaseUrl();
    private final String INVENTORY_PAGE_URL = BASE_URL + PageUrlPaths.INVENTORY_PAGE;

    private final By inventoryPageTitle = By.cssSelector("[data-test='title']");

    public InventoryPage(WebDriver driver) {
        super(driver);
        log.debug("InventoryPage()");
    }

    public InventoryPage open() {
        log.debug("openLoginPage: {}", INVENTORY_PAGE_URL);
        openUrl(INVENTORY_PAGE_URL);
        return this;
    }

    public String getInventoryPageTitle() {
        log.debug("getInventoryPageTitle()");
        return getTextFromWebElement(getWebElement(inventoryPageTitle));
    }

    public void verifyInventoryPage() {
        waitForUrlChange(INVENTORY_PAGE_URL, Timeouts.TIME_SHORTEST);
    }
}
