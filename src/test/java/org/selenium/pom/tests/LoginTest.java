package org.selenium.pom.tests;

import io.qameta.allure.Description;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Description("Login during checkout")
    @Test
    public void loginDuringCheckout() throws IOException {
        String username = "testuser" + new FakerUtils().generateRandomNumber();
        User user = new User()
                .setUsername(username)
                .setPassword("password")
                .setEmail(username + "@gmail.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .clickShowLogin()
                .login(user);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }

    @Description("Login Fails during checkout")
    @Test
    public void loginFails() throws IOException {
        String username = "asdasd";
        User user = new User()
                .setUsername(username)
                .setPassword("password1");
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .clickShowLogin()
                .login(user);
        Assert.assertTrue(checkoutPage.getLoginFailErrorMessage(username));
    }
}
