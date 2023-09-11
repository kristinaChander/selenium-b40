package litecart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@name='username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    public void setUserName(String userName) {
        userNameField.sendKeys(userName);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }
}
