package litecart;

import litecart.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static litecart.config.WebDriverContext.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CountriesAndZonesTest extends BaseTest {

    private CountriesPage countriesPage;
    private LoginPage loginPage;
    private SelectedCountryPage selectedCountryPage;
    private GeoZonesPage geoZonesPage;
    private EditGeoZonesPage editGeoZonesPage;

    @BeforeEach
    void init() {
        countriesPage = new CountriesPage();
        loginPage = new LoginPage();
        selectedCountryPage = new SelectedCountryPage();
        geoZonesPage = new GeoZonesPage();
        editGeoZonesPage = new EditGeoZonesPage();
        getWebDriver().get("http://localhost:8080/litecart/admin");
        loginPage.setUserName("admin");
        loginPage.setPassword("admin");
        loginPage.clickLoginBtn();
    }

    @Test
    void isCountriesAlphabeticOrderKeptTest() {
        getWebDriver().get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        List<String> countriesFromPage = countriesPage.getCountriesList();
        List<String> sortedCountries = getSortedNames(countriesFromPage);
        assertEquals(countriesFromPage, sortedCountries, "Countries are not listed in alphabetical order");

        for (int i = 0; i < countriesFromPage.size(); i++) {
            if (countriesPage.getZoneNumber(i) != 0) {
                countriesPage.selectCountry(i);
                List<String> zonesFromPage = selectedCountryPage.getZoneNames();
                List<String> sortedZones = getSortedNames(zonesFromPage);
                assertEquals(zonesFromPage, sortedZones, "Zones are  not in alphabetic order");
                getWebDriver().navigate().back();
            }
        }
    }


    @Test
    void isZonesAlphabeticOrderKeptTest() {
        getWebDriver().get("http://localhost:8080/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> countriesWithZones = geoZonesPage.getCountries();
        for (int i = 0; i < countriesWithZones.size(); i++) {
            geoZonesPage.selectCountry(i);
            List<String> geoZoneNames = editGeoZonesPage.getZoneNames();
            List<String> sortedZones = getSortedNames(geoZoneNames);
            assertEquals(geoZoneNames, sortedZones, "Zones Are not in alphabetical order");
            getWebDriver().navigate().back();
        }
    }

    private List<String> getSortedNames(List<String> names) {
        List<String> countriesCopy = new ArrayList<>(names);
        countriesCopy.sort(String::compareToIgnoreCase);
        return countriesCopy;
    }
}
