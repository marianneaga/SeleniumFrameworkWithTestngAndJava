package org.selenium.pom.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class MyHeader extends BasePage {

    @FindBy(css = "#menu-item-1227 > a")
    private WebElement storeMenuLink;

    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu(){
        getVisibilityOfElement(storeMenuLink).click();
        return new StorePage(driver);
    }
}
