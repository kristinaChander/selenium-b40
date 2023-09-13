package litecart;

import litecart.pages.RubberDucksPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LabelsTest extends BaseTest {
    @Autowired
    private RubberDucksPage ducksPage;

    @Test
    void isEachDuckHasLabelTest() {
        driver.get("http://localhost:8080/litecart");
        assertTrue(ducksPage.checkEachDuckHasOneLabel(), "not every duck has one label");
    }
}
