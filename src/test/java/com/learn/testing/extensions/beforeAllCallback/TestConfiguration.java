package com.learn.testing.extensions.beforeAllCallback;


import org.junit.jupiter.api.extension.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.nio.file.NoSuchFileException;
import java.util.Properties;
import java.util.stream.Stream;

public class TestConfiguration implements ParameterResolver, BeforeAllCallback {


    private Properties properties;
    private TemplateProperties templateProperties;

    public void beforeAll(ExtensionContext extensionContext) {

        properties = initializeProperties();
        templateProperties = new TemplateProperties(properties);

        storeAsSystemProperties(properties, templateProperties);

    }

    private static void storeAsSystemProperties(Properties properties, TemplateProperties templateProperties) {
        properties.stringPropertyNames()
                .forEach(key -> System.setProperty(key, templateProperties.getProperty(key)));
    }


    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        Parameter parameter = parameterContext.getParameter();
        Class<?> type = parameter.getType();

        if (isProperty(type) || isTemplateProperty(type)) {
            return true;
        }

        Property annotation = parameter.getAnnotation(Property.class);
        return annotation != null;

    }

    private boolean isTemplateProperty(Class<?> type) {
        return TemplateProperties.class.isAssignableFrom(type);
    }

    private boolean isProperty(Class<?> type) {
        return Properties.class.isAssignableFrom(type);
    }

    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        Parameter parameter = parameterContext.getParameter();
        Class<?> type = parameter.getType();

        if (isTemplateProperty(type)) {
            return templateProperties;
        } else if (isProperty(type)) {
            return properties;
        }

        Property annotation = parameter.getAnnotation(Property.class);
        if (annotation != null && String.class.isAssignableFrom(type)) {
            Properties source = annotation.resolveVars() ? templateProperties : properties;
            String key = annotation.value();

            if (!source.stringPropertyNames().contains(key)) {
                throw new ParameterResolutionException(String.format("Cannot inject property of name '%s'", key));
            }

            return source.getProperty(key);
        }


        throw new ParameterResolutionException("Cannot inject parameter");

    }

    public Properties initializeProperties() {

        final String ENVIRONMENT = "ENVIRONMENT";
        final String PLATFORM = "PLATFORM";

        final String defaultEnvironment = "QA";
        final String defaultPlatform = "EU";

        Properties properties = new Properties(System.getProperties());

        Properties localProperties = loadPropertiesFromResource("local.properties");
        String environment = System.getProperty("environment", localProperties.getProperty("environment", defaultEnvironment));
        String platform = System.getProperty("platform", localProperties.getProperty("platform", defaultPlatform));

        Stream.of(new String[]{"local", "common"})
                .map(key -> key.replace(ENVIRONMENT, environment).replace(PLATFORM, platform))
                .map(key -> key + ".properties")
                .map(this::loadPropertiesFromResource)
                .forEach(p -> p.forEach(properties::putIfAbsent));

        return properties;
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
        } catch (IOException e) {
            e.printStackTrace();

        }

        return new Properties();

    }


}

