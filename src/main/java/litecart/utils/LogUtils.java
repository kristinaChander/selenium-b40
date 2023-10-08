package litecart.utils;

import litecart.config.WebDriverContext;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

@UtilityClass
public class LogUtils {

    public static List<LogEntry> getBrowserConsoleLogs() {
        return WebDriverContext.getWebDriver().manage().logs().get("browser").getAll();
    }
}
