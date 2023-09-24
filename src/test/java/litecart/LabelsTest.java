package litecart;

import litecart.pages.RubberDucksMainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static litecart.config.WebDriverContext.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LabelsTest extends BaseTest {

    private RubberDucksMainPage ducksPage;

    @BeforeEach
    void setUp() {
        ducksPage = new RubberDucksMainPage();
    }

    @Test
    void isEachDuckHasLabelTest() {
        getWebDriver().get("http://localhost:8080/litecart");
        assertTrue(ducksPage.checkEachDuckHasOneLabel(), "not every duck has one label");
    }
}
