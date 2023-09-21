package litecart.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class GoodsPage extends BaseGoodsPage {
    @FindBy(css = "#box-campaigns li:nth-child(1).product .name")
    private WebElement goodName;
    @FindBy(css = "#box-campaigns li:nth-child(1).product .regular-price")
    private WebElement standardPrice;
    @FindBy(css = "#box-campaigns li:nth-child(1).product .campaign-price")
    private WebElement promoPrice;

    @FindBy(css = "#box-campaigns li:nth-child(1).product")
    private WebElement firstCampaignElement;

    public void selectFirstItemFromCampaign() {
        firstCampaignElement.click();
    }
}
