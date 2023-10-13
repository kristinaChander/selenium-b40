package litecart.pages;

import litecart.config.WebDriverContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static litecart.utils.LogUtils.getBrowserConsoleLogs;

public class CatalogPage extends BasePage {
    @FindBy(xpath = "//a[contains(text(),'Add New Product')]")
    private WebElement addProduct;

    @FindBy(xpath = "//*[@class='dataTable']//*[@class='fa fa-folder']/../a")
    private List<WebElement> closedFolders;

    @FindBy(xpath = "//*[@class='dataTable']//img/../a")
    private List<WebElement> itemsList;

    public void clickAddNewProduct() {
        addProduct.click();
    }

    public void openAllFolders() {
        closedFolders.forEach(WebElement::click);
    }

    public boolean isLogsAbsentForEachItem() {
        for (int i = 0; i < itemsList.size(); i++) {
            if (!itemsList.get(i).isDisplayed()) {
                new WebDriverWait(WebDriverContext.getWebDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(itemsList));
            }
            itemsList.get(i).click();
            List<LogEntry> logs = getBrowserConsoleLogs();
            if (logs.size() > 0) {
                return true;
            }
            WebDriverContext.getWebDriver().navigate().back();
        }
        return false;
    }
}

