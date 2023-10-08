package litecart;

import litecart.pages.CatalogPage;
import litecart.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static litecart.config.WebDriverContext.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LogsTest extends BaseTest {
    private LoginPage loginPage;
    private CatalogPage catalogPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
        catalogPage = new CatalogPage();
    }

    @Test
    void loginToAdminTest() {
        getWebDriver().get("http://localhost:8080/litecart/admin");
        loginPage.setUserName("admin");
        loginPage.setPassword("admin");
        loginPage.clickLoginBtn();
        getWebDriver().get("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        catalogPage.openAllFolders();
        assertFalse(catalogPage.isLogsAbsentForEachItem());
    }
}
