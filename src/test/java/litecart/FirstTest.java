package litecart;

import org.junit.jupiter.api.Test;

import static dev.failsafe.internal.util.Assert.isTrue;
import static litecart.config.WebDriverContext.getWebDriver;

public class FirstTest extends BaseTest {
    @Test
    public void openBrowserTest() {
        getWebDriver().get("https://www.google.com/");
        isTrue(getWebDriver().getCurrentUrl().contains("google"), "Wrong url");
    }
}
