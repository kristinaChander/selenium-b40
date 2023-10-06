package litecart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CountriesPage extends BasePage {
    @FindBy(css = "[name=countries_form] tr td:nth-child(5) a")
    private List<WebElement> countries;

    @FindBy(css = "[name=countries_form] tr td:nth-child(6)")
    private List<WebElement> zones;
    @FindBy(css = "[title='Edit']")
    private List<WebElement> editCountry;


    public List<String> getCountriesList() {
        return countries.stream()
                .map(country -> country.getAttribute("textContent"))
                .collect(Collectors.toList());
    }

    public int getZoneNumber(int index) {
        String zoneValue = zones.get(index).getAttribute("textContent");
        return Integer.parseInt(zoneValue);
    }

    public void selectCountry(int index) {
        countries.get(index).click();
    }

    public void clickEditCountry(int editIndex) {
        editCountry.get(editIndex).click();
    }
}
