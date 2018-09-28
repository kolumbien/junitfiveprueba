package com.learn.testing.extensions.beforeAllCallback;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;
import java.util.Properties;

public class BeforeAllClase implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("BeforeAllClase:  " + extensionContext.getTestClass().toString());

        loadPropertiesFromResource("src/test/ejemplo.properties");


    }

    private Properties loadPropertiesFromResource(String resourcePath) {

        try {
            Properties properties = new Properties();


            try (OutputStream output = new FileOutputStream(resourcePath)) {

                if (output == null) {
                    throw new NoSuchFileException(resourcePath);
                }

                properties.setProperty("shop", "Mi shop");
                properties.store(output,null);
            }

            return properties;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



}
