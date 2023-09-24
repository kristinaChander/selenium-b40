package litecart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

public class NewProductPage extends BasePage {
    @FindBy(xpath = "//*[contains(text(),'Enabled')]")
    private WebElement statusEnabled;
    @FindBy(css = "input[name='name[en]']")
    private WebElement nameField;
    @FindBy(css = "input[name='code']")
    private WebElement codeField;
    @FindBy(xpath = "//strong[text()='Categories']/..//input")
    private List<WebElement> categoryCheckBoxes;
    @FindBy(css = "select[name='default_category_id']")
    private WebElement defaultCategoryField;
    @FindBy(xpath = "//strong[contains(text(),'Product Groups')]/..//tr//input/../following-sibling::td")
    private List<WebElement> productGroups;
    @FindBy(css = "input[name='quantity']")
    private WebElement quantityValue;
    @FindBy(css = "select[name='quantity_unit_id']")
    private WebElement quantityUnitField;
    @FindBy(css = "select[name='delivery_status_id']")
    private WebElement deliveryStatusSelect;
    @FindBy(xpath = "//strong[contains(text(),'Product Groups')]/..//tr//input")
    private List<WebElement> productGroupCheckBoxes;
    @FindBy(css = "select[name='sold_out_status_id']")
    private WebElement soldOutStatusSelect;
    @FindBy(css = "input[type='file']")
    private WebElement uploadImageField;
    @FindBy(xpath = "//*[contains(text(),'Date Valid From')]/../input")
    private WebElement dateFrom;
    @FindBy(xpath = "//*[contains(text(),'Date Valid To')]/../input")
    private WebElement dateTo;
    @FindBy(css = "button[name='save']")
    private WebElement saveBtn;
    @FindBy(css = "div .tabs li a")
    private List<WebElement> catalogTabs;
    @FindBy(css = "select[name='manufacturer_id']")
    private WebElement manufacturer;
    @FindBy(css = "select[name='supplier_id']")
    private WebElement supplier;
    @FindBy(css = "input[name='keywords']")
    private WebElement keywordsField;
    @FindBy(css = "input[name='short_description[en]']")
    private WebElement shortDescriptionField;
    @FindBy(css = ".trumbowyg-editor")
    private WebElement longDescriptionField;
    @FindBy(css = "input[name='head_title[en]']")
    private WebElement headTitleField;
    @FindBy(css = "input[name='meta_description[en]']")
    private WebElement metaDescriptionField;
    @FindBy(css = "input[name='purchase_price']")
    private WebElement purchasePriceField;
    @FindBy(css = "select[name='purchase_price_currency_code']")
    private WebElement currencyField;
    @FindBy(css = "input[name='gross_prices[USD]']")
    private WebElement priceUSDTax;
    @FindBy(css = "input[name='gross_prices[EUR]']")
    private WebElement priceEurTax;
    @FindBy(css = ".dataTable td:nth-child(3) a")
    private List<WebElement> productList;
    @FindBy(css = "input[name='prices[USD]']")
    private WebElement priceUSD;
    @FindBy(css = "input[name='prices[EUR]']")
    private WebElement priceEur;

    public void setItemEnabled() {
        statusEnabled.click();
    }

    public void setName(String value) {
        nameField.sendKeys(value);
    }

    public void setCode(String value) {
        codeField.sendKeys(value);
    }

    public void setCategory(String value) {
        for (WebElement webElement : categoryCheckBoxes) {
            if (webElement.getAttribute("data-name").equalsIgnoreCase(value)) {
                webElement.click();
            }
        }
    }

    public void setDefaultCategory(String value) {
        Select defaultCategory = new Select(defaultCategoryField);
        defaultCategory.selectByVisibleText(value);
    }

    public void setProductGroups(List<String> list) {
        for (String s : list) {
            for (int i = 0; i < productGroups.size(); i++) {
                if (s.equalsIgnoreCase(productGroups.get(i).getAttribute("textContent"))) {
                    productGroupCheckBoxes.get(i).click();
                }
            }
        }
    }

    public void setQuantity(String value) {
        quantityValue.clear();
        quantityValue.sendKeys(value);
    }

    public void setQuantityUnit(String value) {
        Select quantityField = new Select(quantityUnitField);
        quantityField.selectByVisibleText(value);
    }

    public void setDeliveryStatus(String value) {
        Select deliveryStatus = new Select(deliveryStatusSelect);
        deliveryStatus.selectByVisibleText(value);
    }

    public void setSoldOutStatus(String value) {
        Select soldOutStatus = new Select(soldOutStatusSelect);
        soldOutStatus.selectByVisibleText(value);
    }

    public void uploadImage(File file) {
        uploadImageField.sendKeys(file.getAbsolutePath());
    }

    public void setDateValidFrom(String value) {
        dateFrom.sendKeys(value);
    }

    public void setDateValidTo(String value) {
        dateTo.sendKeys(value);
    }

    public void save() {
        saveBtn.click();
    }

    public void switchToTab(String value) {
        for (WebElement tab : catalogTabs) {
            if (tab.getAttribute("textContent").equalsIgnoreCase(value)) {
                tab.click();
                return;
            }
        }
    }

    public void selectManufacturer(String value) {
        Select manufacturerField = new Select(manufacturer);
        manufacturerField.selectByVisibleText(value);
    }

    public void setKeyWords(String value) {
        keywordsField.sendKeys(value);
    }

    public void setShortDescription(String value) {
        shortDescriptionField.sendKeys(value);
    }

    public void setLongDescription(String value) {
        longDescriptionField.sendKeys(value);
    }

    public void setHeadTitle(String value) {
        headTitleField.sendKeys(value);
    }

    public void setMetaDescription(String value) {
        metaDescriptionField.sendKeys(value);
    }

    public void setPurchasePrice(String value) {
        purchasePriceField.clear();
        purchasePriceField.sendKeys(value);
    }

    public void selectCurrency(String value) {
        Select currency = new Select(currencyField);
        currency.selectByVisibleText(value);
    }

    public void setPriceUSD(String value) {
        priceUSD.clear();
        priceUSD.sendKeys(value);
    }

    public void setPriceEUR(String value) {
        priceEur.clear();
        priceEur.sendKeys(value);
    }

    public void clickProductName(String value) {
        for (WebElement product : productList) {
            if (product.getAttribute("textContent").equalsIgnoreCase(value)) {
                product.click();
                return;
            }
        }
    }

    public String getProductName() {
        return nameField.getAttribute("value");
    }

    public String getProductCode() {
        return codeField.getAttribute("value");
    }
}
