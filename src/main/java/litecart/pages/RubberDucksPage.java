package litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RubberDucksPage extends BasePage {
    @FindBy(css = ".product")
    private List<WebElement> duckCards;

    public boolean checkEachDuckHasOneLabel() {
        return duckCards.stream()
                .allMatch(this::validateLabelCountIsOne);
    }

    private boolean validateLabelCountIsOne(WebElement duckCard) {
        return duckCard.findElements(new By.ByCssSelector(".sticker")).size() == 1;
    }

}
