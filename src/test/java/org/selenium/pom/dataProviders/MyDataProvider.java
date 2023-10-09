package org.selenium.pom.dataProviders;

import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Arrays;

public class MyDataProvider {
    @DataProvider(name = "getFeaturedProducts", parallel = true)
    public Object[] getFeaturedProducts() throws IOException {
        Product[] allProducts = JacksonUtils.deserializedJson("products.json", Product[].class);

        Product[] featuredProducts = Arrays.stream(allProducts)
                .filter(product -> Boolean.TRUE.equals(product.getFeatured()))
                .toArray(Product[]::new);
        return featuredProducts;
    }
}
