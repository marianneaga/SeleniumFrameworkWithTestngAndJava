package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.EndPoint;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {

    @FindBy(id = "woocommerce-product-search-field-0")
    private WebElement searchField;
    @FindBy(css = "button[value='Search']")
    private WebElement searchButton;
    @FindBy(css = ".woocommerce-products-header__title.page-title")
    private WebElement title;
    @FindBy(css = ".woocommerce-info.woocommerce-no-products-found")
    private WebElement productNotFoundErrorMessage;
    private final ProductThumbnail productThumbnail;


    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    // Structural Page Object example:
//    public StorePage enterTextInSearchField(String text){
//        driver.findElement(searchField).sendKeys(text);
//        return this;
//    }
//
//    public StorePage clickSearchButton(){
//        driver.findElement(searchButton).click();
//        return this;
//    }
//    public Boolean isLoaded() {
//        wait.until(ExpectedConditions.urlContains("/store"));
//        return true;
//    }

    public StorePage search(String text) {
        getVisibilityOfElement(searchField).sendKeys(text);
        getElementToBeClickable(searchButton).click();
        return this;
    }

    public StorePage load() {
        load(EndPoint.STORE.url);
        return this;
    }

    public String getTitle() {
        return getVisibilityOfElement(title).getText();
    }

    public String getNotFoundProductErrorMessage() {
        return getVisibilityOfElement(productNotFoundErrorMessage).getText();
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }
}
