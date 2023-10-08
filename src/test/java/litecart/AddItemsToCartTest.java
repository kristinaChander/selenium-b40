package litecart;

import litecart.pages.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddItemsToCartTest extends BaseTest {

    private static final int DUCKS_TO_ADD = 3;
    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @Test
    void addItemsToCart() {
        application.goToLitecart();
        application.addDucks(DUCKS_TO_ADD, this::beforeAddDuckCheck, this::afterAddDuckCheck);
        application.openCart();
        application.removeDucks(DUCKS_TO_ADD);
        assertTrue(application.isAllDucksRemoved());
    }

    private void beforeAddDuckCheck(Integer itemIndex, Integer itemCountBefore) {
        assertEquals(itemIndex, itemCountBefore);
    }

    private void afterAddDuckCheck(Integer itemCountAfter, Integer itemCountBefore) {
        assertEquals(itemCountAfter, itemCountBefore + 1);
    }
}
