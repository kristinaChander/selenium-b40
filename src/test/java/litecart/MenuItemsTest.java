package litecart;

import litecart.pages.HomeAdminPage;
import litecart.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static litecart.config.WebDriverContext.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuItemsTest extends BaseTest {
    private LoginPage loginPage;
    private HomeAdminPage homeAdminPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
        homeAdminPage = new HomeAdminPage();
    }

    @Test
    void isAllMenuItemsOpen() {
        getWebDriver().get("http://localhost:8080/litecart/admin");
        loginPage.setUserName("admin");
        loginPage.setPassword("admin");
        loginPage.clickLoginBtn();
        assertTrue(homeAdminPage.isMenuItemsDisplayed(), "not all menu items displayed");
        int itemsCount = homeAdminPage.getMenuItemsCount();
        for (int i = 0; i < itemsCount; i++) {
            homeAdminPage.selectParentItem(i);
            assertTrue(homeAdminPage.isHeaderDisplayed(), "No header");
            int subSectionsCount = homeAdminPage.getSubSectionsCount();
            if (subSectionsCount > 0) {
                for (int j = 0; j < subSectionsCount; j++) {
                    homeAdminPage.selectSubsection(j);
                    assertTrue(homeAdminPage.isHeaderDisplayed(), "No header");
                }
            }
        }

    }
}
