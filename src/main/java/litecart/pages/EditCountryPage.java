package litecart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditCountryPage extends BasePage {
    @FindBy(css = ".fa-external-link")
    private List<WebElement> externalLinksList;

    public List<WebElement> getInfoLinks() {
        return externalLinksList;
    }
}
