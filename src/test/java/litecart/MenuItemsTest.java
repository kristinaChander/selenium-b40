package litecart;

import litecart.pages.HomeAdminPage;
import litecart.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuItemsTest extends BaseTest {
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private HomeAdminPage homeAdminPage;

    @Test
    void isAllMenuItemsOpen() {
        driver.get("http://localhost:8080/litecart/admin");
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


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
