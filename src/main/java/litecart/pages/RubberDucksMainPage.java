package litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RubberDucksMainPage extends BasePage {
    @FindBy(css = ".product")
    private List<WebElement> duckCards;
    @FindBy(css = "[name='email']")
    private WebElement emailField;
    @FindBy(css = "[name='password']")
    private WebElement passwordField;
    @FindBy(css = "[type='submit']")
    private WebElement loginBtn;
    @FindBy(xpath = "//a[text()='New customers click here']")
    private WebElement newCustomerLink;
    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutBtn;
    @FindBy(css = "#box-most-popular .product:nth-child(1)")
    private WebElement firstItem;

    public boolean checkEachDuckHasOneLabel() {
        return duckCards.stream()
                .allMatch(this::validateLabelCountIsOne);
    }

    private boolean validateLabelCountIsOne(WebElement duckCard) {
        return duckCard.findElements(new By.ByCssSelector(".sticker")).size() == 1;
    }

    public void login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginBtn.click();
    }

    public void startCreatingNewCustomer() {
        newCustomerLink.click();
    }

    public void logout() {
        logoutBtn.click();
    }

    public void selectItemFromGoodsList() {
        firstItem.click();
    }
}
