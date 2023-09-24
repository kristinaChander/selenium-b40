package litecart;

import litecart.pages.CatalogPage;
import litecart.pages.HomeAdminPage;
import litecart.pages.LoginPage;
import litecart.pages.NewProductPage;
import litecart.utils.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Arrays;

import static litecart.config.WebDriverContext.getWebDriver;
import static litecart.utils.FileUtils.getResourceAsFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddProductToAdminTest extends BaseTest {
    public static final String PRODUCT_NAME = "Bee Duck";
    public static final String PRODUCT_CODE = "Bee - 12345";
    public static final String CATEGORY = "Rubber Ducks";
    public static final String FEMALE_GROUP = "Female";
    public static final String MALE_GROUP = "Male";
    public static final String UNISEX_GROUP = "Unisex";
    public static final String QUANTITY = "100";
    public static final String SOLD_OUT_STATUS = "Temporary sold out";
    public static final String DATE_VALID_FROM = "12122022";
    public static final String DATE_VALID_TO = "12122030";
    public static final String INFORMATION_TAB = "Information";
    public static final String MANUFACTURER = "ACME Corp.";
    public static final String KEY_WORDS = "Just a duck";
    public static final String SHORT_DESCRIPTION = "Short duck description";
    public static final String LONG_DESCRIPTION = "Duck that attentively listens to you";
    public static final String TITLE = "Duck title";
    public static final String META_DESCRIPTION = "MetaDuck";
    public static final String PRICES_TAB = "Prices";
    public static final String PURCHASE_PRICE = "78.09";
    public static final String CURRENCY_VALUE = "US Dollars";
    public static final String PRICE_USD = "65.09";
    public static final String PRICE_EUR = "12.21";
    public static final String CATALOG = "Catalog";
    public static final String QUANTITY_UNIT = "pcs";
    public static final String DELIVERY_STATUS = "3-5 days";
    private LoginPage loginPage;
    private HomeAdminPage homeAdminPage;
    private CatalogPage catalogPage;
    private NewProductPage newProductPage;
    @Value("classpath:Badeente_Biene.jpg")
    private Resource rubberDuckFile;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
        homeAdminPage = new HomeAdminPage();
        catalogPage = new CatalogPage();
        newProductPage = new NewProductPage();
    }

    @Test
    void loginToAdminTest() {
        getWebDriver().get("http://localhost:8080/litecart/admin");
        loginPage.setUserName("admin");
        loginPage.setPassword("admin");
        loginPage.clickLoginBtn();
        homeAdminPage.selectParentItemByName(CATALOG);
        catalogPage.clickAddNewProduct();
        newProductPage.setItemEnabled();
        newProductPage.setName(PRODUCT_NAME);
        newProductPage.setCode(PRODUCT_CODE);
        newProductPage.setCategory(CATEGORY);
        newProductPage.setDefaultCategory(CATEGORY);
        newProductPage.setProductGroups(Arrays.asList(FEMALE_GROUP, MALE_GROUP, UNISEX_GROUP));
        newProductPage.setQuantity(QUANTITY);
        newProductPage.setQuantityUnit(QUANTITY_UNIT);
        newProductPage.setDeliveryStatus(DELIVERY_STATUS);
        newProductPage.setSoldOutStatus(SOLD_OUT_STATUS);
        newProductPage.uploadImage(getResourceAsFile(rubberDuckFile));
        newProductPage.setDateValidFrom(DATE_VALID_FROM);
        newProductPage.setDateValidTo(DATE_VALID_TO);
        newProductPage.switchToTab(INFORMATION_TAB);
        newProductPage.selectManufacturer(MANUFACTURER);
        newProductPage.setKeyWords(KEY_WORDS);
        newProductPage.setShortDescription(SHORT_DESCRIPTION);
        newProductPage.setLongDescription(LONG_DESCRIPTION);
        newProductPage.setHeadTitle(TITLE);
        newProductPage.setMetaDescription(META_DESCRIPTION);
        newProductPage.switchToTab(PRICES_TAB);
        newProductPage.setPurchasePrice(PURCHASE_PRICE);
        newProductPage.selectCurrency(CURRENCY_VALUE);
        newProductPage.setPriceUSD(PRICE_USD);
        newProductPage.setPriceEUR(PRICE_EUR);
        newProductPage.save();
        newProductPage.clickProductName(PRODUCT_NAME);
        assertEquals(PRODUCT_NAME, newProductPage.getProductName(), "Product Name is incorrect");
    }
}
