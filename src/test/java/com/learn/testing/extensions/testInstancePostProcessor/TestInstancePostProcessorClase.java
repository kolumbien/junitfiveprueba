package com.learn.testing.extensions.testInstancePostProcessor;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class TestInstancePostProcessorClase implements TestInstancePostProcessor {


    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext extensionContext) throws Exception {
        System.out.println("TestInstancePostProcessorClase: "+testInstance.getClass());

    }
}
