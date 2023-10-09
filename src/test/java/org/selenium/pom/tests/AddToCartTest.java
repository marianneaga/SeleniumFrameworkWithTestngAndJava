package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.constants.DiscountCoupons;
import org.selenium.pom.dataProviders.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class AddToCartTest extends BaseTest {

    @Test(description = "Add to Cart from Store Page")
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCardButton(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
    @Test(description = "Add to cart from Store Page and Apply a 5 Dollar Discount Coupon")
    public void addToCartFromStorePageAndApplyA5DollarDiscountCoupon() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCardButton(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        double totalPrice = Double.parseDouble(cartPage.getCartTotalPrice());
        System.out.println("TOTAL PRICE BEFORE DISCOUNT: " + totalPrice);
        cartPage.applyCouponCode(String.valueOf(DiscountCoupons.OFFCART5));
        double subtotalPrice = Double.parseDouble(cartPage.getSubtotalPrice());
        double stateTax = Double.parseDouble(cartPage.getStateTax());
        double shippingPrice = Double.parseDouble(cartPage.getShippingPrice());
        Assert.assertEquals(Double.parseDouble(cartPage.getCartTotalPrice()), subtotalPrice + stateTax + shippingPrice - 5.0);
        System.out.println("TOTAL PRICE AFTER DISCOUNT: " + cartPage.getCartTotalPrice());
    }
    @Test(description = "Add to cart from Store Page and Apply a Shipping Free Discount")
    public void addToCartFromStorePageAndApplyShippingFreeDiscount() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCardButton(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        double totalPrice = Double.parseDouble(cartPage.getCartTotalPrice());
        System.out.println("TOTAL PRICE BEFORE DISCOUNT: " + totalPrice);
        cartPage.applyCouponCode(String.valueOf(DiscountCoupons.FREESHIP));
        double shippingPrice = Double.parseDouble(cartPage.getShippingPrice());
        Assert.assertEquals(Double.parseDouble(cartPage.getCartTotalPrice()), totalPrice - shippingPrice);
        System.out.println("TOTAL PRICE AFTER DISCOUNT: " + cartPage.getCartTotalPrice());
    }
    @Test(description = "Add to Cart from Store Page and Apply a 25% discount coupon")
    public void addToCartFromStorePageAndApply25PercentDiscount() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCardButton(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        double totalPrice = Double.parseDouble(cartPage.getCartTotalPrice());
        System.out.println("TOTAL PRICE BEFORE DISCOUNT: " + totalPrice);
        cartPage.applyCouponCode(String.valueOf(DiscountCoupons.OFF25));
        double subtotalPrice = Double.parseDouble(cartPage.getSubtotalPrice());
        double discountOf25Percent = (Double.parseDouble(cartPage.getSubtotalPrice()) * 25)/100;
        double stateTax = Double.parseDouble(cartPage.getStateTax());
        double shippingPrice = Double.parseDouble(cartPage.getShippingPrice());
        Assert.assertEquals(Double.parseDouble(cartPage.getCartTotalPrice()),  (subtotalPrice + stateTax + shippingPrice) - discountOf25Percent);
        System.out.println("TOTAL PRICE AFTER DISCOUNT: " + cartPage.getCartTotalPrice());
    }


    @Test(description = "add to Cart from Product Page")
    public void addToCartFromProductPage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new ProductPage(getDriver())
                .loadProduct(product.getName().toLowerCase().replaceAll(" ", "-"))
                .addToCart()
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test(dataProvider = "getFeaturedProducts",
            dataProviderClass = MyDataProvider.class,
            description = "Add to cart only featured products")
    public void addToCartFeaturedProducts(Product product){
        CartPage cartPage = new HomePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCardButton(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

}