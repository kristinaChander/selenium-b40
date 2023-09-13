package litecart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomeAdminPage extends BasePage {
    @FindBy(css = "li#app-")
    private List<WebElement> menuItemsList;
    @FindBy(css = "#content h1")
    private WebElement pageHeader;
    @FindBy(css = "#app- li")
    private List<WebElement> subSectionList;


    public int getMenuItemsCount() {
        return menuItemsList.size();
    }

    public void selectParentItem(int itemIndex) {
        menuItemsList.get(itemIndex).click();
    }

    public boolean isHeaderDisplayed() {
        return pageHeader.isDisplayed();
    }

    public int getSubSectionsCount() {
        return subSectionList.size();
    }

    public void selectSubsection(int itemIndex) {
        subSectionList.get(itemIndex).click();
    }

    public boolean isMenuItemsDisplayed() {
        return menuItemsList.stream().allMatch(s -> s.isDisplayed());
    }
}
