package litecart;

import litecart.pages.DetailedGoodsInfoPage;
import litecart.pages.GoodsPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static litecart.config.WebDriverContext.getWebDriver;

public class GoodsPropertiesTest extends BaseTest {
    private GoodsPage goodsPage;
    private DetailedGoodsInfoPage goodsInfoPage;

    @BeforeEach
    void setUp() {
        goodsPage = new GoodsPage();
        goodsInfoPage = new DetailedGoodsInfoPage();
    }

    @Test
    void compareGoodsPropertyTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        getWebDriver().get("http://localhost:8080/litecart");
        String goodNameMain = goodsPage.getGoodsName();
        int standardPriceMain = goodsPage.getGoodStandardPrice();
        int promoPriceMain = goodsPage.getGoodPromoPrice();
        softAssertions.assertThat(goodsPage.isStandardPriceGrey()).isTrue();
        softAssertions.assertThat(goodsPage.isStandardPriceStrikeThrough()).isTrue();
        softAssertions.assertThat(goodsPage.isPromoPriceRed()).isTrue();
        softAssertions.assertThat(goodsPage.isPromoPriceBold()).isTrue();
        softAssertions.assertThat(goodsPage.isPromoPriceBiggerThanStandard()).isTrue();
        goodsPage.selectFirstItemFromCampaign();
        String detailedGoodName = goodsInfoPage.getGoodsName();
        int detailedStandardPrice = goodsInfoPage.getGoodStandardPrice();
        int detailedPromoPrice = goodsInfoPage.getGoodPromoPrice();
        softAssertions.assertThat(goodsInfoPage.isStandardPriceGrey()).isTrue();
        softAssertions.assertThat(goodsInfoPage.isStandardPriceStrikeThrough()).isTrue();
        softAssertions.assertThat(goodsInfoPage.isPromoPriceRed()).isTrue();
        softAssertions.assertThat(goodsInfoPage.isPromoPriceBold()).isTrue();
        softAssertions.assertThat(goodNameMain).isEqualTo(detailedGoodName);
        softAssertions.assertThat(standardPriceMain).isEqualTo(detailedStandardPrice);
        softAssertions.assertThat(promoPriceMain).isEqualTo(detailedPromoPrice);
        softAssertions.assertThat(goodsInfoPage.isPromoPriceBiggerThanStandard()).isTrue();
        softAssertions.assertAll();
    }
}
