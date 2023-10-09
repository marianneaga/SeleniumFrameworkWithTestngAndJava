package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;

public class ProductPage extends BasePage {
    @FindBy(css = ".product_title.entry-title")
    private WebElement productName;
    @FindBy(css = "button[value='1215']")
    private WebElement addToCartButton;
    @FindBy(css = "div[role='alert'] a[class='button wc-forward']")
    private WebElement viewCardLink;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage loadProduct(String productNameSeparatedByDash){
        load("/product/" + productNameSeparatedByDash + "/");
        return this;
    }

    public String getTitle(){
        return getVisibilityOfElement(productName).getText();
    }
    public ProductPage addToCart(){
        getElementToBeClickable(addToCartButton).click();
        return this;
    }
    public CartPage clickViewCart() {
        getElementToBeClickable(viewCardLink).click();
        return new CartPage(driver);
    }

}
