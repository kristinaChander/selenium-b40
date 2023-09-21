package litecart;

import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.SpringBootTest;

import static litecart.config.WebDriverContext.quitWebDriver;

@SpringBootTest
public class BaseTest {

    @AfterEach
    public void tearDown() {
        quitWebDriver();
    }
}
