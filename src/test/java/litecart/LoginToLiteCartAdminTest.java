package litecart;

import dev.failsafe.internal.util.Assert;
import litecart.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LoginToLiteCartAdminTest extends BaseTest {
    @Autowired
    private LoginPage loginPage;

    @Test
    void loginToAdminTest() {
        driver.get("http://localhost:8080/litecart/admin");
        loginPage.setUserName("admin");
        loginPage.setPassword("admin");
        loginPage.clickLoginBtn();

        Assert.isTrue(driver.getCurrentUrl().contains("admin"), "Wrong url");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
