package litecart.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class GeoZonesPage extends BasePage {
    @FindBy(css = "[name=geo_zones_form] td:nth-child(3) a")
    private List<WebElement> countries;

    public void selectCountry(int index) {
        countries.get(index).click();
    }
}
