package litecart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class EditGeoZonesPage extends BasePage {
    @FindBy(css = "#table-zones td:nth-child(3) [selected]")
    private List<WebElement> geoZones;

    public List<String> getZoneNames() {
        return geoZones.stream()
                .map(zone -> zone.getAttribute("textContent"))
                .collect(Collectors.toList());
    }
}
