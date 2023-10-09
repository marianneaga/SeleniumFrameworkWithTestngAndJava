package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class Product {
    private int id;
    private String name;
    private Boolean featured;

    public Product() {
    }
//To parse the JSON Array and to extract the product by "id"
    public Product(int id) throws IOException {
        this.id = id;
        Product[] products = JacksonUtils.deserializedJson("products.json", Product[].class);
        for (Product product:
            products){
            if (product.id == id){
                this.id = id;
                this.name = product.name;
                this.featured =product.featured;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }
}
