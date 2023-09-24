package litecart;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import litecart.pages.CreateAccountPage;
import litecart.pages.RubberDucksMainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static litecart.config.WebDriverContext.getWebDriver;

public class LoginToLiteCartTest extends BaseTest {
    private RubberDucksMainPage ducksPage;
    private CreateAccountPage createAccountPage;

    @BeforeEach
    void setUp() {
        ducksPage = new RubberDucksMainPage();
        createAccountPage = new CreateAccountPage();
    }

    Faker faker = new Faker();

    @Test
    void registerNewUserTest() {
        getWebDriver().get("http://localhost:8080/litecart");
        ducksPage.startCreatingNewCustomer();
        createAccountPage.setFirstName(faker.name());
        createAccountPage.setLastName(faker.funnyName());
        Address address = faker.address();
        createAccountPage.setAddress(address);
        createAccountPage.setPostCode(address);
        createAccountPage.setCity(address);
        createAccountPage.selectUnitedStates();
        createAccountPage.selectRandomZone();
        String email = createAccountPage.setEmail(faker.internet());
        createAccountPage.setPhone(faker.phoneNumber());
        String password = createAccountPage.setPassword(faker.internet());
        createAccountPage.confirmPassword(password);
        createAccountPage.clickCreateAccount();
        ducksPage.logout();
        ducksPage.login(email, password);
        ducksPage.logout();
    }
}
