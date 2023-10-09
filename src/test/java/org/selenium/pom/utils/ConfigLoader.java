package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvironmentType;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String environment = System.getProperty("environment", String.valueOf(EnvironmentType.STAGE));
        switch (EnvironmentType.valueOf(environment)){
            case STAGE:
                properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
                break;
            case PRODUCTION:
                properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                break;
            default:
                throw new IllegalStateException("Invalid environment type: " + environment);
        }

    }

    public static ConfigLoader getInstanceMethod(){
        if (configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl(){
        String properties = this.properties.getProperty("baseUrl");
        if (properties != null){
            return properties;
        } else {
            throw new RuntimeException("property baseUrl is not specified in the stg_config.properties");
        }
    }

    public String getUsername(){
        String properties = this.properties.getProperty("username");
        if (properties != null){
            return properties;
        } else {
            throw new RuntimeException("property username is not specified in the stg_config.properties");
        }
    }
    public String getPassword(){
        String properties = this.properties.getProperty("password");
        if (properties != null){
            return properties;
        } else {
            throw new RuntimeException("property password is not specified in the stg_config.properties");
        }
    }
}
