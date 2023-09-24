package litecart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SelectedCountryPage extends BasePage {
    @FindBy(css = "#table-zones tr td:nth-child(3)")
    private List<WebElement> zoneNames;

    public List<String> getZoneNames() {
        List<String> zones = new ArrayList<>();
        for (int i = 0; i < zoneNames.size(); i++) {
            String zoneValue = zoneNames.get(i).getAttribute("textContent");
            if (zoneValue != "") {
                zones.add(zoneValue);
            }
        }
        return zones;
    }
}
