package litecart.config;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class WebDriverContext {
    private static WebDriverFactory webDriverFactory;
    private static final ThreadLocal<WebDriver> INSTANCE = new ThreadLocal<>();

    public WebDriverContext(WebDriverFactory webDriverFactory) {
        WebDriverContext.webDriverFactory = webDriverFactory;
        INSTANCE.set(webDriverFactory.createDriver());
    }

    public static WebDriver getWebDriver() {
        if (INSTANCE.get() == null) {
            WebDriver webDriver = webDriverFactory.createDriver();
            INSTANCE.set(webDriver);
            return webDriver;
        }
        return INSTANCE.get();
    }

    public static void quitWebDriver() {
        INSTANCE.get().quit();
        INSTANCE.set(null);
    }
}
