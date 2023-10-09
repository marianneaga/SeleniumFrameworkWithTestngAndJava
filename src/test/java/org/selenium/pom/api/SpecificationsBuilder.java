package org.selenium.pom.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.selenium.pom.utils.ConfigLoader;

public class SpecificationsBuilder {

    public static RequestSpecification getRequestSpecifications(){
        return new RequestSpecBuilder().
                setBaseUri(ConfigLoader.getInstanceMethod().getBaseUrl()).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpecifications(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }

}
