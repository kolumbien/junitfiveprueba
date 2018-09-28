package com.learn.testing.extensions.beforeAllCallback;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.util.Properties;

public class BeforeAllClaseDos implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("BeforeAllClaseDos:  " + extensionContext.getTestClass().get().getName());
    }


    private Properties loadPropertiesFromResource(String resourcePath) {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Properties properties = new Properties();
            try (InputStream resourceAsStream = classLoader.getResourceAsStream(resourcePath)) {

                if (resourceAsStream == null) {
                    throw new NoSuchFileException(resourcePath);
                }


                properties.load(resourceAsStream);
            }

            return properties;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
