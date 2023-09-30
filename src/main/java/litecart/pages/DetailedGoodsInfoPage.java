package litecart.pages;

import litecart.config.WebDriverContext;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class DetailedGoodsInfoPage extends BaseGoodsPage {
    @FindBy(css = "#box-product h1")
    private WebElement goodName;
    @FindBy(css = "#box-product .regular-price")
    private WebElement standardPrice;
    @FindBy(css = "#box-product .campaign-price")
    private WebElement promoPrice;
    @FindBy(css = "span.quantity")
    private WebElement itemCount;
    @FindBy(xpath = "//a[text()[contains(.,'Checkout')]]")
    private WebElement checkout;
    @FindBy(css = "[name=remove_cart_item]")
    private List<WebElement> removeItem;
    @FindBy(css = "tr button[name='add_cart_product']")
    private WebElement addToCart;
    @FindBy(xpath = "//*[text()='Order Summary']/..//tr/td[@style='text-align: center;']")
    private List<WebElement> itemCountInTable;
    @FindBy(css = "p>em")
    private WebElement infoMessage;
    @FindBy(css = "input[type='number']")
    private WebElement itemNumber;
    @FindBy(xpath = "//*[text()='Update']")
    private WebElement updateBtn;
    @FindBy(css = "#box-checkout-cart .item p:nth-child(2)")
    private WebElement itemPrice;
    @FindBy(xpath = "//*[text()='Subtotal:']/../../td[2]")
    private WebElement totalSum;

    public void waitItemCount(int itemCount) {
        getWebDriverWait()
                .until(ExpectedConditions.textToBePresentInElement(this.itemCount, String.valueOf(itemCount)));
    }

    public void addToCart() {
        addToCart.click();
    }

    public int getItemCount() {
        return Integer.parseInt(itemCount.getText());
    }

    public void openCart() {
        checkout.click();
    }

    public double removeOneItemFromCart() {
        getWebDriverWait()
                .until(ExpectedConditions.visibilityOf(itemNumber));
        double price = Double.parseDouble(itemPrice.getAttribute("textContent").substring(1));
        int value = Integer.parseInt(itemNumber.getAttribute("value"));
        if (value > 1) {
            getWebDriverWait()
                    .until(ExpectedConditions.elementToBeClickable(itemNumber));
            itemNumber.clear();
            itemNumber.sendKeys(value - 1 + "");
            updateBtn.click();
        } else {
            if (removeItem.get(0).isDisplayed()) {
                removeItem.get(0).click();
            }
        }
        return price;
    }

    public double getItemSum() {
        return Double.parseDouble(totalSum.getText().substring(1));
    }

    public String getMessage() {
        return infoMessage.getText();
    }

    public void waitInfoMessageToDisplay() {
        getWebDriverWait()
                .until(ExpectedConditions.visibilityOf(infoMessage));
    }

    public void waitForPriceToDecrease(double currentItemSum, double itemPrice) {
        getWebDriverWait().until(ExpectedConditions
                .textToBePresentInElement(totalSum, currentItemSum - itemPrice + ""));
    }

    public int getItemAmount() {
        getWebDriverWait()
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[text()='Order Summary']/..//tr/td[@style='text-align: center;']"), 0));
        return getItemAmountNoWait();
    }

    public void waitForItemAmount(int amount) {
        getWebDriverWait()
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[text()='Order Summary']/..//tr/td[@style='text-align: center;']"), 0)
                        .andThen(list -> itemSumIs(amount)));
    }

    private static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(WebDriverContext.getWebDriver(), Duration.ofSeconds(10));
    }

    private int getItemAmountNoWait() {
        return itemCountInTable.stream()
                .map(item -> Integer.parseInt(item.getText()))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private ExpectedCondition<Integer> itemSumIs(int sum) {
        return input -> {
            if (getItemAmountNoWait() == sum) {
                return sum;
            }
            return null;
        };
    }
}
