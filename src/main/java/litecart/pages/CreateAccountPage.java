package litecart.pages;

import com.github.javafaker.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CreateAccountPage extends BasePage {
    @FindBy(css = "input[name='firstname']")
    private WebElement firstNameField;
    @FindBy(css = "input[name='lastname']")
    private WebElement lastNameField;
    @FindBy(css = "input[name='address1']")
    private WebElement addressField;
    @FindBy(css = "input[name='postcode']")
    private WebElement postCodeField;
    @FindBy(css = "select[name='country_code']")
    private WebElement countryField;
    @FindBy(css = "input[name='city']")
    private WebElement cityField;
    @FindBy(css = "select[name='zone_code']")
    private WebElement zoneField;
    @FindBy(css = "input[name='email']")
    private WebElement emailField;
    @FindBy(css = "input[name='phone']")
    private WebElement phoneField;
    @FindBy(css = "input[name='password']")
    private WebElement passwordField;
    @FindBy(css = "input[name='confirmed_password']")
    private WebElement confirmPasswordField;
    @FindBy(css = "button[type='submit']")
    private WebElement createAccountButton;

    public void setFirstName(Name name) {
        firstNameField.sendKeys(name.firstName());
    }

    public void setLastName(FunnyName name) {
        lastNameField.sendKeys(name.name());
    }

    public void setAddress(Address address) {
        addressField.sendKeys(address.fullAddress());
    }

    public void setPostCode(Address address) {
        postCodeField.sendKeys(address.zipCode().substring(0,5));
    }

    public void selectUnitedStates() {
        Select countryList = new Select(countryField);
        countryList.selectByVisibleText("United States");
    }

    public void setCity(Address address) {
        cityField.sendKeys(address.city());
    }

    public void selectRandomZone() {
        Select zones = new Select(zoneField);
        int size = zones.getOptions().size();
        zones.selectByIndex(new Random().nextInt(size));
    }

    public String setEmail(Internet internet) {
        String email = internet.emailAddress();
        emailField.sendKeys(email);
        return email;
    }

    public void setPhone(PhoneNumber phone) {
        String beginning = phoneField.getAttribute("placeholder");
        phoneField.sendKeys(beginning + phone.subscriberNumber());
    }

    public String setPassword(Internet internet) {
        String passwordString = internet.password(true);
        passwordField.sendKeys(passwordString);
        return passwordString;
    }

    public void confirmPassword(String password) {
        confirmPasswordField.sendKeys(password);
    }

    public void clickCreateAccount() {
        createAccountButton.click();
    }
}
