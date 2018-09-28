package com.learn.testing.extensions.beforeTestExecutionCallback;

import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class beforeTestExecutionClass implements BeforeTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        System.out.println("beforeTestExecutionClass:  " + extensionContext.getTestClass().toString());
    }
}
