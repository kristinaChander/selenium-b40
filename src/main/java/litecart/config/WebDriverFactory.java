package litecart.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class WebDriverFactory {

    private static final String BROWSER_TYPE_FIREFOX_NIGHTLY = "firefox-nightly";

    @Value("${browser-type:chrome}")
    private String browserType;

    @Value("${firefox-binary-path:}")
    private String firefoxBinaryPath;

    public WebDriver createDriver() {
        WebDriverManager webDriverManager = getWebDriverManager(browserType);
        if (!ObjectUtils.isEmpty(firefoxBinaryPath)) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBinary(firefoxBinaryPath);
            webDriverManager.capabilities(firefoxOptions);
        }
        return webDriverManager.create();
    }

    private WebDriverManager getWebDriverManager(String browserType) {
        if (BROWSER_TYPE_FIREFOX_NIGHTLY.equals(browserType)) {
            return WebDriverManager.getInstance(DriverManagerType.FIREFOX);
        }
        return WebDriverManager.getInstance(browserType);
    }
}
