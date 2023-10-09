package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.EndPoint;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {

    @FindBy(className = "showlogin")
    private WebElement showLoginButton;
    @FindBy(id = "username")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "rememberme")
    private WebElement rememberMeCheckBox;
    @FindBy(name = "login")
    private WebElement loginButton;
    @FindBy(id = "billing_first_name")
    private WebElement firstName;
    @FindBy(id = "billing_last_name")
    private WebElement lastName;
    @FindBy(id = "billing_address_1")
    private WebElement addressLineOne;
    @FindBy(id = "billing_city")
    private WebElement city;
    @FindBy(id = "billing_postcode")
    private WebElement postalCode;
    @FindBy(id = "billing_email")
    private WebElement email;
    @FindBy(css = "#place_order")
    private WebElement placeOrderButton;
    @FindBy(css = ".woocommerce-notice")
    private WebElement successNote;
    @FindBy(id = "billing_country")
    private WebElement countryDropDown;
    @FindBy(id = "billing_state")
    private WebElement stateDropDown;
    @FindBy(id = "payment_method_bacs")
    private WebElement directBankTransferRadioButton;
    @FindBy(css = "#payment_method_cod")
    private WebElement cashOnDeliveryRadioButton;
    @FindBy(id = "select2-billing_country-container")
    private WebElement alternativeCountryDropDown;
    @FindBy(id = "select2-billing_state-container")
    private WebElement alternativeStateDropDown;
    @FindBy(css = "td[class='product-name']")
    private WebElement productName;
    @FindBy(xpath = "//div[@class='woocommerce-notices-wrapper']//li[1]")
    private WebElement loginFailErrorMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load(){
        load(EndPoint.CHECKOUT.url);
        return this;
    }

    public CheckoutPage clickShowLogin() {
        getElementToBeClickable(showLoginButton).click();
        return this;
    }


    public CheckoutPage login(User user) {
        getVisibilityOfElement(this.username).sendKeys(user.getUsername());
        getVisibilityOfElement(this.password).sendKeys(user.getPassword());
        getElementToBeClickable(this.rememberMeCheckBox).click();
        getElementToBeClickable(this.loginButton).click();
        wait.until(ExpectedConditions.invisibilityOf(loginButton));
        return this;
    }

    public CheckoutPage insertFirstName(String firstName) {
        WebElement e = getVisibilityOfElement(this.firstName);
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage insertLastName(String lastName) {
        WebElement e = getVisibilityOfElement(this.lastName);
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage insertAddressLineOne(String address) {
        WebElement e = getVisibilityOfElement(this.addressLineOne);
        e.clear();
        e.sendKeys(address);
        return this;
    }

    public CheckoutPage insertCity(String city) {
        WebElement e = getVisibilityOfElement(this.city);
        e.clear();
        e.sendKeys(city);
        return this;
    }

    public CheckoutPage insertPostalCode(String postalCode) {
        WebElement e = getVisibilityOfElement(this.postalCode);
        e.clear();
        e.sendKeys(postalCode);
        return this;
    }

    public CheckoutPage insertEmail(String email) {
        WebElement e = getVisibilityOfElement(this.email);
        e.clear();
        e.sendKeys(email);
        return this;
    }
    public CheckoutPage selectCountry(String countryName){
        //This code is working on Google Chrome browser instead of Firefox which throw an exception
        //This is an open bug that is currently unresolved(org.openqa.selenium.ElementNotIntractableException: Element <option> could not be scrolled into view)
        //Once the list of elements it's too high, the element might not be in the view.
//        Select select = new Select(getElementToBeClickable(countryDropDown));
//        select.selectByVisibleText(countryName);
        getElementToBeClickable(alternativeCountryDropDown).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }
    public CheckoutPage selectState(String state){
//        Same issue as we have with country drop-down
//        Select select = new Select(getElementToBeClickable(stateDropDown));
//        select.selectByVisibleText(state);
        getElementToBeClickable(alternativeStateDropDown).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + state + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage selectDirectBankTransfer(){
        WebElement e = getElementToBeClickable(directBankTransferRadioButton);
        if (!e.isSelected()){
            e.click();
        }
        return this;
    }

    public CheckoutPage selectCashOnDelivery(){
        getElementToBeClickable(cashOnDeliveryRadioButton).click();
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
            return insertFirstName(billingAddress.getFirstName())
                    .insertLastName(billingAddress.getLastName())
                    .selectCountry(billingAddress.getCountry())
                    .insertAddressLineOne(billingAddress.getAddressLineOne())
                    .insertCity(billingAddress.getCity())
                    .selectState(billingAddress.getState())
                    .insertPostalCode(billingAddress.getPostalCode())
                    .insertEmail(billingAddress.getEmail());
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear();
        getElementToBeClickable(placeOrderButton).click();
        return this;
    }

    public String getConfirmationNotice() {
        return getVisibilityOfElement(successNote).getText();
    }
    public String getProductName(){
        return getVisibilityOfElement(productName).getText();
    }
    public boolean getLoginFailErrorMessage(String username){
        return getVisibilityOfElement(loginFailErrorMessage).getText().contains(username);
    }
}
