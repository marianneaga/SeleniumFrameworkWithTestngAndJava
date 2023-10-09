package org.selenium.pom.tests;

import io.qameta.allure.Description;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTest extends BaseTest {

    @Description("Search for a product with partial match")
    @Test
    public void searchWithPartialMatch(){
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
    }

    @Description("Search for a product with exact match")
    @Test
    public void searchWithExactMatch() throws IOException {
        Product product = new Product(1215);
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(product.getName());
        ProductPage productPage = new ProductPage(getDriver())
                .loadProduct(product.getName().toLowerCase().replaceAll(" ", "-"));
        Assert.assertEquals(productPage.getTitle(), "Blue Shoes");
    }

    @Description("Search for a non existing product")
    @Test
    public void searchWithNonExistingProduct() throws IOException {
        Product product = new Product(0);
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(product.getName());
        Assert.assertEquals(storePage.getNotFoundProductErrorMessage(), "No products were found matching your selection.");
    }
}
