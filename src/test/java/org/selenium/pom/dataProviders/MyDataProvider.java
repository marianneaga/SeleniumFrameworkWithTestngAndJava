package org.selenium.pom.dataProviders;

import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "getFeaturedProducts", parallel = true)
    public Object[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializedJson("products.json", Product[].class);
    }
}
