package litecart.pages;

import litecart.dto.Color;
import org.openqa.selenium.WebElement;

import static litecart.utils.AttributeUtils.*;

public abstract class BaseGoodsPage extends BasePage {
    protected abstract WebElement getGoodName();

    protected abstract WebElement getStandardPrice();

    protected abstract WebElement getPromoPrice();

    public String getGoodsName() {
        return getTextContent(getGoodName());
    }

    public int getGoodStandardPrice() {
        return getPrice(getStandardPrice());
    }

    public int getGoodPromoPrice() {
        return getPrice(getPromoPrice());
    }

    public boolean isStandardPriceGrey() {
        return isPriceGrey(getStandardPrice());
    }

    public boolean isStandardPriceStrikeThrough() {
        return isStrikeThrough(getStandardPrice());
    }

    public boolean isPromoPriceRed() {
        Color promoPriceColor = getColor(getPromoPrice());
        return promoPriceColor.green() == 0 && promoPriceColor.blue() == 0;
    }

    public boolean isPromoPriceBold() {
        return getPromoPrice().getCssValue("font-weight").equals("700") ||
                getPromoPrice().getCssValue("font-weight").equals("900");
    }

    public boolean isPromoPriceBiggerThanStandard() {
        return getFontSize(getPromoPrice()) > getFontSize(getStandardPrice());
    }
}
