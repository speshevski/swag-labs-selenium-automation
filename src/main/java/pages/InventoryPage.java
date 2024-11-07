package pages;

import data.PageUrlPaths;
import data.Timeouts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PropertiesUtils;

import static utils.LoggerUtils.log;

public class InventoryPage extends BasePageClass {
    private final String BASE_URL = PropertiesUtils.getBaseUrl();
    private final String INVENTORY_PAGE_URL = BASE_URL + PageUrlPaths.INVENTORY_PAGE;

    // private final By inventoryPageTitle = By.cssSelector("[data-test='title']");

    @FindBy(css = "[data-test='title']")
    private WebElement inventoryTitle;

    public InventoryPage(WebDriver driver) {
        super(driver);
        log.debug("InventoryPage()");
    }

    public InventoryPage open(boolean verifyPage) {
        log.debug("openInventoryPage: {}", INVENTORY_PAGE_URL);
        openUrl(INVENTORY_PAGE_URL);
        if (verifyPage) {
            verifyInventoryPage();
        }
        return this;
    }

    public InventoryPage open() {
        return open(true);
    }

    public String getInventoryPageTitle() {
        log.debug("getInventoryPageTitle()");
        return getTextFromWebElement(inventoryTitle);
    }

    public InventoryPage verifyInventoryPage() {
        waitForUrlChange(INVENTORY_PAGE_URL, Timeouts.TIME_SHORTEST);
        return this;
    }
}
