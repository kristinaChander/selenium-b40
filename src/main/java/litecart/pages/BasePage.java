package litecart.pages;

import litecart.config.WebDriverContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage() {
        WebDriver driver = WebDriverContext.getWebDriver();
        PageFactory.initElements(driver, this);
    }
}
