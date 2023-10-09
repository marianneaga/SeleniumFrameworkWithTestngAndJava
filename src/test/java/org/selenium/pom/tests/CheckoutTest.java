package org.selenium.pom.tests;

import io.qameta.allure.Description;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @Description("Guest checkout using direct bank transfer")
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializedJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getConfirmationNotice(), "Thank you. Your order has been received.");
    }

    @Description("Login and checkout using direct bank transfer")
    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializedJson("myBillingAddress.json", BillingAddress.class);
        String username = "testuser" + new FakerUtils().generateRandomNumber();
        User user = new User()
                .setUsername(username)
                .setPassword("password")
                .setEmail(username + "@gmail.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getConfirmationNotice(), "Thank you. Your order has been received.");
    }

    @Description("Guest checkout using cash on delivery")
    @Test
    public void guestCheckoutUsingCashOnDelivery() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializedJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load()
                .setBillingAddress(billingAddress)
                .selectCashOnDelivery()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getConfirmationNotice(), "Thank you. Your order has been received.");
    }

    @Description("Login and checkout using cash on delivery")
    @Test
    public void loginAndCheckoutUsingCashOnDelivery() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializedJson("myBillingAddress.json", BillingAddress.class);
        String username = "testuser" + new FakerUtils().generateRandomNumber();
        User user = new User()
                .setUsername(username)
                .setPassword("password")
                .setEmail(username + "@gmail.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load()
                .setBillingAddress(billingAddress)
                .selectCashOnDelivery()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getConfirmationNotice(), "Thank you. Your order has been received.");
    }
}
