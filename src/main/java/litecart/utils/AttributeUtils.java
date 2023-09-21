package litecart.utils;

import litecart.dto.Color;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebElement;

@UtilityClass
public class AttributeUtils {

    public static String getTextContent(WebElement webElement) {
        return webElement.getAttribute("textContent");
    }

    public static int getPrice(WebElement webElement) {
        String textContent = getTextContent(webElement);
        return Integer.parseInt(textContent.substring(1));
    }

    public static Color getColor(WebElement webElement) {
        String priceColor = webElement.getCssValue("color");
        String[] color = priceColor.substring(priceColor.indexOf("(") + 1, priceColor.length() - 1).split(", ");
        return new Color(
                Integer.parseInt(color[0]),
                Integer.parseInt(color[1]),
                Integer.parseInt(color[2])
        );
    }


    public static int getFontSize(WebElement webElement) {
        return Integer.parseInt(webElement.getCssValue("font-size").substring(0, 2));
    }

    public static boolean isPriceGrey(WebElement webElement) {
        Color standardPriceColor = getColor(webElement);
        return (standardPriceColor.red() == standardPriceColor.green()
                && standardPriceColor.green() == standardPriceColor.blue());
    }

    public static boolean isStrikeThrough(WebElement webElement) {
        return webElement.getCssValue("textDecoration").contains("line-through") ||
                webElement.getCssValue("text-decoration").contains("line-through");
    }
}
