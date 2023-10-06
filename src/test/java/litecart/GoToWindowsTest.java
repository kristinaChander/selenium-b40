package litecart;

import litecart.pages.CountriesPage;
import litecart.pages.EditCountryPage;
import litecart.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static litecart.config.WebDriverContext.getWebDriver;

public class GoToWindowsTest extends BaseTest {

    private LoginPage loginPage;
    private CountriesPage countriesPage;
    private EditCountryPage editCountryPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        countriesPage = new CountriesPage();
        editCountryPage = new EditCountryPage();
    }

    @Test
    public void checkLinksCanBeFollowedTest() {
        getWebDriver().get("http://localhost:8080/litecart/admin");
        loginPage.setUserName("admin");
        loginPage.setPassword("admin");
        loginPage.clickLoginBtn();
        getWebDriver().get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        int countriesNumber = countriesPage.getCountriesList().size();
        countriesPage.clickEditCountry(new Random().nextInt(countriesNumber));
        List<WebElement> externalLink = editCountryPage.getInfoLinks();
        for (int i = 0; i < externalLink.size(); i++) {
            String mainWindow = getWebDriver().getWindowHandle();
            externalLink.get(i).click();
            new WebDriverWait(getWebDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));
            String newWindow = getNewWindowId(mainWindow);
            getWebDriver().switchTo().window(newWindow);
            getWebDriver().close();
            getWebDriver().switchTo().window(mainWindow);
        }
    }

    private String getNewWindowId(String mainWindow) {
        Set<String> allWindows = getWebDriver().getWindowHandles();
        allWindows.remove(mainWindow);
        return allWindows.iterator().next();
    }
}
