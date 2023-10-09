package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.ProductPage;

public class ProductThumbnail extends BasePage {
    @FindBy(css = "a[title='View cart']")
    private WebElement viewCardLink;

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getToCartButtonElement(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    //Dynamic UI Element handling example (Page Factory pattern doesn't support dynamic elements):
    public ProductThumbnail clickAddToCardButton(String productName) {
        By addToCardButton = getToCartButtonElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCardButton)).click();
        return this;
    }

    private By getProductElement(String productName){
        return By.xpath("//h2[normalize-space()='" + productName + "']");
    }

    public ProductPage getStoreProduct(String productName){
        By productElement = getProductElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(productElement)).click();
        return new ProductPage(driver);
    }
    public CartPage clickViewCart() {
        getElementToBeClickable(viewCardLink).click();
        return new CartPage(driver);
    }
}
