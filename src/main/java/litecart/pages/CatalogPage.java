package litecart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogPage extends BasePage {
    @FindBy(xpath = "//a[contains(text(),'Add New Product')]")
    private WebElement addProduct;

    public void clickAddNewProduct() {
        addProduct.click();
    }
}
