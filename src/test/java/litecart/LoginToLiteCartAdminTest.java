package litecart;

import dev.failsafe.internal.util.Assert;
import litecart.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static litecart.config.WebDriverContext.getWebDriver;

class LoginToLiteCartAdminTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    void loginToAdminTest() {
        getWebDriver().get("http://localhost:8080/litecart/admin");
        loginPage.setUserName("admin");
        loginPage.setPassword("admin");
        loginPage.clickLoginBtn();

        Assert.isTrue(getWebDriver().getCurrentUrl().contains("admin"), "Wrong url");
    }
}
