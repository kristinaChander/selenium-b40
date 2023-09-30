package litecart;

import litecart.config.WebDriverContext;
import litecart.pages.DetailedGoodsInfoPage;
import litecart.pages.RubberDucksMainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static litecart.config.WebDriverContext.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddItemsToCartTest extends BaseTest {

    private static final int DUCKS_TO_ADD = 3;

    private RubberDucksMainPage ducksPage;
    private DetailedGoodsInfoPage goodsInfoPage;

    @BeforeEach
    void setUp() {
        ducksPage = new RubberDucksMainPage();
        goodsInfoPage = new DetailedGoodsInfoPage();
    }

    @Test
    void addItemsToCart() {
        getWebDriver().get("http://localhost:8080/litecart");
        for (int itemIndex = 0; itemIndex < DUCKS_TO_ADD; itemIndex++) {
            addDuck(itemIndex);
            if (itemIndex != DUCKS_TO_ADD - 1) {
                WebDriverContext.getWebDriver().navigate().back();
            }
        }

        goodsInfoPage.openCart();

        for (int itemIndex = DUCKS_TO_ADD; itemIndex > 0; itemIndex--) {
            double currentItemSum = goodsInfoPage.getItemSum();
            int currentItemAmount = goodsInfoPage.getItemAmount();
            double itemPrice = goodsInfoPage.removeOneItemFromCart();
            if (itemIndex > 1) {
                goodsInfoPage.waitForPriceToDecrease(currentItemSum, itemPrice);
                goodsInfoPage.waitForItemAmount(currentItemAmount - 1);
            }
        }
        goodsInfoPage.waitInfoMessageToDisplay();
        assertEquals(goodsInfoPage.getMessage(), "There are no items in your cart.");
    }

    private void addDuck(int itemIndex) {
        ducksPage.selectItemFromGoodsList();
        int itemCountBefore = goodsInfoPage.getItemCount();
        assertEquals(itemIndex, itemCountBefore);
        goodsInfoPage.addToCart();
        goodsInfoPage.waitItemCount(itemCountBefore + 1);
        int itemCountAfter = goodsInfoPage.getItemCount();
        assertEquals(itemCountAfter, itemCountBefore + 1);
    }

}
