import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestClass {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void openBrowserTest() {
        driver.get("https://www.google.com/");
        Assert.isTrue(driver.getCurrentUrl().contains("google"), "Wrong url");
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

}
