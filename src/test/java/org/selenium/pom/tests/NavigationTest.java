package org.selenium.pom.tests;

import io.qameta.allure.Description;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NavigationTest extends BaseTest {

    @Description("Navigate from home to store using main menu")
    @Test
    public void navigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver())
                .load()
                .getMyHeader()
                .navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(), "Store");
    }

    @Description("Navigate from store to product page")
    @Test
    public void navigateFromStoreToProductPage() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage = new StorePage(getDriver())
                .load()
                .getProductThumbnail()
                .getStoreProduct(product.getName());
        Assert.assertEquals(productPage.getTitle(), "Blue Shoes");
    }


}
