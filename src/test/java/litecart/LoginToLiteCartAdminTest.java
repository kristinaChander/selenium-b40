package litecart;

import dev.failsafe.internal.util.Assert;
import litecart.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static litecart.config.WebDriverContext.getWebDriver;

class LoginToLiteCartAdminTest extends BaseTest {
    @Autowired
    private LoginPage loginPage;

    @Test
    void loginToAdminTest() {
        getWebDriver().get("http://localhost:8080/litecart/admin");
        loginPage.setUserName("admin");
        loginPage.setPassword("admin");
        loginPage.clickLoginBtn();

        Assert.isTrue(getWebDriver().getCurrentUrl().contains("admin"), "Wrong url");
    }
}
