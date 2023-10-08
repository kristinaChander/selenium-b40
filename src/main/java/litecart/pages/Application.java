package litecart.pages;

import java.util.function.BiConsumer;

import static litecart.config.WebDriverContext.getWebDriver;

public class Application {
    private static final String NO_ITEMS_MESSAGE = "There are no items in your cart.";
    private final RubberDucksMainPage ducksPage;
    private final DetailedGoodsInfoPage goodsInfoPage;

    public Application() {
        ducksPage = new RubberDucksMainPage();
        goodsInfoPage = new DetailedGoodsInfoPage();
    }

    public void goToLitecart() {
        getWebDriver().get("http://localhost:8080/litecart");
    }

    private void addDuck(int itemIndex,
                         BiConsumer<Integer, Integer> beforeAddDuckValidator,
                         BiConsumer<Integer, Integer> afterAddDuckValidator) {
        ducksPage.selectItemFromGoodsList();
        int itemCountBefore = goodsInfoPage.getItemCount();
        beforeAddDuckValidator.accept(itemIndex, itemCountBefore);
        goodsInfoPage.addToCart();
        goodsInfoPage.waitItemCount(itemCountBefore + 1);
        int itemCountAfter = goodsInfoPage.getItemCount();
        afterAddDuckValidator.accept(itemCountAfter, itemCountBefore);
    }

    public void addDucks(int ducksToAdd,
                         BiConsumer<Integer, Integer> beforeAddDuckValidator,
                         BiConsumer<Integer, Integer> afterAddDuckValidator) {
        for (int itemIndex = 0; itemIndex < ducksToAdd; itemIndex++) {
            addDuck(itemIndex, beforeAddDuckValidator, afterAddDuckValidator);
            if (itemIndex != ducksToAdd - 1) {
                getWebDriver().navigate().back();
            }
        }
    }

    public void openCart() {
        goodsInfoPage.openCart();
    }

    public void removeDucks(int ducksToAdd) {
        for (int itemIndex = ducksToAdd; itemIndex > 0; itemIndex--) {
            double currentItemSum = goodsInfoPage.getItemSum();
            int currentItemAmount = goodsInfoPage.getItemAmount();
            double itemPrice = goodsInfoPage.removeOneItemFromCart();
            if (itemIndex > 1) {
                goodsInfoPage.waitForPriceToDecrease(currentItemSum, itemPrice);
                goodsInfoPage.waitForItemAmount(currentItemAmount - 1);
            }
        }
    }

    public boolean isAllDucksRemoved() {
        goodsInfoPage.waitInfoMessageToDisplay();
        return NO_ITEMS_MESSAGE.equals(goodsInfoPage.getMessage());
    }
}
