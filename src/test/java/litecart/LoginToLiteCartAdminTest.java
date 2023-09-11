package litecart;

import dev.failsafe.internal.util.Assert;
import litecart.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginToLiteCartAdminTest {
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private WebDriver driver;

    @Test
    public void loginToAdminTest() {
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
