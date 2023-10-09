package org.selenium.pom.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;


public class CartPage extends BasePage {

    @FindBy(css = "td[class='product-name'] a")
    private WebElement productName;
    @FindBy(css = ".checkout-button")
//  @CacheLookup is used to avoid a new API call for the element when we run the test again, it will be stored in the cache and the test will run faster
    @CacheLookup
    private WebElement checkoutButton;
    @FindBy(css = "tr[class='cart-subtotal'] bdi:nth-child(1)")
    private WebElement subtotalPrice;
    @FindBy(css = "tr[class='woocommerce-shipping-totals shipping'] bdi:nth-child(1)")
    private WebElement shippingPrice;
    @FindBy(css = "td[data-title='CA State Tax'] span[class='woocommerce-Price-amount amount']")
    private WebElement stateTax;
    @FindBy(css = "tr[class='order-total'] bdi:nth-child(1)")
    private WebElement cartTotalPrice;
    @FindBy(css = "#coupon_code")
    private WebElement couponCodeBox;
    @FindBy(css = "button[value='Apply coupon']")
    private WebElement applyCouponButton;
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public CheckoutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        return new CheckoutPage(driver);
    }
    public CartPage applyCouponCode(String couponCode){
        getVisibilityOfElement(couponCodeBox).sendKeys(couponCode);
        getElementToBeClickable(applyCouponButton).click();
        waitForOverlaysToDisappear();
        return this;
    }
    public String getShippingPrice(){
        waitForOverlaysToDisappear();
        return getVisibilityOfElement(shippingPrice).getText().replaceAll("\\$", "");
    }

    public String getSubtotalPrice(){
        waitForOverlaysToDisappear();
        return getVisibilityOfElement(subtotalPrice).getText().replaceAll("\\$", "");
    }
    public String getStateTax(){
        waitForOverlaysToDisappear();
        return getVisibilityOfElement(stateTax).getText().replaceAll("\\$", "");
    }
    public String getCartTotalPrice(){
        waitForOverlaysToDisappear();
        return getVisibilityOfElement(cartTotalPrice).getText().replaceAll("\\$", "");
    }

}
