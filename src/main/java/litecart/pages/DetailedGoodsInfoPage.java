package litecart.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class DetailedGoodsInfoPage extends BaseGoodsPage {
    @FindBy(css = "#box-product h1")
    private WebElement goodName;
    @FindBy(css = "#box-product .regular-price")
    private WebElement standardPrice;
    @FindBy(css = "#box-product .campaign-price")
    private WebElement promoPrice;
}
