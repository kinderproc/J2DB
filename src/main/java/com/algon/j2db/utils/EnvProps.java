package com.algon.j2db.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvProps {

    private static Properties properties = new Properties();

    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String metaUrl() {
        return properties.getProperty("spring.datasource.url");
    }

    public static String metaUser() {
        return properties.getProperty("spring.datasource.username");
    }

    public static String metaPassword() {
        return properties.getProperty("spring.datasource.password");
    }

}
